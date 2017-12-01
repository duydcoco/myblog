package com.ck.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentParam {

    private String content;
    private Long contentsId;
    private Long parentId = 0L;
    private String author;
    private String mail;
}
