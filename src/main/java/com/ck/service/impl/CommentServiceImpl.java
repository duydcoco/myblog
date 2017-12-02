package com.ck.service.impl;

import com.ck.vo.CommentVo;
import com.ck.dto.CommentParam;
import com.ck.entity.Comments;
import com.ck.entity.Contents;
import com.ck.repository.CommentRepository;
import com.ck.repository.ContentsRepository;
import com.ck.service.CommentService;
import com.ck.utils.PageEntity;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.ck.utils.ValidateUtils.NOT_NULL;
import static com.ck.utils.ValidateUtils.validate;


public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ContentsRepository contentsRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveComment(CommentParam commentParam) {
        validate(commentParam.getContent(),NOT_NULL,"评论内容不能为空");
        validate(commentParam.getContent().length()<5||commentParam.getContent().length()>2000,"评论字数在5-2000个字符");
        validate(commentParam.getContentsId()==null,"文章不能为空");
        validate(contentsRepository.findOne(commentParam.getContentsId())==null,"文章不存在");
        Comments comments = new Comments();
        comments.setContentsId(commentParam.getContentsId());
        comments.setCreated(DateTime.now().getMillis());
        comments.setAuthor(commentParam.getAuthor());
        comments.setMail(commentParam.getMail());
        comments.setParentId(commentParam.getParentId());
        commentRepository.save(comments);
        Contents contents = contentsRepository.findOne(commentParam.getContentsId());
        contents.setCommentsNum(contents.getCommentsNum()+1);
        commentRepository.save(comments);
    }

    @Override
    public PageEntity<CommentVo> findPage(Long contentId, Pageable pageable) {
        validate(contentId,NOT_NULL,"文章不能为空");
        //获取一级评论
        Page<Comments> page = commentRepository.findAllByParentIdAndContentsId(0L,contentId,pageable);
        //重新构造vo看有无回复
        List<CommentVo> commentVoList = page
                .getContent()
                .stream()
                .map(comments -> {
                    int count = commentRepository.countByParentId(comments.getCoid());
                    Boolean isHasChrildren = count>0?Boolean.TRUE:Boolean.FALSE;
                    CommentVo commentVo = CommentVo
                            .builder()
                            .parent(comments)
                            .isHasChidren(isHasChrildren)
                            .build();
                    return commentVo;
                }).collect(Collectors.toList());
        PageEntity<CommentVo> pageEntity = new PageEntity<CommentVo>();
        pageEntity.setPageNumber(page.getNumber());
        pageEntity.setPageSize(page.getSize());
        pageEntity.setTotal(page.getTotalElements());
        pageEntity.setList(commentVoList);
        pageEntity.setTotal(page.getTotalElements());
        return pageEntity;
    }

    @Override
    public PageEntity<Comments> getDetail(Long parentId,Long contentsId,Pageable pageable) {
       validate(parentId,NOT_NULL,"上级评论不能为空");
       Page<Comments> page = commentRepository.findAllByParentIdAndContentsId(parentId,contentsId,pageable);
       PageEntity<Comments> pageEntity = new PageEntity<Comments>();
       pageEntity.setTotal(page.getTotalElements());
       pageEntity.setList(page.getContent());
       pageEntity.setPageSize(page.getSize());
       pageEntity.setPageNumber(page.getNumber());
       return pageEntity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long comentsId,Long contentsId) {
        validate(comentsId,NOT_NULL,"评论id不能为空");
        validate(contentsId,NOT_NULL,"文章id不能为空");
        commentRepository.delete(comentsId);
        Contents contents = contentsRepository.findOne(contentsId);
        validate(contents,NOT_NULL,"此文章不存在");
        if(Objects.nonNull(contents.getCommentsNum())&&contents.getCommentsNum()>0){
            contents.setCommentsNum(contents.getCommentsNum()-1);
            contentsRepository.save(contents);
        }
    }


}
