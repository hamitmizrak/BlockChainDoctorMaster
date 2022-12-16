package com.hamitmizrak.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

//lombok
@Data
@Log4j2

//Entity
@Entity
@Table(name="blockchain")
public class BlockEntity implements Serializable {
    public static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_id")
    private Long id;

    @Column(name = "transactions")
    @Transient
    private List<String> transactions;

    @Column(name = "block_hash")
    private Integer blockHash;

    @Column(name = "previos_block_hash")
    private Integer previosBlockHash;

    //relation Patient(1) -Block(N)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonIgnore
    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
   // @JoinColumn(name = "patient_id",nullable = false)
    /*ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id",insertable = false, updatable = false)*/
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id",nullable = true)
    //@Fetch(FetchMode.JOIN)
    private PatientEntity patientEntity;

    public BlockEntity() {
    }

    public BlockEntity(Long id, Integer blockHash, Integer previosBlockHash, PatientEntity patientEntity) {
        this.id = id;
        this.blockHash = blockHash;
        this.previosBlockHash = previosBlockHash;
        this.patientEntity = patientEntity;
    }


    @Column(name = "created_date")
    //@CreationTimestamp
    //@Temporal(TemporalType.TIMESTAMP)
    private Date createdDate= new Date(System.currentTimeMillis());

}
