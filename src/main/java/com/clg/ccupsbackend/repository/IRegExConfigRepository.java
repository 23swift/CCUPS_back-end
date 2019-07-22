package com.clg.ccupsbackend.repository;

import java.util.List;

import com.clg.ccupsbackend.model.InstitutionModel;
import com.clg.ccupsbackend.model.RegExConfigModel;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * RegExConfigRepository
 */
@Repository
public interface IRegExConfigRepository extends JpaRepository<RegExConfigModel,Long>{

    
    public RegExConfigModel findByInstitution(InstitutionModel inst);
    public RegExConfigModel findByInstitutionAndFileTypeAndFileSection(InstitutionModel inst,Long fileType,Long fileSection);
    
}