package com.ck.repository;

import com.ck.entity.Attach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachRepository extends JpaRepository<Attach,Long>{

    Page<Attach> findAll(Pageable pageable);
}
