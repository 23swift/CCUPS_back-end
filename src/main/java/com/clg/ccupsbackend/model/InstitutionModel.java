package com.clg.ccupsbackend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * InstitutionModel
 */
@Table(name = "Institution")
@Entity
public class InstitutionModel {
   @Id 
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    private String code;
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "institution_id")
    private List<InputFileModel> inputFileConfig = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "institution_id")
    private List<RegExConfigModel> regExPattern= new ArrayList<>();

    public String getCode() {
        return code;
    }

    public List<RegExConfigModel> getRegExPattern() {
		return regExPattern;
	}

	public void setRegExPattern(List<RegExConfigModel> regExPattern) {
		this.regExPattern = regExPattern;
	}

	public List<InputFileModel> getInputFileConfig() {
		return inputFileConfig;
	}

	public void setInputFileConfig(List<InputFileModel> inputFileConfig) {
		this.inputFileConfig = inputFileConfig;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode(String code) {
        this.code = code;
    }

   
}