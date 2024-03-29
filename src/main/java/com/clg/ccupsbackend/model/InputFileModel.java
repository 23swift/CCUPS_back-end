package com.clg.ccupsbackend.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * Inputfile
 */
@Entity
public class InputFileModel {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int fileSection;
    private int fileType;
    private int sequenceNum;
    private String fieldName;
    // private String dataType;
    
    private String size;
    @ManyToOne
    private DataTypeModel dataType;
   
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private InstitutionModel institution;

    public String getSize() {
        return size;
    }

  

    



	public InstitutionModel getInstitution() {
		return institution;
	}







	public void setInstitution(InstitutionModel institution) {
		this.institution = institution;
	}







	public int getSequenceNum() {
        return sequenceNum;
    }

    public void setSequenceNum(int sequenceNum) {
        this.sequenceNum = sequenceNum;
    }

    public DataTypeModel getDataType() {
        return dataType;
    }

    public void setDataType(DataTypeModel dataType) {
        this.dataType = dataType;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public int getFileSection() {
        return fileSection;
    }

    public void setFileSection(int fileSection) {
        this.fileSection = fileSection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSize(String size) {
        this.size = size;
    }

    

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    
    
}