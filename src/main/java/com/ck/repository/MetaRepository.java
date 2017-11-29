package com.ck.repository;

import com.ck.entity.Metas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Native;
import java.util.List;

/**
 * Created by dudycoco on 17-11-28.
 */
public interface MetaRepository extends JpaRepository<Metas,Long>{

    String findSql = "select " +
            "m.mid as mid," +
            "m.description as description," +
            "m.name as name," +
            "m.slug as slug," +
            "m.sort as sort," +
            "m.type as type," +
            "m.parent as parent," +
            "count(r.cid) as count " +
            "from Metas m,RelationShips r where m.mid = r.mid";

    List<Metas> findAllByTypeOrderBySortDescMidDesc(String type);

    @Query(findSql+" and m.type=?1 and m.name=?2 group by m.mid")
    Metas findByTypeAndName(String type,String name);

}