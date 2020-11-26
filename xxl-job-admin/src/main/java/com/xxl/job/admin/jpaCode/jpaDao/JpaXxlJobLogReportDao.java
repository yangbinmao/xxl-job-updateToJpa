package com.xxl.job.admin.jpaCode.jpaDao;


import com.xxl.job.admin.jpaCode.model.XxlJobLogReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * job log
 * @author xuxueli 2019-11-22
 */

public interface JpaXxlJobLogReportDao extends JpaRepository<XxlJobLogReportEntity,Integer>, JpaSpecificationExecutor<XxlJobLogReportEntity> {

	@Transactional
	@Modifying
	@Query("update XxlJobLogReportEntity x set " +
			"x.runningCount=:#{#xxlJobLogReport.runningCount}" +
			",x.sucCount=:#{#xxlJobLogReport.sucCount}" +
			",x.failCount=:#{#xxlJobLogReport.failCount} " +
			"where x.triggerDay=:#{#xxlJobLogReport.triggerDay}")
	public int update(@Param("xxlJobLogReport")XxlJobLogReportEntity xxlJobLogReport);

	@Query("select x from XxlJobLogReportEntity x where x.triggerDay between ?1 and ?2 order by x.triggerDay asc ")
	public List<XxlJobLogReportEntity> queryLogReport(Date triggerDayFrom, Date triggerDayTo);

	@Query("select sum(x.runningCount) as runningCount , sum(x.sucCount) as sucCount ,sum(x.failCount) as failCount from XxlJobLogReportEntity x ")
	public Map<String,Integer> queryLogReportTotal();

}
