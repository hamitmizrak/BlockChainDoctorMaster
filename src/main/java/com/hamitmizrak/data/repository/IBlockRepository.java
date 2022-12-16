package com.hamitmizrak.data.repository;


import com.hamitmizrak.data.entity.BlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBlockRepository extends JpaRepository<BlockEntity,Long> {
    List<BlockEntity> findAllByPatientEntity_Id(Long id);


}

