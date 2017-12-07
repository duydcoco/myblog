package com.ck.repository;

import com.ck.entity.Options;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Options,Long>{

    Options findByOptionName(String key);

    List<Options> findByOptionNameLike(String optionName);

    void  deleteByOptionNameLike(String key);
}
