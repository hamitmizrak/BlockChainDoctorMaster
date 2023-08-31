package com.hamitmizrak.api.impl;


import com.hamitmizrak.api.IDoctorApiRest;
import com.hamitmizrak.business.dto.DoctorDto;
import com.hamitmizrak.business.services.IDoctorService;
import com.hamitmizrak.error.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//lombok
@Log4j2
//Service => constructor injection(3.YOL)
@RequiredArgsConstructor

//rest
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
//dış dünyaya açılan kapımız
public class DoctorRestApi implements IDoctorApiRest {

    private final static String PATH="/api/v1/doctors";
    private final IDoctorService service;

    //speed data
    @Override
    public String getSpeedData() {
        return null;
    }

    //SAVE
    //http://localhost:8080/api/v1/doctors
    @Override
    @PostMapping("/doctors")
    public ResponseEntity<?> createDoctor(@Valid @RequestBody DoctorDto doctorDto) {
        service.createDoctor(doctorDto);
        //int status, String path, String message
        ApiResult apiResult=new ApiResult(200,PATH,"created doctors");
        //return ResponseEntity.ok(employeeDto);
        return ResponseEntity.ok(apiResult);
    }

    //LIST
    //http://localhost:8080/api/v1/doctors
    @Override
    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
      List<DoctorDto> list=  service.getAllDoctors();
        return ResponseEntity.ok(list);
    }

    //FIND
    //http://localhost:8080/api/v1/doctors/1
    @Override
    @GetMapping("/doctors/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable(name="id")  Long id) {
        return ResponseEntity.ok(service.getDoctorById(id));
    }

    // DELETE
    // http://localhost:8080/api/v1/doctors/1
    @Override
    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteDoctor(@PathVariable(name="id")  Long id) {
        service.deleteDoctor(id);
        Map<String,Boolean> response=new HashMap<>();
        response.put("silindi",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // UPDATE
    // http://localhost:8080/api/v1/doctors/1
    @Override
    @PutMapping("/doctors/{id}")
    public ResponseEntity<DoctorDto> updateDoctor(@Valid @PathVariable(name="id") Long id, @RequestBody DoctorDto doctorDto) {
        service.updateDoctor(id,doctorDto);
        return ResponseEntity.ok(doctorDto);
    }
}
