package com.hamitmizrak.api.impl;


import com.hamitmizrak.api.IPatientApiRest;
import com.hamitmizrak.business.dto.PatientDto;
import com.hamitmizrak.business.services.IPatientService;
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
public class PatientRestApi implements IPatientApiRest {

    private final static String PATH="/api/v1/patients";
    private final IPatientService service;

    //speed data
    @Override
    public String getSpeedData() {
        return null;
    }

    //SAVE
    //http://localhost:8080/api/v1/patients
    @Override
    @PostMapping("/patients")
    public ResponseEntity<?> createPatient(@Valid @RequestBody PatientDto patientDto) {
        service.createPatient(patientDto);
        //int status, String path, String message
        ApiResult apiResult=new ApiResult(200,PATH,"created Employee");
        //return ResponseEntity.ok(employeeDto);
        return ResponseEntity.ok(apiResult);
    }

    // LIST
    //http://localhost:8080/api/v1/patients
    @Override
    @GetMapping("/patients")
    public ResponseEntity<List<PatientDto>> getAllPatients() {
      List<PatientDto> list=  service.getAllPatients();
        return ResponseEntity.ok(list);
    }

    // FIND
    // http://localhost:8080/api/v1/patients/1
    @Override
    @GetMapping("/patients/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable(name="id")  Long id) {
        return ResponseEntity.ok(service.getPatientById(id));
    }

    //DELETE
    //http://localhost:8080/api/v1/patients/1
    @Override
    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePatient(@PathVariable(name="id")  Long id) {
        service.deletePatient(id);
        Map<String,Boolean> response=new HashMap<>();
        response.put("silindi",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // UPDATE
    // http://localhost:8080/api/v1/patients/1
    @Override
    @PutMapping("/patients/{id}")
    public ResponseEntity<PatientDto> updatePatient(@Valid @PathVariable(name="id") Long id, @RequestBody PatientDto patientDto) {
        service.updatePatient(id,patientDto);
        return ResponseEntity.ok(patientDto);
    }
}
