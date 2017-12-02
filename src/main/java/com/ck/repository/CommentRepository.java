package com.ck.repository;

import com.ck.entity.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments,Long>{

    Page<Comments> findAllByParentIdAndContentsId(Long parentId,Long contentsId,Pageable pageable);

    int countByParentId(Long parentId);

}
