package com.ck.service.impl;

import com.ck.constant.ContentConstant;
import com.ck.constant.Types;
import com.ck.entity.Contents;
import com.ck.entity.Metas;
import com.ck.entity.RelationShips;
import com.ck.repository.ContentsRepository;
import com.ck.repository.MetaRepository;
import com.ck.repository.RelationShipRespository;
import com.ck.service.ArticleService;
import com.ck.utils.PageEntity;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Strings;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

import static com.ck.utils.ValidateUtils.NOT_NULL;
import static com.ck.utils.ValidateUtils.validate;

public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ContentsRepository contentsRepository;
    @Autowired
    private MetaRepository metaRepository;
    @Autowired
    private RelationShipRespository relationShipRespository;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Contents publish(Contents contents) {
        validate(contents,NOT_NULL,"文章为空");
        validate(StringUtils.isBlank(contents.getTitle()),"文章标题不能为空");
        validate(contents.getTitle().length()> ContentConstant.MAX_TITLE_COUNT,"文章标题最多可以输入" + ContentConstant.MAX_TITLE_COUNT + "个字符");
        validate(StringUtils.isBlank(contents.getContent()),"文章内容不能为空");
        validate(contents.getContent().length()>ContentConstant.MAX_TEXT_COUNT,"文章内容最多可以输入" + ContentConstant.MAX_TEXT_COUNT + "个字符");
        validate(Objects.isNull(contents.getUser()),"请登录后发布文章");
        if(StringUtils.isNotBlank(contents.getSlug())){
            validate(contents.getSlug().length()<5,"路径太短了");
            validate("");//校验路径暂留
            validate(contentsRepository.countByTypeAndSlug(contents.getType(),contents.getSlug())>0,"该路径已经存在，请重新输入");
        }
        contents.setContent(EmojiParser.parseToAliases(contents.getContent()));
        contents.setCreated(DateTime.now().getMillis());
        contents.setModified(DateTime.now().getMillis());
        contentsRepository.saveAndFlush(contents);
        saveMetas(contents.getCid(),contents.getTags(),Types.TAG);
        saveMetas(contents.getCid(),contents.getCategories(),Types.CATEGORY);
        return contents;
    }

    private void saveMetas(Long cid, String names, String type) {
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

}
