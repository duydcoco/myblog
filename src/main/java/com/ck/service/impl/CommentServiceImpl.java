package com.ck.service.impl;

import com.ck.dto.CommentParam;
import com.ck.entity.Comments;
import com.ck.entity.Contents;
import com.ck.repository.CommentRepository;
import com.ck.repository.ContentsRepository;
import com.ck.service.CommentService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
        comments.setContents(Contents.builder().cid(commentParam.getContentsId()).build());
        comments.setCreated(DateTime.now().getMillis());
        comments.setAuthor(commentParam.getAuthor());
        comments.setMail(commentParam.getMail());
        comments.setParentComments(Comments.builder().coid(commentParam.getParentId()).build());
        commentRepository.save(comments);
        Contents contents = comments.getContents();
        contents.setCommentsNum(contents.getCommentsNum()+1);
        commentRepository.save(comments);
    }
}
