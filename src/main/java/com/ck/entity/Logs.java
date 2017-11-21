package com.ck.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 日志
 * Created by dudycoco on 17-11-17.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "t_logs")
public class Logs implements Serializable{

    @Id
    @Column(name = "id",columnDefinition = "BigInt(20)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "action"
            ,columnDefinition = "varchar(25) comment '产生的动作'")
    private String action;

    @Column(name = "data"
            ,columnDefinition = "varchar(25) comment '产生的数据'")
    private String data;

    @ManyToOne
    @JoinColumn(name = "author_id"
            ,columnDefinition = "BigInt(20) comment '发生人id'")
    private User author;

    @Column(name = "ip"
            ,columnDefinition = "varchar(25) comment '日志产生的ip'")
    private String ip;

    @Column(name = "created"
            ,columnDefinition = "BigInte(20) comment '日志创建时间'")
    private Long created;
}
