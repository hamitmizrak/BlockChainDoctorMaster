package com.hamitmizrak.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.util.Date;


//lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2

//Entity
@Entity
@Table(name="log")
public class BlockChainLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "last_record_blockHash")
    private Integer lastRecordBlockHash;


    @Column(name = "created_date")
    private Date createdDate;
}
