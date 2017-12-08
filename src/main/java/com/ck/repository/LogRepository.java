package com.ck.repository;

import com.ck.entity.Logs;
import com.ck.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Logs,Long>{

    Page<Logs> findAllByAuthorOrderByCreated(User user, Pageable pageable);
}
