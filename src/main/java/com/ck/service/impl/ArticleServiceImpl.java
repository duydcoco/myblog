package com.ck.service.impl;

import com.ck.constant.Types;
import com.ck.entity.Contents;
import com.ck.repository.ContentsRepository;
import com.ck.service.ArticleService;
import com.ck.utils.PageEntity;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ContentsRepository contentsRepository;

    @Override
    public PageEntity<Contents> findPage(Pageable pageable) {
        Page<Contents> contentsPage = contentsRepository.findAllByType(Types.ARTICLE,pageable);
        PageEntity<Contents> pageEntity = new PageEntity<Contents>();
        pageEntity.setTotal(contentsPage.getTotalElements());
        pageEntity.setPageNumber(contentsPage.getNumber());
        pageEntity.setPageSize(contentsPage.getSize());
        pageEntity.setList(contentsPage.getContent());
        return null;
    }

    @Override
    public Optional<Contents> getContents(String cid) {
        if(!Strings.isNullOrEmpty(cid)){
            if(StringUtils.isNumeric(cid)){
                return Optional.ofNullable(contentsRepository.getOne(Long.valueOf(cid)));
            }else{
                return Optional.ofNullable(contentsRepository.findBySlug(cid));
            }
        }
        return Optional.empty();
    }
}
