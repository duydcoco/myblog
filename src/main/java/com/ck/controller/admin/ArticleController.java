package com.ck.controller.admin;

import com.ck.entity.Contents;
import com.ck.service.ArticleService;
import com.ck.utils.PageEntity;
import com.ck.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

}
