package com.ck.service.impl;

import com.ck.constant.Types;
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
    public void deleteByMid(Long mid) {
        validate(mid,NOT_NULL,"id不能为空");
        Metas metas = metaRepository.findOne(mid);
        validate(metas,NOT_NULL,"找不到id为:"+mid+"的项");
        String name = metas.getName();
        String type = metas.getType();
        metaRepository.delete(mid);
        List<RelationShips> relationShipsList = relationShipRespository.findAllByMid(mid);
        if(!CollectionUtils.isEmpty(relationShipsList)){
            relationShipsList.stream()
                    .map(relationShips -> contentsRepository.findOne(relationShips.getCid()))
                    .filter(Objects::nonNull)
                    .forEach(contents -> {
                        boolean isUpdate = false;
                        if (Types.TAG.equals(type)){
                            isUpdate = true;
                            contents.setTags(resetMetas(name,contents.getTags()));
                        }
                        if(Types.CATEGORY.equals(type)){
                            contents.setCategories(resetMetas(name,contents.getTags()));
                        }
                        if(isUpdate){
                            contentsRepository.saveAndFlush(contents);
                        }
                    });
        }
    }

    @Override
    public void saveMeta(String name, String type) {
        if(!Strings.isNullOrEmpty(name)&&!Strings.isNullOrEmpty(type)){
            Metas metas = metaRepository.findByTypeAndName(type,name);
            validate(!Objects.isNull(metas),"该项已经存在");
            metas.setName(name);
            metas.setType(type);
            metaRepository.save(metas);
        }
    }

    private String resetMetas(String name, String metas) {
        String[] strArray = metas.split(",");
        for(String meta : strArray){
            StringBuilder stringBuilder = new StringBuilder();
            if(!name.equals(meta)){
                stringBuilder.append(",").append(meta);
            }
            if(stringBuilder.length()>0){
                return stringBuilder.substring(1);
            }
        }
        return "";
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
