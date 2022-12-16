package com.hamitmizrak.data.repository;

import com.hamitmizrak.data.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDoctorRepository extends JpaRepository<DoctorEntity,Long> {

}

