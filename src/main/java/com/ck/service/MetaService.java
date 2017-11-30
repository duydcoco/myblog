package com.ck.service;

import com.ck.entity.Contents;
import com.ck.entity.Metas;
import com.ck.entity.Options;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by dudycoco on 17-11-28.
 */
public interface MetaService {

    /**
     * 根据类型查询 meta
     * @param type tag or category
     * @return
     */
    List<Metas> getMetas(String type);

    Map<String, List<Contents>> getMetaMapping(String type);

    Optional<Metas> getMetasByTypeAndName(String type, String name);

    void deleteByMid(Long mid);

    void saveMeta(String name,String type);
}
