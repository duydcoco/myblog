package com.ck.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 文章内容
 * Created by dudycoco on 17-11-17.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name="t_contents")
public class Contents implements Serializable{

    @Id
    @Column(columnDefinition = "BigInt(20)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;

    @NotEmpty(message = "标题不能为空")
    @Length(max = 200, message = "文章标题最多可以输入%d个字符")
    @Column(name = "title"
            ,columnDefinition = "varchar(25) comment '内容标题'")
    private String  title;

    @Column(name = "slug"
            ,columnDefinition = "varchar(25) comment '内容缩略名'")
    private String  slug;

    @Column(name = "created"
            ,columnDefinition = "BingInt(25) comment '内容生成时的GMT unix时间戳'")
    private Long created;

    @Column(name = "modified"
            ,columnDefinition = "BingInt(25) comment '内容更改时的GMT unix时间戳'")
    private Long modified;

    @NotEmpty(message = "内容不能为空")
    @Length(max = 10000, message = "文章内容最多可以输入%d个字符")
    @Column(name = "content"
            ,columnDefinition = "longtext(10000) comment '内容文字'")
    private String  content;

    @ManyToOne
    @JoinColumn(name = "author_id"
            ,columnDefinition = "BigInt(20) comment '内容所属用户id'")
    private User user;

    @Column(name = "hits"
            ,columnDefinition = "Int(20) comment '点击次数'")
    private Integer hits;

    @Column(name = "type"
            ,columnDefinition = "varchar(25) comment '内容类别'")
    private String  type;

    @Column(name = "fmt_type"
            ,columnDefinition = "varchar(25) comment '内容类型，markdown或者html'")
    private String  fmtType;

    @Column(name = "thumb_img"
            ,columnDefinition = "varchar(100) comment '文章缩略图'")
    private String  thumbImg;

    @Column(name = "tags"
            ,columnDefinition = "varchar(25) comment '标签列表'")
    private String  tags;

    @Column(name = "categories"
            ,columnDefinition = "varchar(25) comment '分类列表'")
    private String  categories;

    @Column(name = "status"
            ,columnDefinition = "varchar(25) comment '内容状态'")
    private String  status;

    @Column(name = "comments_num"
            ,columnDefinition = "varchar(25) comment '内容所属评论数'")
    private Integer commentsNum;

    @Column(name = "allow_comment"
            ,columnDefinition = "tinyint(1) comment '是否允许评论'")
    private Boolean allowComment;

    @Column(name = "allow_ping"
            ,columnDefinition = "tinyint(1) comment '是否允许ping'")
    private Boolean allowPing;

    @Column(name = "allow_feed"
            ,columnDefinition = "tinyint(1) comment '允许出现在聚合中'")
    private Boolean allowFeed;
}
