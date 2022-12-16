package com.hamitmizrak.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private Long id;
    private String name;
    private String surname;
    private String title;
    private String tcNumber;
    private Date createdDate=new Date(System.currentTimeMillis());
}
