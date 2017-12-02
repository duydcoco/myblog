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

    PageEntity<CommentVo> findPage(Long contentId, Pageable pageable);

    /**
     * 获取某一评论的回复信息
     * @param parentId
     * @return
     */
    PageEntity<Comments> getDetail(Long parentId, Long conentsId, Pageable pageable);

    void delete(Long comentsId,Long contentsId);
}
