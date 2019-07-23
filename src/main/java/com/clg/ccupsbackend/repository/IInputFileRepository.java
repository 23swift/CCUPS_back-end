package com.clg.ccupsbackend.repository;

import java.util.List;

import com.clg.ccupsbackend.model.InputFileModel;
import com.clg.ccupsbackend.model.InstitutionModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * IInputFileRepository
 */
public interface IInputFileRepository extends JpaRepository<InputFileModel,Long> {

    // @Query("SELECT  i  FROM InputFileModel i WHERE i.institution.id =1 and i.fileSection=1 and i.fileType=1 ORDER BY i.sequenceNum")
    public List<InputFileModel> findByInstitutionAndFileTypeAndFileSection(InstitutionModel ints,int fileType,int fileSection);
    public List<InputFileModel> findByInstitutionAndFileTypeAndFileSectionOrderBySequenceNumAsc(InstitutionModel ints,int fileType,int fileSection);
}