package com.hamitmizrak.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

//lombok
@Data
@Log4j2

//Entity
@Entity
@Table(name="patient")
public class PatientEntity implements Serializable {
    public static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long id;

    private String name;
    private String surname;
    private String diagnosis;
    @Column(name = "tc_number")
    private String tcNumber;

    // Relation    @ManyToMany(mappedBy = "rolesEntities", fetch = FetchType.LAZY)    private Collection<AdminEntity> adminEntities;

    //relation (Doktor(N) - Patient(N)
    //@ManyToMany(mappedBy = "patientEntityList",fetch = FetchType.LAZY)
    //private Collection<DoctorEntity>  doctorEntityList;

    //relation Patient(1) -Block(N)
    @OneToMany(mappedBy = "patientEntity",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    //@OneToMany(targetEntity = BlockEntity.class, mappedBy = "patientEntity", orphanRemoval = false, fetch = FetchType.LAZY)
    private List<BlockEntity> blockEntityList;

    public PatientEntity() {
    }

    public PatientEntity(Long id, String name, String surname, String diagnosis, String tcNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.diagnosis = diagnosis;
        this.tcNumber = tcNumber;
    }

    @Column(name = "created_date")
    //@CreationTimestamp
    //@Temporal(TemporalType.TIMESTAMP)
    private Date createdDate= new Date(System.currentTimeMillis());
}
