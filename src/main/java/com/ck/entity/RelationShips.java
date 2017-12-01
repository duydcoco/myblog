package com.ck.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 内容表与项目表的关联
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "t_relationships")
public class RelationShips implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mid"
            ,columnDefinition = "BigInt(25) comment '项目id'")
    private Long mid;

    @Column(name = "cid"
            ,columnDefinition = "BigInt(25) comment '内容id'")
    private Long cid;

}
