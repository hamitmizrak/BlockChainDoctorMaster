package com.hamitmizrak.api.impl;


import blockchain.Block;
import com.hamitmizrak.api.IBlockChainApiRest;

import com.hamitmizrak.bean.ModelMapperBean;
import com.hamitmizrak.bean.PasswordEncoderBean;
import com.hamitmizrak.business.dto.BlockDto;
import com.hamitmizrak.business.dto.PatientDto;
import com.hamitmizrak.business.services.IBlockService;
import com.hamitmizrak.business.services.IPatientService;
import com.hamitmizrak.data.entity.BlockEntity;
import com.hamitmizrak.data.entity.PatientEntity;
import com.hamitmizrak.data.repository.IBlockRepository;
import com.hamitmizrak.data.repository.IPatientRepository;
import com.hamitmizrak.error.ApiResult;
import com.hamitmizrak.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

//lombok
@Log4j2
//Service => constructor injection(3.YOL)
@RequiredArgsConstructor

//rest
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
//dış dünyaya açılan kapımız
public class BlockChainRestApi implements IBlockChainApiRest {

    private final static String PATH = "/api/v1/blockchains";
    private final IBlockService blockService;


    //speed data
    @Override
    public String getSpeedData() {
        return null;
    }


    //Patient CREATE
    //http://localhost:8080/api/v1/create/blockchains/patients
    @Override
    @PostMapping("/create/blockchains/patients")
    public ResponseEntity<?> createPatientBlockChain(@RequestBody PatientDto patientDto) {
        boolean result = true;
        ApiResult apiResult = null;
        blockService.createBlockchain(patientDto);
        apiResult = new ApiResult(200, PATH, "Created");
        return ResponseEntity.ok(apiResult);
    }


    //Patient LIST
    //http://localhost:8080/api/v1/list/blockchains/patients
    @Override
    @GetMapping("/list/blockchains/patients")
    public ResponseEntity<?> listPatientBlockChain() {
        List<BlockEntity> blockChain = (ArrayList<BlockEntity>) blockService.getAllBlockchains();
        String sum[] = new String[blockChain.size()];

        for (int i = 0; i < blockChain.size(); i++) {
            //bütün zincir
            sum[i] = String.valueOf(blockChain.get(i));
        }

        String result = "";
        for (int i = 0; i < sum.length; i++) {
            //bütün zincir
            result += sum[i];
            System.out.println(result);
        }
        return ResponseEntity.ok(blockService.getAllBlockchains());
    }


    //FIND
    //http://localhost:8080/api/v1/blockchains/1
    @Override
    @GetMapping("/blockchains/{id}")
    public ResponseEntity<BlockDto> getBlockChainById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(blockService.getBlockchainById(id));
    }

    //DELETE
    //http://localhost:8080/api/v1/blockchains/1
    @Override
    @DeleteMapping("/blockchains/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBlockChain(@PathVariable(name = "id") Long id) {
        blockService.deleteBlockchain(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("silindi", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //UPDATE
    //http://localhost:8080/employee/api/v1/blockchains/1
    @Override
    @PutMapping("/blockchains/{id}")
    public ResponseEntity<BlockDto> updateBlockChain(@Valid @PathVariable(name = "id") Long id, @RequestBody BlockDto blockDto) {
        blockService.updateBlockchain(id, blockDto);
        return ResponseEntity.ok(blockDto);
    }
}
