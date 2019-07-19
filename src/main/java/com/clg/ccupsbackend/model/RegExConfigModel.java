package com.clg.ccupsbackend.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * RegExConfigModel
 */
@Entity
public class RegExConfigModel {

    public RegExConfigModel(){}
    
    
  
    public RegExConfigModel(int fileSection, int fileType, String regExPattern) {

        this.fileSection = fileSection;
        this.fileType = fileType;
        this.regExPattern = regExPattern;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int fileSection;
    private int fileType;
    private String regExPattern; 

    @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "institution_id", referencedColumnName="id")
    private InstitutionModel institution;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}