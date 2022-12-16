package com.hamitmizrak.business.dto;

import com.hamitmizrak.data.entity.BlockEntity;
import com.hamitmizrak.data.entity.PatientEntity;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class BlockDto {

    private PatientDto[] transactions;
    private int blockHash;
    private int previousBlockHash;
    private Date createdDate=new Date(System.currentTimeMillis());

    private PatientDto patientDto;

    //not parameters constructor
    public BlockDto() {
    }

    //parameters constructor
    public BlockDto(PatientDto[] transactions, int previousBlockHash) {
        this.transactions = transactions;
        this.previousBlockHash = previousBlockHash;
        //bir önceki ile şimdikinini karması
        this.blockHash= Arrays.hashCode(new int[]{ Arrays.hashCode(transactions) , this.previousBlockHash});
    }

    public BlockDto(BlockEntity blockEntity) {
    }

    @Override
    public String toString() {
        return "Block{" +
                "transactions=" + Arrays.toString(transactions) +
                ","+ ".blockHash=" + blockHash +
                ", previousBlockHash=" + previousBlockHash +
                ", createdDate=" + createdDate +
                '}';
    }
}
