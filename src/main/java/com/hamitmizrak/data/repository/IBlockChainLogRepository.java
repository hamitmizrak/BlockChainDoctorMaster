package com.hamitmizrak.data.repository;

import com.hamitmizrak.data.entity.BlockChainLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlockChainLogRepository extends JpaRepository<BlockChainLogEntity,Long> {

}

