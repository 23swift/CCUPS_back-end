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

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public List<InputFileModel> GetInputFileConfig(@RequestParam("instId") Integer instId,@RequestParam("fileType") Integer fileType, @RequestParam("fileSection") Integer fileSection){
        List<InputFileModel> list;
        InstitutionModel inst=institutionRepo.findById(Long.valueOf(instId)).get();
        // list=repo.findAll(Sort.by(Direction.DESC,"sequenceNum"));
        list=repo.findByInstitutionAndFileTypeAndFileSectionOrderBySequenceNumAsc(inst,fileType,fileSection);
        return list;
    }

    @GetMapping("/GetInputFileConfigOrderBySequenceAsc")
    public List<InputFileModel> GetInputFileConfigOrderBySequenceAsc(@RequestParam("instId") Integer instId,@RequestParam("fileType") Integer fileType, @RequestParam("fileSection") Integer fileSection){
        InstitutionModel inst=institutionRepo.findById(Long.valueOf(instId)).get();
        List<InputFileModel> list =repo.findByInstitutionAndFileTypeAndFileSectionOrderBySequenceNumAsc(inst,fileType,fileSection);
        return list;
    }

    @PostMapping("/addFileConfig")
    public  InputFileModel AddFileConfig(@RequestBody  InputFileModel fileConfig,@RequestParam("instId") Integer instId) {
        
        // repo.save(fileConfig);
        InstitutionModel inst=institutionRepo.findById(Long.valueOf(instId)).get();
       
        inst.getInputFileConfig().add(fileConfig);
         institutionRepo.save(inst);
        institutionRepo.flush();
        generateRegEx(inst,fileConfig.getFileType(),fileConfig.getFileSection());
       
       
        return fileConfig;
        // return inst.regExPattern;
    }
    @DeleteMapping("/deleteFileConfig")
    public List<InputFileModel> DeleteFileConfig(@RequestBody  long id) {
        List<InputFileModel> list;
        InputFileModel currentItem = repo.findById(id).get();
        repo.delete(currentItem);
       
        list=repo.findByInstitutionAndFileTypeAndFileSectionOrderBySequenceNumAsc(currentItem.getInstitution(),currentItem.getFileType(),currentItem.getFileSection());
        return list;
    }
    @PutMapping("/UpdateConfigSequence")
    public void UpdateConfigSequence(@RequestBody List<InputFileModel> fileConfigList,@RequestParam("instId") Integer instId,@RequestParam("fileType") Integer fileType, @RequestParam("fileSection") Integer fileSection){
        InstitutionModel inst=institutionRepo.findById(Long.valueOf(instId)).get();
       for (InputFileModel item : fileConfigList) {
        InputFileModel currentItem = repo.findById(Long.valueOf(item.getId())).get();
        currentItem.setSequenceNum(item.getSequenceNum());
        repo.save(currentItem);   
       }
      
       
       generateRegEx(inst,fileType,fileSection);
    }
    @PutMapping("/UpdateConfig")
    public void UpdateConfig(@RequestBody InputFileModel fileConfig){
       
        InputFileModel currentItem = repo.findById(Long.valueOf(fileConfig.getId())).get();
        currentItem=fileConfig;
        repo.save(currentItem);   
    }
    @PostMapping("/GetMatchingInfo")
    public List<MatchInfo> GetMatchingInfo( @RequestBody String fileContent,  @RequestParam("instId") Integer instId,@RequestParam("fileType") Integer fileType, @RequestParam("fileSection") Integer fileSection){
        List<MatchInfo> result=null;
        InstitutionModel inst=institutionRepo.findById(Long.valueOf(instId)).get();
        RegExConfigModel regeExConfig=regExConfigRepo.findByInstitutionAndFileTypeAndFileSection(inst, fileType , fileSection);
        final String pattern= regeExConfig.getRegExPattern();
    //    final String pattern= "(?<recordType>[0-9]{3})(?<cardNumber>[0-9]{16})(?<name>[a-zA-Z0-9\\s\\_\\@\\$\\&\\(\\)\\-\\[\\]\\;\\:\\,\\.\\/\\|\\\\]{30})(?<accountNumber>[0-9]{10})(?<amount>[0-9]{13})(?<amountDecimal>[0-9]{2})(?<transactionDate>[0-9]{8})(?<batchNumber>[a-zA-Z0-9]{10})(?<bankBatchId>[0-9]{10})";
    // final String pattern= "(?<recordType>[0-9]{3})";
        result = regsexService.getMatchInformation(pattern, fileContent);
        
        return result;

    }
    @GetMapping("/GetMatchingGroup")
    public List<String> GetMatchingGroup(@RequestBody String pattern){
        List<String> result=null;
    //    final String patternS=  "(?<recordType>[0-9]{3})(?<cardNumber>[0-9]{16})(?<name>[a-zA-Z0-9\\s\\_\\@\\$\\&\\(\\)\\-\\[\\]\\;\\:\\,\\.\\/\\|\\\\]{30})(?<accountNumber>[0-9]{10})";;
        result = regsexService.getGroupNames( pattern);
        
        return result;

    }
    private void generateRegEx(InstitutionModel inst,int fileType,int fileSection){
        RegExConfigModel regEx=regExConfigRepo.findByInstitutionAndFileTypeAndFileSection(inst,fileType,fileSection);
        if(regEx ==null){
            regEx=new RegExConfigModel(fileType,fileSection,GenerateRegexBasedOnSequence(inst.getId(),fileType,fileSection));
        inst.getRegExPattern().add(regEx);
        institutionRepo.save(inst);
        }else{
            regEx.setRegExPattern(GenerateRegexBasedOnSequence(inst.getId(),fileType,fileSection));
            regExConfigRepo.save(regEx);
            regExConfigRepo.flush();
        }
        
        institutionRepo.flush();
    }

    private String GenerateRegexBasedOnSequence(Long instId, int fileType, int fileSection ){
        InstitutionModel inst=institutionRepo.findById(instId).get();
        List<InputFileModel> config =repo.findByInstitutionAndFileTypeAndFileSectionOrderBySequenceNumAsc(inst,fileType,fileSection);
        String result="";
        
        for (InputFileModel item : config) {
            if(item.getSequenceNum()==1){
                result= result +"(?<" +item.getFieldName() +">^" + item.getDataType().getRegexpattern() +"{"+item.getSize()+"}"+")" ;
            }else{
                result= result +"(?<" +item.getFieldName() +">" + item.getDataType().getRegexpattern() +"{"+item.getSize()+"}"+")" ;
            }
            
        }
        


        return result;

    }
}