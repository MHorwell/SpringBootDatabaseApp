package com.horwell.matthew.springboot.database.springBootDatabaseApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.horwell.matthew.springboot.database.springBootDatabaseApp.model.SpringBootDataModel;

@Repository
public interface SpringBootRepository extends JpaRepository<SpringBootDataModel,Long>{

}
