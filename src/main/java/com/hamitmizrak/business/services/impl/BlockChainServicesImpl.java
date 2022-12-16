package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.bean.ModelMapperBean;
import com.hamitmizrak.bean.PasswordEncoderBean;
import com.hamitmizrak.business.dto.BlockDto;
import com.hamitmizrak.business.dto.PatientDto;
import com.hamitmizrak.business.services.IBlockService;
import com.hamitmizrak.business.services.IPatientService;
import com.hamitmizrak.data.entity.BlockChainLogEntity;
import com.hamitmizrak.data.entity.BlockEntity;
import com.hamitmizrak.data.entity.PatientEntity;
import com.hamitmizrak.data.repository.IBlockChainLogRepository;
import com.hamitmizrak.data.repository.IBlockRepository;
import com.hamitmizrak.data.repository.IPatientRepository;
import com.hamitmizrak.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//lombok
@RequiredArgsConstructor
@Log4j2

@Service
//@Transactional
//Asıl işi yapan katmandır
public class BlockChainServicesImpl implements IBlockService {

    //constructor injection
    private final IBlockRepository blockRepository;
    private final IPatientRepository patientRepository;
    private final ModelMapperBean modelMapper;
    private final PasswordEncoderBean passwordEncoderBean;

    private final IPatientService patientService;

    private final IBlockChainLogRepository chainLogRepository;


    //Model Mapper (DTO )
    @Override
    public BlockDto entityToDto(BlockEntity blockEntity) {
        BlockDto blockDto = modelMapper.modelMapperMethod().map(blockEntity, BlockDto.class);
        return blockDto;
    }

    //Model Mapper (Entity)
    @Override
    public BlockEntity dtoToEntity(BlockDto blockDto) {
        BlockEntity blockEntity = modelMapper.modelMapperMethod().map(blockDto, BlockEntity.class);
        return blockEntity;
    }

    private Boolean isBlcokHashEquals() {
        //Log
        List<BlockChainLogEntity> blockChainLogEntities = chainLogRepository.findAll();
        List<BlockEntity> blockEntityList = blockRepository.findAll();

        if (blockChainLogEntities.size() > 0 && blockEntityList.size() > 0) {
            int blockLogNumber = blockChainLogEntities.get(blockChainLogEntities.size() - 1).getLastRecordBlockHash();
            int blockNumber = blockEntityList.get(blockEntityList.size() - 1).getBlockHash();

            if ( blockLogNumber==blockNumber )
                return true;
        }
        return false;
    }

    //CREATE
    @Override
    public PatientDto createBlockchain(@RequestBody PatientDto patientDto) {
        if (patientDto != null) {
            //patient(1) - blockchain(N)
            //patient(1)
            PatientEntity patientEntity = new PatientEntity();
            patientEntity.setName(patientDto.getName());
            patientEntity.setDiagnosis(patientDto.getDiagnosis());
            patientEntity.setSurname(patientDto.getSurname());
            patientEntity.setTcNumber(passwordEncoderBean.passwordEncoderMethod().encode(patientDto.getTcNumber()));
            patientRepository.save(patientEntity);

            BlockEntity blockEntity = new BlockEntity();
            List<BlockEntity> blockEntitiesList = blockRepository.findAll();
            if (blockEntitiesList.size() != 0) {
                Integer data1 = blockEntitiesList.get(blockEntitiesList.size() - 1).getBlockHash();
                System.out.println(data1);
            }

            if (blockEntitiesList.size() == 0) {
                blockEntity.setPreviosBlockHash(0);

                blockEntity.setPatientEntity(patientEntity);
                blockEntity.setBlockHash(patientDto.hashCode());
                blockRepository.save(blockEntity);
                // Loglama
                BlockChainLogEntity blockChainLogEntity = new BlockChainLogEntity();
                blockChainLogEntity.setLastRecordBlockHash(blockEntity.getBlockHash());
                chainLogRepository.save(blockChainLogEntity);
            } else {
                boolean result=isBlcokHashEquals();
                if(result){
                    System.out.println("Devam ediliyor. !!!");
                    blockEntity.setPreviosBlockHash(blockEntitiesList.get(blockEntitiesList.size() - 1).getBlockHash());
                    blockEntity.setPatientEntity(patientEntity);
                    blockEntity.setBlockHash(patientDto.hashCode());
                    blockRepository.save(blockEntity);
                    // Loglama
                    BlockChainLogEntity blockChainLogEntity = new BlockChainLogEntity();
                    blockChainLogEntity.setLastRecordBlockHash(blockEntity.getBlockHash());
                    chainLogRepository.save(blockChainLogEntity);
                } else{
                    System.out.println("Değişim meydana gelmiş");
                }
            }
        }
        return patientDto;
    }

    //LIST
    @Override
    public List<?> getAllBlockchains() {
       /* List<BlockEntity> list;

        if(id.isPresent()){
            list = blockRepository.findAllByPatientEntity_Id(1L);
        }else {
            list = blockRepository.findAll();
        }
        return list.stream().map(BlockDto::new).collect(Collectors.toList());*/

        List<BlockEntity> listTemp = new ArrayList<>();
        List<BlockDto> blockDtoList = new ArrayList<>();
        for (long i = 1; i <= blockRepository.findAll().size(); i++) {
            PatientDto patientDto2 = patientService.getPatientById(i);
            PatientEntity patientEntity1 = modelMapper.modelMapperMethod().map(patientDto2, PatientEntity.class);
            BlockEntity blockEntity1 = blockRepository.findById(i).orElseThrow(() -> new ResourceNotFoundException("Bulunamadı"));
            blockEntity1.setPatientEntity(patientEntity1);
            listTemp.add(blockEntity1);
        }
        //listTemp.stream().map((temp) -> temp.setPatientEntity(patientEntity1)).collect(Collectors.toList());
        //listTemp.forEach((temp) -> System.out.println(temp));

       /* for(BlockEntity temp:  listTemp){
            BlockDto blockDto=modelMapper.modelMapperMethod().map(temp,BlockDto.class);
            blockDtoList.add(blockDto);
        }*/

    /*    public List<BlogResponse> getAllBlogPosts(Optional<Long> userId){
        List<BlogPosts> list;
        if(userId.isPresent()){
            list = blogPostsRepository.findAllByBlogUsers_UserId(userId.get());
        }else {
            list = blogPostsRepository.findAll();
        }
        return list.stream().map(BlogResponse::new).collect(Collectors.toList());
    }*/
        return listTemp;
    }


    //FIND
    @Override
    @GetMapping("/find/blockchains/{id}")
    public BlockDto getBlockchainById(@PathVariable(name = "id") Long id) {
        //find Entity
        BlockEntity blockEntity = blockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " id bulunamadı"));
        BlockDto blockDto = entityToDto(blockEntity);
        return blockDto;
    }

    @Override
    public PatientDto getPatientById(Long id) {
        PatientEntity patientEntity = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " id bulunamadı"));
        PatientDto patientDto = modelMapper.modelMapperMethod().map(patientEntity, PatientDto.class);
        return patientDto;
    }

    //DELETE
    @Override
    @DeleteMapping("/delete/blockchains/{id}")
    public Map<String, Boolean> deleteBlockchain(@PathVariable(name = "id") Long id) {
        //find Entity
        BlockEntity blockEntity = blockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " id bulunamadı"));
        //Object delete
        blockRepository.delete(blockEntity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("silindi", Boolean.TRUE);
        return response;
    }

    //UPDATE
    @Override
    @PutMapping("/update/blockchains/{id}")
    public BlockDto updateBlockchain(@PathVariable(name = "id") Long id, @RequestBody BlockDto blockDto) {
        //find Entity
        BlockEntity blockEntity = blockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " id bulunamadı"));
        if (blockEntity != null) {
            blockEntity.setBlockHash(blockDto.getBlockHash());
            blockEntity.setPreviosBlockHash(blockDto.getPreviousBlockHash());
            //blockEntity.setTransactions(blockDto.getTransactions());
            blockRepository.save(blockEntity);
        }
        return blockDto;
    }
}

