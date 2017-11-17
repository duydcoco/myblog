package com.ck.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 附件
 * Created by dudycoco on 17-11-17.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name="t_attach")
public class Attach implements Serializable{

    @Id
    @Column(columnDefinition = "BigInt(20)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fname"
            ,columnDefinition = "varchar(50) comment '文件名称'")
    private String fname;

    @Column(name = "ftype"
            ,columnDefinition = "varchar(25) comment '文件类型'")
    private String ftype;

    @Column(name = "fkey"
            ,columnDefinition = "varchar(25) comment '文件磁盘映射'")
    private String fkey;

    @ManyToOne
    @JoinColumn(name = "author_id",columnDefinition = "BigInt(20) comment '作者id' ")
    private User author;

    @Column(name = "created"
            ,columnDefinition = "BigInt(25) comment '上传此文件时的时间戳'")
    private Long created;
}
