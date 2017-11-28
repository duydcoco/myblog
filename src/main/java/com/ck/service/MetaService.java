package com.ck.service;

import com.ck.entity.Contents;
import com.ck.entity.Metas;

import java.util.List;
import java.util.Map;

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


}
