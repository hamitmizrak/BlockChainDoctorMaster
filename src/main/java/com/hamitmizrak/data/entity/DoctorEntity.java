package com.hamitmizrak.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

//lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2

//Entity
@Entity
@Table(name="doctor")
public class DoctorEntity implements Serializable {
    public static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long id;

    private String name;
    private String surname;
    private String title;
    @Column(name = "tc_number")
    private String tcNumber;

    //relation (Doktor(N) - Patient(N)
    //@ManyToMany(fetch = FetchType.EAGER)
    //@JoinTable(joinColumns = @JoinColumn(name="doctor_id"),inverseJoinColumns = @JoinColumn(name="patient_id"))
    //private Collection<PatientEntity>  patientEntityList;

    @Column(name = "created_date")
    //@CreationTimestamp
    //@Temporal(TemporalType.TIMESTAMP)
    private Date createdDate= new Date(System.currentTimeMillis());

}
