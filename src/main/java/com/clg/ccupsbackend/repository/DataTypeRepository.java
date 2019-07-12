package com.clg.ccupsbackend.repository;
import com.clg.ccupsbackend.model.DataTypeModel;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * DataTypeRepository
 */
public interface DataTypeRepository extends JpaRepository<DataTypeModel,Long> {

    
}