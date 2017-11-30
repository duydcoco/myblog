package com.ck.repository;

import com.ck.entity.Contents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentsRepository extends JpaRepository<Contents,Long> {

    Page<Contents> findAllByType(String type, Pageable pageable);

    Contents findBySlug(String slug);

    int countByTypeAndSlug(String type,String slug);
}
