package com.ck.entity;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 评论
 * Created by dudycoco on 17-11-17.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name="t_comments")
public class Comments implements Serializable{

    @Id
    @Column(name = "coid",columnDefinition = "BigInt(20)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long coid;

    @ManyToOne
    @JoinColumn(name = "cid",columnDefinition = "BigInt(20) comment '评论所属文章'")
    private Contents contents;

    @Column(name = "created"
            ,columnDefinition = "BigInt(25) comment '评论生成时的GMT unix时间戳'")
    private Long created;

    @NotEmpty(message = "请输入评论作者")
    @Length(max = 30, message = "姓名过长")
    @Column(name = "author"
            ,columnDefinition = "varchar(25) comment '评论作者'")
    private String author;

    @Column(name = "author_id"
            ,columnDefinition = "BigInt(25) comment '评论所属用户id'")
    private Long author_id;

    @Column(name = "owner_id"
            ,columnDefinition = "BigInt(25) comment '评论所属内容作者id'")
    private Long ownerId;

    @NotEmpty(message = "请输入电子邮箱")
    @Email(message = "请输入正确的邮箱格式")
    @Column(name = "mail"
            ,columnDefinition = "varchar(25) comment '评论者邮件'")
    private String mail;

    @Column(name = "url"
            ,columnDefinition = "varchar(25) comment '评论者网址'")
    private String url;

    @Column(name = "ip"
            ,columnDefinition = "varchar(25) comment '评论者ip地址'")
    private String ip;

    @Column(name = "agent"
            ,columnDefinition = "varchar(25) comment '评论者客户端'")
    private String agent;

    @NotEmpty(message = "请输入评论内容")
    @Length(max = 2000, message = "请输入%d个字符以内的评论")
    @Column(name = "content"
            ,columnDefinition = "text(2000) comment '评论内容'")
    private String content;

    @Column(name = "type"
            ,columnDefinition = "varchar(25) comment '评论类型'")
    private String type;

    @Column(name = "status"
            ,columnDefinition = "varchar(25) comment '评论状态'")
    private String status;

    @Column(name = "parent"
            ,columnDefinition = "BigInt(25) comment '父级评论'")
    private Comments parentComments;
}
