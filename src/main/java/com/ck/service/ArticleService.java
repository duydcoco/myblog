package com.ck.service;

import com.ck.entity.Contents;
import com.ck.utils.PageEntity;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ArticleService {

   /**
    * 文章列表
    * @param pageable
    * @return
    */
   PageEntity<Contents> findPage(Pageable pageable);

   /**
    * 某一篇文章
    * @param cid
    * @return
    */
   Optional<Contents> getContents(String cid);

   /**
    * 发布文章
    * @param contents
    * @return
    */
   Contents publish(Contents contents);

   /**
    * 更新文章
    * @param contents
    */
   void updateArticle(Contents contents);

   /**
    * 删除文章
    * @param cid
    */
   void delete(Long cid);

   /**
    * 文章通过标签或者目录归类
    * @param mid
    * @param pageable
    * @return
    */
   PageEntity<Contents> findPageByMid(Long mid,Pageable pageable);
}
