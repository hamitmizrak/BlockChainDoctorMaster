package com.hamitmizrak.api;

import com.hamitmizrak.business.dto.PatientDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IPatientApiRest {

    //speed data
    public String getSpeedData();

    //save
    public ResponseEntity<?>  createPatient(PatientDto patientDto);

    //list
    public ResponseEntity<List<PatientDto>>  getAllPatients();

    //find
    public ResponseEntity<PatientDto>  getPatientById(Long id);

    //delete
    public ResponseEntity<Map<String,Boolean>>  deletePatient(Long id);

    //update
    public ResponseEntity<PatientDto>  updatePatient(Long id,PatientDto patientDto);
}
