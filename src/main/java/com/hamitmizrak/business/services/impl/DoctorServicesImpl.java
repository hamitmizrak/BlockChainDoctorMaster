package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.bean.ModelMapperBean;
import com.hamitmizrak.bean.PasswordEncoderBean;
import com.hamitmizrak.business.dto.DoctorDto;
import com.hamitmizrak.business.services.IDoctorService;
import com.hamitmizrak.data.entity.DoctorEntity;
import com.hamitmizrak.data.repository.IDoctorRepository;
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
public class DoctorServicesImpl implements IDoctorService  {

    //constructor injection
    private final IDoctorRepository repository;
    private final ModelMapperBean modelMapper;
    private final PasswordEncoderBean passwordEncoderBean;

    //Model Mapper (DTO )
    @Override
    public DoctorDto entityToDto(DoctorEntity doctorEntity) {
        DoctorDto doctorDto=modelMapper.modelMapperMethod().map(doctorEntity,DoctorDto.class);
        return doctorDto;
    }

    //Model Mapper (Entity)
    @Override
    public DoctorEntity dtoToEntity(DoctorDto doctorDto) {
        DoctorEntity doctorEntity=modelMapper.modelMapperMethod().map(doctorDto,DoctorEntity.class);
        return doctorEntity;
    }

    //CREATE
    @Override
    @PostMapping("/save/doctors")
    public DoctorDto createDoctor(@RequestBody DoctorDto doctorDto) {
        if(doctorDto!=null){
            //Spring Security maskeleme yapmak
            doctorDto.setTcNumber(passwordEncoderBean.passwordEncoderMethod().encode(doctorDto.getTcNumber()));
            DoctorEntity employeeEntity=dtoToEntity(doctorDto);
            repository.save(employeeEntity);
        }
        return doctorDto;
    }

    //LIST
    @Override
    @GetMapping("/list/doctors")
    public List<DoctorDto> getAllDoctors() {
        //entity List (FindAll)
        Iterable<DoctorEntity> entityList=repository.findAll();
        //dto list
        List<DoctorDto> dtoList=new ArrayList<>();
        for (DoctorEntity temp: entityList){
            DoctorDto employeeDto=entityToDto(temp);
            dtoList.add(employeeDto);
        }
        return dtoList;
    }

    //FIND
    @Override
    @GetMapping("/find/doctors/{id}")
    public DoctorDto getDoctorById(@PathVariable(name="id") Long id) {
        //find Entity
        DoctorEntity doctorEntity=repository.findById(id).orElseThrow(()->new ResourceNotFoundException(id+" id bulunamadı"));
        DoctorDto doctorDto=entityToDto(doctorEntity);
        return doctorDto;
    }

    //DELETE
    @Override
    @DeleteMapping("/delete/doctors/{id}")
    public Map<String, Boolean> deleteDoctor(@PathVariable(name="id") Long id) {
        //find Entity
        DoctorEntity doctorEntity=repository.findById(id).orElseThrow(()->new ResourceNotFoundException(id+" id bulunamadı"));
        //Object delete
        repository.delete(doctorEntity);
        Map<String, Boolean> response=new HashMap<>();
        response.put("silindi",Boolean.TRUE);
        return response;
    }

    //UPDATE
    @Override
    @PutMapping("/update/doctors/{id}")
    public DoctorDto updateDoctor(@PathVariable(name="id") Long id, @RequestBody DoctorDto employeeDto) {
        //find Entity
        DoctorEntity doctorEntity=repository.findById(id).orElseThrow(()->new ResourceNotFoundException(id+" id bulunamadı"));
        if(doctorEntity!=null){
            doctorEntity.setName(employeeDto.getName());
            doctorEntity.setSurname(employeeDto.getSurname());
            doctorEntity.setTcNumber(employeeDto.getTcNumber());
            doctorEntity.setTitle(employeeDto.getTitle());
            repository.save(doctorEntity);
        }
        return employeeDto;
    }
}

