package com.ck.repository;

import com.ck.entity.Contents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContentsRepository extends JpaRepository<Contents,Long> {
    String findSql = "SELECT c.cid, " +
            "c.title, " +
            "c.slug, " +
            "c.created, " +
            "c.modified, " +
            "c.content, " +
            "c.user, " +
            "c.hits, " +
            "c.type, " +
            "c.fmtType, " +
            "c.thumbImg, " +
            "c.categories, " +
            "c.tags, " +
            "c.status, " +
            "c.commentsNum, " +
            "c.allowComment, " +
            "c.allowPing, " +
            "c.allowFeed " +
            "FROM Contents c, RelationShips r " +
            "WHERE c.cid = r.cid " +
            "AND c.status = 'publish' " +
            "AND c.type = 'post' " +
            "AND r.mid = ?1";

    Page<Contents> findAllByType(String type, Pageable pageable);

    Contents findBySlug(String slug);

    int countByTypeAndSlug(String type,String slug);

    @Query(findSql)
    Page<Contents> findByMid(Long mid,Pageable pageable);

}
