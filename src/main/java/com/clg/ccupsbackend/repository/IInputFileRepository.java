package com.clg.ccupsbackend.repository;

import com.clg.ccupsbackend.model.InputFileModel;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * IInputFileRepository
 */
public interface IInputFileRepository extends JpaRepository<InputFileModel,Long> {

    
}