package com.ck.repository;

import com.ck.entity.RelationShips;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelationShipRespository extends JpaRepository<RelationShips,Long>{

    List<RelationShips> findAllByMid(Long mid);

    int countByMidAndCid(Long mid,Long cid);
}
