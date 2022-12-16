package com.hamitmizrak.business.services;
import com.hamitmizrak.business.dto.BlockDto;
import com.hamitmizrak.business.dto.PatientDto;
import com.hamitmizrak.data.entity.BlockEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IBlockService {

    //model mapper
    public BlockDto entityToDto(BlockEntity blockEntity);
    public BlockEntity dtoToEntity(BlockDto blockDto);

    //CREATE
    @PostMapping("/save/blockchains")
    PatientDto createBlockchain(@RequestBody PatientDto patientDto);

    //list
    @GetMapping("/list/blockchains")
    List<?> getAllBlockchains();

    //find
    public BlockDto getBlockchainById(Long id);
    public PatientDto getPatientById(Long id);

    //delete
    public Map<String,Boolean> deleteBlockchain(Long id);

    //update
    public BlockDto updateBlockchain(Long id,BlockDto blockDto);
}




