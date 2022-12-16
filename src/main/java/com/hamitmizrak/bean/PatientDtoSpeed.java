package com.hamitmizrak.bean;

import com.hamitmizrak.business.dto.PatientDto;
import com.hamitmizrak.business.services.impl.PatientServicesImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;





@Configuration
public class PatientDtoSpeed {
    //CommandLineReunner ==> interface
    //Lambda i interface 1 metodu varsa bunu lambda expression ile yazabiliriz.

    @Bean
    CommandLineRunner createLogin(PatientServicesImpl service){
        //Lambda Expression Java=tineError Javascript=FateError
        return (args)->{
            for (int i = 1; i <=1 ; i++) {
                PatientDto patientDto= PatientDto.builder()
                        .name("adı"+i).surname("soyadı"+i).diagnosis("tanı"+i).tcNumber("tc"+i).build();
                //service.createPatient(patientDto);
            }

        };


    }
}

