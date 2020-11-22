package com.xxl.job.admin.jpaCode.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * xxl-job log for glue, used to track job code process
 * @author xuxueli 2016-5-19 17:57:46
 */
@Data
@Table(name="xxl_job_logglue")
@Entity
public class XxlJobLogGlueEntity {

	@Id    //主键id
	@GeneratedValue(strategy= GenerationType.IDENTITY) //主键生成策略
	@Column(name="id")
	private int id;
	@Column(name="job_id")
	private int jobId;				// 任务主键ID
	@Column(name="glue_type")
	private String glueType;		// GLUE类型	#com.xxl.job.core.glue.GlueTypeEnum
	@Column(name="glue_source")
	private String glueSource;
	@Column(name="glue_remark")
	private String glueRemark;
	@Column(name="add_time")
	private Date addTime;
	@Column(name="update_time")
	private Date updateTime;



}
