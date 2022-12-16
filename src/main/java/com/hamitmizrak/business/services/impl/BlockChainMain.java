package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.business.dto.BlockDto;
import com.hamitmizrak.business.dto.PatientDto;
import com.hamitmizrak.business.services.IBlockService;
import com.hamitmizrak.business.services.IDoctorService;
import com.hamitmizrak.business.services.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.ArrayList;
import java.util.List;
//lombok


@Controller
public class BlockChainMain {

    @Autowired
    private  IBlockService blockService;
    @Autowired
    private  IPatientService patientService;

    @Autowired
    private  IDoctorService doctorService;

    public BlockChainMain() {
    }


    public void lastProject(){

    //Blokchain Patient inital chain data
    List<PatientDto> patientDtoList =   patientService.getAllPatients();
   /* new ArrayList<>(); //patientServices.getAllPatients();
    patientDtoList.add(PatientDto.builder().id(1L).name("adı1").surname("soyadı1").diagnosis("tanı1").tcNumber("tc1").build());
    patientDtoList.add(PatientDto.builder().id(2L).name("adı2").surname("soyadı2").diagnosis("tanı2").tcNumber("tc2").build());
    patientDtoList.add(PatientDto.builder().id(3L).name("adı3").surname("soyadı3").diagnosis("tanı3").tcNumber("tc3").build());*/

    //Blok zinciri listesi oluşturdum
    ArrayList<BlockDto> blockChain = new ArrayList<BlockDto>();
    List<Integer> hashList = new ArrayList<>();

    //ilk zincir


    for (int i = 0; i < patientDtoList.size(); i++) {
        PatientDto[] initialValues = patientDtoList.toArray(new PatientDto[i]);
        BlockDto firstBlock = new BlockDto(initialValues, i);
        blockChain.add(firstBlock);
        //System.out.println((i+1)+". " + firstBlock.toString());
        System.out.println("Block Chain is: " + blockChain.toString());
        hashList.add(firstBlock.getBlockHash());
    }

    //blockChain.forEach((temp)-> System.out.println(temp));
    hashList.forEach((temp)-> System.out.println(temp));
}
//750274062
//750274063
//750274064

/*    public void deneme() {
        //hashTutorials();

        //important: Redis

        //Blokchain Patient inital chain data
        List<PatientDto> patientDtoList = new ArrayList<>(); //patientServices.getAllPatients();
        patientDtoList.add(PatientDto.builder().id(1L).name("adı1").surname("soyadı1").diagnosis("tanı1").tcNumber("tc1").build());
        patientDtoList.add(PatientDto.builder().id(2L).name("adı2").surname("soyadı2").diagnosis("tanı2").tcNumber("tc2").build());
        patientDtoList.add(PatientDto.builder().id(3L).name("adı3").surname("soyadı3").diagnosis("tanı3").tcNumber("tc3").build());

        //Blockchain List
        ArrayList<BlockDto> blockChain = new ArrayList<BlockDto>();
        List<Integer> hashList = new ArrayList<>();

        //Blokchain inital chain data


        for (int i = 0; i < patientDtoList.size(); i++) {
            //Blokchain inital chain data
            BlockDto firstBlock = new BlockDto(patientDtoList.get(i), patientDtoList.get(i).getId());
            blockChain.add(firstBlock);
            System.out.println("Block Chain is: " + blockChain.toString());
            hashList.add(firstBlock.getBlockHash());
        }

    }*/

    public static void main(String[] args) {
        BlockChainMain chainService=new BlockChainMain();
        chainService.lastProject();
    }

}
