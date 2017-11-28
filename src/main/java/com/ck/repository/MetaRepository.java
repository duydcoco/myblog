package com.ck.repository;

import com.ck.entity.Metas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by dudycoco on 17-11-28.
 */
public interface MetaRepository extends JpaRepository<Metas,Long>{

    List<Metas> findAllByTypeOrderBySortDescMidDesc(String type);
}
