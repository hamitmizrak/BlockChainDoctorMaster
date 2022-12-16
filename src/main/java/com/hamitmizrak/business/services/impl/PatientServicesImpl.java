package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.bean.ModelMapperBean;
import com.hamitmizrak.bean.PasswordEncoderBean;
import com.hamitmizrak.business.dto.DoctorDto;
import com.hamitmizrak.business.dto.PatientDto;
import com.hamitmizrak.business.services.IDoctorService;
import com.hamitmizrak.business.services.IPatientService;
import com.hamitmizrak.data.entity.DoctorEntity;
import com.hamitmizrak.data.entity.PatientEntity;
import com.hamitmizrak.data.repository.IDoctorRepository;
import com.hamitmizrak.data.repository.IPatientRepository;
import com.hamitmizrak.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//lombok
@RequiredArgsConstructor
@Log4j2

@Service
//@Transactional
//Asıl işi yapan katmandır
public class PatientServicesImpl implements IPatientService {

    //constructor injection
    private final IPatientRepository repository;
    private final ModelMapperBean modelMapper;
    private final PasswordEncoderBean passwordEncoderBean;

    //Model Mapper (DTO )
    @Override
    public PatientDto entityToDto(PatientEntity patientEntity) {
        PatientDto doctorDto=modelMapper.modelMapperMethod().map(patientEntity,PatientDto.class);
        return doctorDto;
    }

    //Model Mapper (Entity)
    @Override
    public PatientEntity dtoToEntity(PatientDto patientDto) {
        PatientEntity patientEntity=modelMapper.modelMapperMethod().map(patientDto,PatientEntity.class);
        return patientEntity;
    }

    //CREATE
    @Override
    @PostMapping("/save/patients")
    public PatientDto createPatient(@RequestBody PatientDto patientDto) {
        if(patientDto!=null){
            //Spring Security maskeleme yapmak
            patientDto.setTcNumber(passwordEncoderBean.passwordEncoderMethod().encode(patientDto.getTcNumber()));
            PatientEntity employeeEntity=dtoToEntity(patientDto);
            repository.save(employeeEntity);
        }
        return patientDto;
    }

    //LIST
    @Override
    @GetMapping("/list/patients")
    public List<PatientDto> getAllPatients() {
        //entity List (FindAll)
        Iterable<PatientEntity> entityList=repository.findAll();
        //dto list
        List<PatientDto> dtoList=new ArrayList<>();
        for (PatientEntity temp: entityList){
            PatientDto employeeDto=entityToDto(temp);
            dtoList.add(employeeDto);
        }
        return dtoList;
    }

    //FIND
    @Override
    @GetMapping("/find/patients/{id}")
    public PatientDto getPatientById(@PathVariable(name="id") Long id) {
        //find Entity
        PatientEntity patientEntity=repository.findById(id).orElseThrow(()->new ResourceNotFoundException(id+" id bulunamadı"));
        PatientDto patientDto=entityToDto(patientEntity);
        return patientDto;
    }

    //DELETE
    @Override
    @DeleteMapping("/delete/patients/{id}")
    public Map<String, Boolean> deletePatient(@PathVariable(name="id") Long id) {
        //find Entity
        PatientEntity patientEntity=repository.findById(id).orElseThrow(()->new ResourceNotFoundException(id+" id bulunamadı"));
        //Object delete
        repository.delete(patientEntity);
        Map<String, Boolean> response=new HashMap<>();
        response.put("silindi",Boolean.TRUE);
        return response;
    }

    //UPDATE
    @Override
    @PutMapping("/update/patients/{id}")
    public PatientDto updatePatient(@PathVariable(name="id") Long id, @RequestBody PatientDto patientDto) {
        //find Entity
        PatientEntity patientEntity=repository.findById(id).orElseThrow(()->new ResourceNotFoundException(id+" id bulunamadı"));
        if(patientEntity!=null){
            patientEntity.setName(patientDto.getName());
            patientEntity.setSurname(patientDto.getSurname());
            patientEntity.setTcNumber(patientDto.getTcNumber());
            patientEntity.setDiagnosis(patientDto.getDiagnosis());
            repository.save(patientEntity);
        }
        return patientDto;
    }
}

