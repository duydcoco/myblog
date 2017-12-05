package com.ck.repository;

import com.ck.entity.Options;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Options,Long>{

    Options findByOptionName(String key);
}
