package com.ck.service;

import com.ck.entity.Attach;
import com.ck.utils.PageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AttachService {

    PageEntity<Attach> findAll(Pageable pageable);

    Attach saveAttach(Attach attach);

    List<Attach> batctInsertAttach(List<Attach> attachList);

    void deleteAttachById(Long id);
}
