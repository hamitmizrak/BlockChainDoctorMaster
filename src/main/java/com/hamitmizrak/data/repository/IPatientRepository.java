package com.hamitmizrak.data.repository;

import com.hamitmizrak.data.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepository extends JpaRepository<PatientEntity,Long> {


}

