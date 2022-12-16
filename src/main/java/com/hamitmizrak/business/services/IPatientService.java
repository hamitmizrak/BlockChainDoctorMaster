package com.hamitmizrak.business.services;

import com.hamitmizrak.business.dto.DoctorDto;
import com.hamitmizrak.business.dto.PatientDto;
import com.hamitmizrak.data.entity.DoctorEntity;
import com.hamitmizrak.data.entity.PatientEntity;

import java.util.List;
import java.util.Map;

public interface IPatientService {

    //model mapper
    public PatientDto entityToDto(PatientEntity patientEntity);
    public PatientEntity dtoToEntity(PatientDto patientDto);

    //save
    public PatientDto createPatient(PatientDto patientDto);

    //list
    public List<PatientDto> getAllPatients();

    //find
    public PatientDto getPatientById(Long id);

    //delete
    public Map<String,Boolean> deletePatient(Long id);

    //update
    public PatientDto updatePatient(Long id,PatientDto patientDto);
}




