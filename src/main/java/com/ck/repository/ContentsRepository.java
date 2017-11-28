package com.ck.repository;

import com.ck.entity.Contents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentsRepository extends JpaRepository<Contents,Long> {

    Page<Contents> findAllByType(String type, Pageable pageable);

    Contents findBySlug(String slug);
}
