package com.ck.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户
 * Created by dudycoco on 17-11-17.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "t_users")
public class User implements Serializable{

    @Id
    @Column(name = "uid",columnDefinition = "BigInt(20)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;

    @Column(name = "username"
            ,columnDefinition = "varchar(25) comment '用户名称'")
    private String username;

    @Column(name = "password"
            ,columnDefinition = "varchar(25) comment '用户密码'")
    private String password;

    @Column(name = "email"
            ,columnDefinition = "varchar(25) comment '用户的邮箱'")
    private String email;

    @Column(name = "home_url"
            ,columnDefinition = "varchar(25) comment '用户的主页'")
    private String homeUrl;

    @Column(name = "screen_name"
            ,columnDefinition = "varchar(25) comment '用户显示的名称'")
    private String screenName;

    @Column(name = "created"
            ,columnDefinition = "BigInt(25) comment '用户注册时的GMT unix时间戳'")
    private Long created;

    @Column(name = "activated"
            ,columnDefinition = "BigInt(25) comment '最后活动时间'")
    private Long activated;

    @Column(name = "logged"
            ,columnDefinition = "BigInt(25) comment '上次登录最后活跃时间'")
    private Long logged;

    @Column(name = "group_name"
            ,columnDefinition = "varchar(25) comment '用户组'")
    private String groupName;

}
