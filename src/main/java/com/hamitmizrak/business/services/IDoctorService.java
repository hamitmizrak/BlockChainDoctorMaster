package com.hamitmizrak.business.services;

import com.hamitmizrak.business.dto.DoctorDto;
import com.hamitmizrak.data.entity.DoctorEntity;

import java.util.List;
import java.util.Map;

public interface IDoctorService {

    //model mapper
    public DoctorDto entityToDto(DoctorEntity doctorEntity);
    public DoctorEntity dtoToEntity(DoctorDto doctorDto);

    //save
    public DoctorDto createDoctor(DoctorDto doctorDto);

    //list
    public List<DoctorDto> getAllDoctors();

    //find
    public DoctorDto getDoctorById(Long id);

    //delete
    public Map<String,Boolean> deleteDoctor(Long id);

    //update
    public DoctorDto updateDoctor(Long id,DoctorDto doctorDto);
}




