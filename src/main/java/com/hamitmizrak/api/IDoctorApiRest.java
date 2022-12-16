package com.hamitmizrak.api;

import com.hamitmizrak.business.dto.DoctorDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IDoctorApiRest {

    //speed data
    public String getSpeedData();

    //save
    public ResponseEntity<?>  createDoctor(DoctorDto doctorDto);

    //list
    public ResponseEntity<List<DoctorDto>>  getAllDoctors();

    //find
    public ResponseEntity<DoctorDto>  getDoctorById(Long id);

    //delete
    public ResponseEntity<Map<String,Boolean>>  deleteDoctor(Long id);

    //update
    public ResponseEntity<DoctorDto>  updateDoctor(Long id,DoctorDto doctorDto);
}
