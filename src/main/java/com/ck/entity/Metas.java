package com.ck.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 项目
 * Created by dudycoco on 17-11-17.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "t_metas")
public class Metas implements Serializable{

    @Id
    @Column(name = "mid",columnDefinition = "BigInt(20)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;

    @Column(name = "name"
            ,columnDefinition = "varchar(25) comment '名称'")
    private String  name;

    @Column(name = "slug"
            ,columnDefinition = "varchar(25) comment '项目缩略名'")
    private String  slug;

    @Column(name = "type"
            ,columnDefinition = "varchar(25) comment '项目类型'")
    private String  type;

    @Column(name = "description"
            ,columnDefinition = "varchar(25) comment '选项描述'")
    private String  description;

    @Column(name = "sort"
            ,columnDefinition = "Int(20) comment '项目排序'")
    private Integer sort;

    @ManyToOne
    @JoinColumn(name = "parent"
            ,columnDefinition = "BigInt(20) comment '父级'")
    private Metas parent;

    @Column(name = "count"
            ,columnDefinition = "Int(20) comment '文章数'")
    private Integer count;
}
