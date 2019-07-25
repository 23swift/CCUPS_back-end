package com.clg.ccupsbackend.repository;





import java.util.Collection;
import java.util.List;

import com.clg.ccupsbackend.model.InputFileModel;
import com.clg.ccupsbackend.model.InstitutionModel;
import com.clg.ccupsbackend.model.RegExConfigModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * IInstitutionRepository
 */
@Repository
public interface IInstitutionRepository extends JpaRepository<InstitutionModel,Long> {

    // @Query("SELECT  i.inputFileConfig  FROM InstitutionModel i WHERE i.id=1 and i.inputFileConfig.getFileSection()=1 and i.inputFileConfig.getFileType()=1")
    // public List<InputFileModel> GetInputFileconfigList();
} 