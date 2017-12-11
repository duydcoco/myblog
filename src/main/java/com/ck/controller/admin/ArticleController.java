package com.ck.controller.admin;

import com.ck.entity.Contents;
import com.ck.service.ArticleService;
import com.ck.utils.PageEntity;
import com.ck.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/article")
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public Result<PageEntity<Contents>> list(Pageable pageable){
        PageEntity<Contents> pageEntity = articleService.findPage(pageable);
        return Result.ok(pageEntity);
    }

    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    public Result<String> add(Contents contents){
        articleService.publish(contents);
        return Result.ok("文章保存成功");
    }

    @RequestMapping(value = "/{cid}",method = RequestMethod.GET)
    public Result<Contents> getContents(@PathVariable("cid") String cid){
        Contents contents = articleService.getContents(cid).orElse(null);
        return Result.ok(contents);
    }



}
