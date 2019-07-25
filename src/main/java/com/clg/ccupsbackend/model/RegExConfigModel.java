package com.clg.ccupsbackend.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * RegExConfigModel
 */
@Entity
public class RegExConfigModel {

    public RegExConfigModel(){}
    
    
  
    public RegExConfigModel( int fileType, int fileSection,String regExPattern) {
        this.fileType = fileType;
        this.fileSection = fileSection;
       
        this.regExPattern = regExPattern;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int fileSection;
    private int fileType;
    @Size(min=1,max=500)
    private String regExPattern; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id")
    @JsonIgnore
    public InstitutionModel institution;

    public int getFileSection() {
        return fileSection;
    }

    public String getRegExPattern() {
        return regExPattern;
    }

    public void setRegExPattern(String regExPattern) {
        this.regExPattern = regExPattern;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public void setFileSection(int fileSection) {
        this.fileSection = fileSection;
    }

    public InstitutionModel getInstitution() {
        return institution;
    }

    public void setInstitution(InstitutionModel institution) {
        this.institution = institution;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}