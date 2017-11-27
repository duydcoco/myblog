package com.ck.service;

import com.ck.entity.Contents;
import com.ck.utils.PageEntity;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.parser.Entity;

public interface ArticleService {

   PageEntity<Contents> findPage(Pageable pageable);
}
