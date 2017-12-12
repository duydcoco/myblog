package com.ck.service.impl;

import com.ck.vo.CommentVo;
import com.ck.dto.CommentParam;
import com.ck.entity.Comments;
import com.ck.entity.Contents;
import com.ck.repository.CommentRepository;
import com.ck.repository.ContentsRepository;
import com.ck.service.CommentService;
import com.ck.utils.PageEntity;
import org.apache.commons.collections4.CollectionUtils;
import org.assertj.core.util.Lists;
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
        PageEntity<CommentVo> pageEntity = new PageEntity<CommentVo>();
        List<CommentVo> commentVoList = page
                .getContent()
                .stream()
                .map(item->{
                    CommentVo commentVo = new CommentVo();
                    commentVo.setParent(item);
                    List<Comments> children = Lists.newArrayList();
                    getChildren(children,item.getCoid());
                    commentVo.setChildren(children);
                    return commentVo;
                })
                .collect(Collectors.toList());
        pageEntity.setPageNumber(page.getNumber());
        pageEntity.setPageSize(page.getSize());
        pageEntity.setList(commentVoList);
        return pageEntity;
    }

    private void getChildren(List<Comments> children, Long coid) {
        List<Comments> commentsList = commentRepository.findAllByParentIdOrderByCoidAsc(coid);
        if(CollectionUtils.isNotEmpty(commentsList)){
            children.addAll(commentsList);
            commentsList.forEach(item->{
                getChildren(children,item.getCoid());
            });
        }
    }

    @Override
    public PageEntity<Comments> listPage(Long userId, Pageable pageable) {
        Page<Comments> page = commentRepository.findByAuthor_idNotOrderByCreatedAsc(userId,pageable);
        PageEntity<Comments> pageEntity = new PageEntity<Comments>();
        pageEntity.setList(page.getContent());
        pageEntity.setPageSize(page.getSize());
        pageEntity.setPageNumber(page.getNumber());
        pageEntity.setTotal(page.getTotalElements());
        return pageEntity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long comentsId) {
        validate(comentsId,NOT_NULL,"评论id不能为空");
        Comments comments= commentRepository.findOne(comentsId);
        commentRepository.delete(comentsId);
        Contents contents = contentsRepository.findOne(comments.getContentsId());
        validate(contents,NOT_NULL,"评论所对应文章不存在");
        if(Objects.nonNull(contents.getCommentsNum())&&contents.getCommentsNum()>0){
            contents.setCommentsNum(contents.getCommentsNum()-1);
            contentsRepository.save(contents);
        }
    }


}
