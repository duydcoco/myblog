package com.ck.service.impl;

import com.ck.entity.Contents;
import com.ck.entity.Metas;
import com.ck.entity.RelationShips;
import com.ck.repository.ContentsRepository;
import com.ck.repository.MetaRepository;
import com.ck.repository.RelationShipRespository;
import com.ck.service.MetaService;
import com.google.common.collect.Maps;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by dudycoco on 17-11-28.
 */
public class MetaServiceImpl implements MetaService{

    @Autowired
    private MetaRepository metaRepository;
    @Autowired
    private RelationShipRespository relationShipRespository;
    @Autowired
    private ContentsRepository contentsRepository;

    @Override
    public List<Metas> getMetas(String type) {
        if(!Strings.isNullOrEmpty(type)){
            metaRepository.findAllByTypeOrderBySortDescMidDesc(type);
        }
        return null;
    }

    @Override
    public Map<String, List<Contents>> getMetaMapping(String type) {
        if(!Strings.isNullOrEmpty(type)){
            List<Metas> metasList = getMetas(type);
            if(!CollectionUtils.isEmpty(metasList)){
                return metasList.stream().collect(Collectors.toMap(Metas::getName,this::getMetaContents));
            }
        }
        return Maps.newHashMap();
    }

    private List<Contents>  getMetaContents(Metas metas) {
        Long                mid               =  metas.getMid();
        List<RelationShips> relationShipsList =  relationShipRespository.findAllByMid(mid);
        if(CollectionUtils.isEmpty(relationShipsList)){
            return Lists.newArrayList();
        }
        return relationShipsList
                .stream()
                .map(relationShips -> contentsRepository.getOne(relationShips.getCid()))
                .collect(Collectors.toList());
    }

}
