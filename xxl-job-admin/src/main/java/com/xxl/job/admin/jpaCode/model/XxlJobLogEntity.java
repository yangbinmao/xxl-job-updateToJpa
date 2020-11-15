package com.xxl.job.admin.jpaCode.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name="xxl_job_log")
@Entity
public class XxlJobLogEntity {

	@Id    //主键id
	@GeneratedValue(strategy= GenerationType.IDENTITY) //主键生成策略
	@Column(name="id")
	private long id;
	
	// job info
	@Column(name="job_group")
	private int jobGroup;
	@Column(name="job_id")
	private int jobId;

	// execute info
	@Column(name="executor_address")
	private String executorAddress;
	@Column(name="executor_handler")
	private String executorHandler;
	@Column(name="executor_param")
	private String executorParam;
	@Column(name="executor_sharding_param")
	private String executorShardingParam;
	@Column(name="executor_fail_retry_count")
	private int executorFailRetryCount;
	
	// trigger info
	@Column(name="trigger_time")
	private Date triggerTime;
	@Column(name="trigger_code")
	private int triggerCode;
	@Column(name="trigger_msg")
	private String triggerMsg;
	
	// handle info
	@Column(name="handle_time")
	private Date handleTime;
	@Column(name="handle_code")
	private int handleCode;
	@Column(name="handle_msg")
	private String handleMsg;

	// alarm info
	@Column(name="alarm_status")
	private int alarmStatus;



}
