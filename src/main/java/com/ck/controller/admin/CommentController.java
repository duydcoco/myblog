package com.ck.controller.admin;

import com.ck.entity.Comments;
import com.ck.service.CommentService;
import com.ck.utils.PageEntity;
import com.ck.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/comment")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public Result<PageEntity<Comments>> list(Pageable pageable){

    }
}
