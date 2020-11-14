package com.xxl.job.admin.jpaCode.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * xxl-job info
 *
 * @author xuxueli  2016-1-12 18:25:49
 */
@Data
@Table(name="xxl_job_info")
@Entity
public class XxlJobInfoEntity {
	@Id    //主键id
	@GeneratedValue(strategy= GenerationType.IDENTITY) //主键生成策略
	@Column(name="id")
	private int id;				// 主键ID
	@Column(name="job_group")
	private int jobGroup;		// 执行器主键ID
	@Column(name="job_cron")
	private String jobCron;		// 任务执行CRON表达式
	@Column(name="job_desc")
	private String jobDesc;
	@Column(name="add_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date addTime;
	@Column(name="update_time")
	@Temporal(TemporalType.TIMESTAMP)
//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	@Column(name="author")
	private String author;		// 负责人
	@Column(name="alarm_email")
	private String alarmEmail;	// 报警邮件
	@Column(name="executor_route_strategy")
	private String executorRouteStrategy;	// 执行器路由策略
	@Column(name="executor_handler")
	private String executorHandler;		    // 执行器，任务Handler名称
	@Column(name="executor_param")
	private String executorParam;		    // 执行器，任务参数
	@Column(name="executor_block_strategy")
	private String executorBlockStrategy;	// 阻塞处理策略
	@Column(name="executor_timeout")
	private int executorTimeout;     		// 任务执行超时时间，单位秒
	@Column(name="executor_fail_retry_count")
	private int executorFailRetryCount;		// 失败重试次数

	@Column(name="glue_type")
	private String glueType;		// GLUE类型	#com.xxl.job.core.glue.GlueTypeEnum
	@Column(name="glue_source")
	private String glueSource;		// GLUE源代码
	@Column(name="glue_remark")
	private String glueRemark;		// GLUE备注
	@Column(name="glue_updatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date glueUpdatetime;	// GLUE更新时间

	@Column(name="child_jobid")
	private String childJobId;		// 子任务ID，多个逗号分隔
	@Column(name="trigger_status")
	private int triggerStatus;		// 调度状态：0-停止，1-运行
	@Column(name="trigger_last_time")
	private long triggerLastTime;	// 上次调度时间
	@Column(name="trigger_next_time")
	private long triggerNextTime;	// 下次调度时间

	public long getTriggerLastTime() {
		return triggerLastTime;
	}

	public void setTriggerLastTime(long triggerLastTime) {
		this.triggerLastTime = triggerLastTime;
	}

}
