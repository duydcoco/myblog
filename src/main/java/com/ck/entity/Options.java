package com.ck.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 配置
 * Created by dudycoco on 17-11-17.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "t_options")
public class Options implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "option_name"
            ,columnDefinition = "varchar(25) comment '配置名称'")
    private String optionName;

    @Column(name = "value"
            ,columnDefinition = "varchar(25) comment '配置值'")
    private String value;

    @Column(name = "description"
            ,columnDefinition = "varchar(25) comment '配置描述'")
    private String description;
}
