package com.xxl.job.admin.jpaCode.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name="xxl_job_log_report")
@Entity
public class XxlJobLogReportEntity {

    @Id    //主键id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //主键生成策略
    @Column(name="id")
    private int id;

    @Column(name ="trigger_day")
    private Date triggerDay;

    @Column(name="running_count")
    private int runningCount;

    @Column(name="suc_count")
    private int sucCount;

    @Column(name="fail_count")
    private int failCount;


}
