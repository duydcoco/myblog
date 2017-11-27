package com.ck.service.impl;

import com.ck.entity.Attach;
import com.ck.repository.AttachRepository;
import com.ck.service.AttachService;
import com.ck.utils.PageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.ck.utils.ValidateUtils.*;

@Service
@Slf4j
public class AttachServiceImpl implements AttachService{

    @Autowired
    private AttachRepository attachRepository;

    @Override
    public PageEntity<Attach> findAll(Pageable pageable) {
        Page<Attach> page = attachRepository.findAll(pageable);
        PageEntity pageEntity = new PageEntity();
        pageEntity.setPageSize(page.getSize());
        pageEntity.setPageNumber(page.getNumber());
        pageEntity.setList(page.getContent());
        pageEntity.setTotal(page.getTotalElements());
        return pageEntity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Attach saveAttach(Attach attach) {
        validate(attach,NOT_NULL,"参数不能为空");
        attachRepository.save(attach);
        return attach;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Attach> batctInsertAttach(List<Attach> attachList) {
        validate(attachList,NOT_EMPTY,"参数不能为空");
        attachRepository.save(attachList);
        return attachList;
    }

    @Override
    public void deleteAttachById(Long id) {
        validate(id,NOT_NULL,"id为"+id+"的图片找不到");
        attachRepository.delete(id);
    }

}
