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
    public Result<PageEntity<Comments>> list(Long userId,Pageable pageable){
        PageEntity<Comments> pageEntity = commentService.listPage(userId,pageable);
        return Result.ok(pageEntity);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public Result<String> delete(Long commentId){
        commentService.delete(commentId);
        return Result.ok("删除评论成功");
    }
}
