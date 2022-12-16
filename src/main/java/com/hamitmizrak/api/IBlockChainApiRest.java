package com.hamitmizrak.api;

import com.hamitmizrak.business.dto.BlockDto;
import com.hamitmizrak.business.dto.PatientDto;
import com.hamitmizrak.data.entity.PatientEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IBlockChainApiRest {

    //speed data
    public String getSpeedData();

    //Patient CREATE
    ResponseEntity<?> createPatientBlockChain(PatientDto patientDto);

    ResponseEntity<?> listPatientBlockChain();

    //find
    public ResponseEntity<BlockDto>  getBlockChainById(Long id);

    //delete
    public ResponseEntity<Map<String,Boolean>>  deleteBlockChain(Long id);

    //update
    public ResponseEntity<BlockDto>  updateBlockChain(Long id,BlockDto blockDto);
}
