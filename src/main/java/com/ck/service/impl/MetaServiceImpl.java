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

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ck.utils.ValidateUtils.NOT_NULL;
import static com.ck.utils.ValidateUtils.validate;

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

    @Override
    public Optional<Metas> getMetasByTypeAndName(String type, String name) {
        Metas metas = null;
        if(!Strings.isNullOrEmpty(type)&&!Strings.isNullOrEmpty(name)){
            metas = metaRepository.findByTypeAndName(type,name);
        }
        return Optional.of(metas);
    }

    @Override
    public void saveMetas(Long cid, String names, String type) {
        validate(cid,NOT_NULL,"内容不能为空");
        if(!Strings.isNullOrEmpty(names)&&Strings.isNullOrEmpty(type)){
            String[] nameArray = names.split(",");
            for(String name:nameArray){
                saveOrUpdate(cid,name,type);
            }
        }
    }

    private void saveOrUpdate(Long cid, String name, String type) {
        Metas metas = metaRepository.findByTypeAndName(type,name);
        Long mid = null;
        if(!Objects.isNull(metas)){
            mid = metas.getMid();
        }else{
            Metas newMetas = Metas.builder()
                    .name(name)
                    .type(type)
                    .description(name)
                    .slug(name)
                    .build();
            mid =metaRepository.save(newMetas).getMid();
        }
        if(!Objects.isNull(mid)){
           int count = relationShipRespository.countByMidAndCid(mid,cid);
           if(count==0){
               RelationShips relationShips = RelationShips.builder()
                       .cid(cid)
                       .mid(mid)
                       .build();
               relationShipRespository.save(relationShips);
           }
        }
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
