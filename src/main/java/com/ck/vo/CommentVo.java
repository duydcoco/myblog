package com.ck.vo;

import com.ck.entity.Comments;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class CommentVo implements Serializable{

    private Comments parent;
    private Boolean isHasChidren;
}
