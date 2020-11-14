package com.xxl.job.admin.jpaCode.model;


import lombok.Data;

import javax.persistence.*;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xuxueli on 16/9/30.
 */

@Table(name="xxl_job_group")
@Entity
@Data
public class XxlJobGroupEntity {
    @Id    //主键id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //主键生成策略
    @Column(name="id")
    private int id;

    @Column(name="app_name")
    private String appname;

    @Column(name="title")
    private String title;

    @Column(name="address_type")
    private int addressType;        // 执行器地址类型：0=自动注册、1=手动录入

    @Column(name="address_list")
    private String addressList;     // 执行器地址列表，多地址逗号分隔(手动录入)
    // registry list
    @Transient
    private List<String> registryList;  // 执行器地址列表(系统注册)


    public List<String> getRegistryList() {
        if (addressList!=null && addressList.trim().length()>0) {
            registryList = new ArrayList<String>(Arrays.asList(addressList.split(",")));
        }
        return registryList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAddressType() {
        return addressType;
    }

    public void setAddressType(int addressType) {
        this.addressType = addressType;
    }

    public String getAddressList() {
        return addressList;
    }

    public void setAddressList(String addressList) {
        this.addressList = addressList;
    }

    @Override
    public String toString() {
        return "XxlJobGroupEntity{" +
                "id=" + id +
                ", appname='" + appname + '\'' +
                ", title='" + title + '\'' +
                ", addressType=" + addressType +
                ", addressList='" + addressList + '\'' +
                ", registryList=" + registryList +
                '}';
    }
}
