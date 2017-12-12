package com.ck.service;


import com.ck.vo.CommentVo;
import com.ck.dto.CommentParam;
import com.ck.entity.Comments;
import com.ck.utils.PageEntity;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public interface CommentService {

    void saveComment(CommentParam commentParam);

    /**
     * 前端获取某篇文章的所有评论及回复
     * @param contentId
     * @param pageable
     * @return
     */
    PageEntity<CommentVo> findPage(Long contentId, Pageable pageable);

    /**
     * 后端的评论管理获取所有非当前作者的评论
     * @param userId
     * @param pageable
     * @return
     */
    PageEntity<Comments> listPage(Long userId,Pageable pageable);

    void delete(Long comentsId);
}
