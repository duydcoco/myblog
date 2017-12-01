package com.ck.service;


import com.ck.dto.CommentParam;
import com.ck.entity.Comments;

public interface CommentService {

    void saveComment(CommentParam commentParam);

}
