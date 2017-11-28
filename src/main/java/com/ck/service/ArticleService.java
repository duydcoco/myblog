package com.ck.service;

import com.ck.entity.Contents;
import com.ck.utils.PageEntity;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ArticleService {

   PageEntity<Contents> findPage(Pageable pageable);

   Optional<Contents> getContents(String cid);
}
