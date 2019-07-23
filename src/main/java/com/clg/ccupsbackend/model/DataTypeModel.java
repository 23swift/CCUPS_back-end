package com.clg.ccupsbackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * DataType
 */
@Entity
public class DataTypeModel {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String description;
    private String regexpattern;
    public DataTypeModel(){}
    public DataTypeModel(String paramDesc, String paramRegexPattern){
        this.description=paramDesc;
        this.regexpattern=paramRegexPattern;
    }
    public int getId() {
        return Id;
    }

    public String getRegexpattern() {
        return regexpattern;
    }

    public void setRegexpattern(String regexpattern) {
        this.regexpattern = regexpattern;
    }

    public String getDesc() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.Id = id;
    }
    
}