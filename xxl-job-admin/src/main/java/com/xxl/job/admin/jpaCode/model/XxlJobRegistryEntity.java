package com.xxl.job.admin.jpaCode.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xuxueli on 16/9/30.
 */
@Data
@Table(name="xxl_job_registry")
@Entity
public class XxlJobRegistryEntity {

    @Id    //主键id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //主键生成策略
    @Column(name="id")
    private int id;

    @Column(name ="registry_group")
    private String registryGroup;

    @Column(name ="registry_key")
    private String registryKey;

    @Column(name ="registry_value")
    private String registryValue;

    @Column(name ="update_time")
    private Date updateTime;

}
