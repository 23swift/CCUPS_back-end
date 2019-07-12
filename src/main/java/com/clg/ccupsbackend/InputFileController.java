package com.clg.ccupsbackend;

import java.util.List;

import com.clg.ccupsbackend.model.InputFileModel;
import com.clg.ccupsbackend.repository.IInputFileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/GetAllInputFileConfig")
    public List<InputFileModel> GetInputFileConfig(){
        // List<PostModel> list= new ArrayList<PostModel>();
        List<InputFileModel> list;
        // list=repo.findAll(Sort.by(Direction.DESC,"sequenceNum"));
        list=repo.findAll(Sort.by(Direction.ASC,"sequenceNum"));
       
        return list;
    }
    @PostMapping("/addFileConfig")
    public InputFileModel AddFileConfig(@RequestBody  InputFileModel fileConfig) {
        repo.save(fileConfig);
        return fileConfig;
    }
}