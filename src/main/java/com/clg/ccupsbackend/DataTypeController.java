package com.clg.ccupsbackend;

import java.util.List;

import com.clg.ccupsbackend.model.DataTypeModel;
import com.clg.ccupsbackend.repository.DataTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DataTypeController
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class DataTypeController {
@Autowired
private DataTypeRepository repo;
@GetMapping("/getAllDataTypes")
public List<DataTypeModel>getAllDataTypes(){
    return repo.findAll();
}
    
}