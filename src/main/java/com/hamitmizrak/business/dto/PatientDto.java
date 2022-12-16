package com.hamitmizrak.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientDto {
    private Long id;
    private String name;
    private String surname;
    private String diagnosis;
    private String tcNumber;
    private Date createdDate=new Date(System.currentTimeMillis());
}
