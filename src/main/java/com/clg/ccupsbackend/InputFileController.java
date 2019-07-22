package com.clg.ccupsbackend;

import java.util.List;
import java.util.Optional;

import com.clg.ccupsbackend.model.DataTypeModel;
import com.clg.ccupsbackend.model.InputFileModel;
import com.clg.ccupsbackend.model.InstitutionModel;
import com.clg.ccupsbackend.model.MatchInfo;
import com.clg.ccupsbackend.model.RegExConfigModel;
import com.clg.ccupsbackend.regExUtil.RegExUtilService;
import com.clg.ccupsbackend.repository.IInputFileRepository;
import com.clg.ccupsbackend.repository.IInstitutionRepository;
import com.clg.ccupsbackend.repository.IRegExConfigRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * InputFileController
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class InputFileController {

    @Autowired IInputFileRepository repo;
    @Autowired IInstitutionRepository institutionRepo;
    @Autowired IRegExConfigRepository regExConfigRepo;

    @Autowired 
    private RegExUtilService regsexService;

    @GetMapping("/GetAllInputFileConfig")
    public List<InputFileModel> GetInputFileConfig(){
        // List<PostModel> list= new ArrayList<PostModel>();
        List<InputFileModel> list;
        // list=repo.findAll(Sort.by(Direction.DESC,"sequenceNum"));
        list=repo.findAll( Sort.by(Direction.ASC,"sequenceNum"));
        return list;
    }

    @GetMapping("/GetInputFileConfigOrderBySequenceAsc")
    public List<InputFileModel> GetInputFileConfigOrderBySequenceAsc(){
        InstitutionModel inst=institutionRepo.findById(Long.valueOf(1)).get();
        List<InputFileModel> list =repo.findByInstitutionOrderBySequenceNumAsc(inst);
        return list;
    }

    @PostMapping("/addFileConfig")
    public  InputFileModel AddFileConfig(@RequestBody  InputFileModel fileConfig) {
        
        // repo.save(fileConfig);
        InstitutionModel inst=institutionRepo.findById(Long.valueOf(1)).get();
        
        inst.inputFileConfig.add(fileConfig);
        institutionRepo.save(inst);
        institutionRepo.flush();
        generateRegEx(inst);
         
      
        return fileConfig;
        // return inst.regExPattern;
    }
    @DeleteMapping("/deleteFileConfig")
    public List<InputFileModel> DeleteFileConfig(@RequestBody  long id) {
        List<InputFileModel> list;
        InputFileModel currentItem = repo.findById(id).get();
        repo.delete(currentItem);
       
        list=repo.findAll(Sort.by(Direction.ASC,"sequenceNum"));
        return list;
    }
    @PutMapping("/UpdateConfigSequence")
    public void UpdateConfigSequence(@RequestBody List<InputFileModel> fileConfigList){
        InstitutionModel inst=institutionRepo.findById(Long.valueOf(1)).get();
       for (InputFileModel item : fileConfigList) {
        InputFileModel currentItem = repo.findById(item.getId()).get();
        currentItem.setSequenceNum(item.getSequenceNum());
        repo.save(currentItem);   
       }
      
       
       generateRegEx(inst);
    }
    @PutMapping("/UpdateConfig")
    public void UpdateConfig(@RequestBody InputFileModel fileConfig){
       
        InputFileModel currentItem = repo.findById(fileConfig.getId()).get();
        currentItem=fileConfig;
        repo.save(currentItem);   
    }
    @GetMapping("/GetMatchingInfo")
    public List<MatchInfo> GetMatchingInfo(@RequestBody String fileContent, Long instId){
        List<MatchInfo> result=null;
        InstitutionModel inst=institutionRepo.findById(Long.valueOf(instId)).get();
        RegExConfigModel regeExConfig=regExConfigRepo.findByInstitutionAndFileTypeAndFileSection(inst, Long.valueOf(1) , Long.valueOf(1));
        final String pattern= regeExConfig.getRegExPattern();
    //    final String pattern= "(?<recordType>[0-9]{3})(?<cardNumber>[0-9]{16})(?<name>[a-zA-Z0-9\\s\\_\\@\\$\\&\\(\\)\\-\\[\\]\\;\\:\\,\\.\\/\\|\\\\]{30})(?<accountNumber>[0-9]{10})(?<amount>[0-9]{13})(?<amountDecimal>[0-9]{2})(?<transactionDate>[0-9]{8})(?<batchNumber>[a-zA-Z0-9]{10})(?<bankBatchId>[0-9]{10})";
    // final String pattern= "(?<recordType>[0-9]{3})";
        result = regsexService.getMatchInformation(pattern, fileContent +"\n");
        
        return result;

    }
    @GetMapping("/GetMatchingGroup")
    public List<String> GetMatchingGroup(@RequestBody String pattern){
        List<String> result=null;
    //    final String patternS=  "(?<recordType>[0-9]{3})(?<cardNumber>[0-9]{16})(?<name>[a-zA-Z0-9\\s\\_\\@\\$\\&\\(\\)\\-\\[\\]\\;\\:\\,\\.\\/\\|\\\\]{30})(?<accountNumber>[0-9]{10})";;
        result = regsexService.getGroupNames( pattern);
        
        return result;

    }
    private void generateRegEx(InstitutionModel inst){
        RegExConfigModel regEx=regExConfigRepo.findByInstitution(inst);
        if(regEx ==null){
            regEx=new RegExConfigModel(1,1,GenerateRegexBasedOnSequence(inst));
        inst.regExPattern.add(regEx);
        institutionRepo.save(inst);
        }else{
            regEx.setRegExPattern(GenerateRegexBasedOnSequence(inst));
            regExConfigRepo.save(regEx);
            regExConfigRepo.flush();
        }
        
        institutionRepo.flush();
    }

    private String GenerateRegexBasedOnSequence(InstitutionModel inst ){
       
        List<InputFileModel> config =repo.findByInstitutionOrderBySequenceNumAsc(inst);
        String result="";
        
        for (InputFileModel item : config) {
            result= result +"(?<" +item.getFieldName() +">" + item.getDataType().getRegexpattern() +"{"+item.getSize()+"}"+")" ;
        }
        


        return result;

    }
}