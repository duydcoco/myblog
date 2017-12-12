package com.ck.vo;

import com.ck.entity.Comments;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommentVo implements Serializable{

    private Comments parent;
    private List<Comments> children;
}
