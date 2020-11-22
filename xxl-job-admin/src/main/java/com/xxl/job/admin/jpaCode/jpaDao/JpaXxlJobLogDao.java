package com.xxl.job.admin.jpaCode.jpaDao;

import com.xxl.job.admin.core.model.XxlJobLog;
import com.xxl.job.admin.jpaCode.model.XxlJobLogEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YBM on 2020/11/15 22:46
 */
public interface JpaXxlJobLogDao extends JpaRepository<XxlJobLogEntity,Long>, JpaSpecificationExecutor<XxlJobLogEntity> {

    @Query("select x from XxlJobLogEntity  x where x.id=?1")
    public XxlJobLogEntity load(@Param("id") long id);

    @Transactional
    @Modifying
    @Query("update XxlJobLogEntity x set x.handleTime=:#{#XxlJobLogEntity.handleTime},x.handleCode=:#{#XxlJobLogEntity.handleCode},x.handleMsg=:#{#XxlJobLogEntity.handleMsg} where x.id=:#{#XxlJobLogEntity.id}")
    public int updateTriggerInfo(@Param("XxlJobLogEntity") XxlJobLogEntity entity);

    @Transactional
    @Modifying
    @Query("delete from XxlJobLogEntity x where x.id=?1")
    public int delete(@Param("id")long jobId);

    @Query("select count(x.triggerCode) as triggerDayCount  ," +
            "sum (case when(x.triggerCode in (0 ,200) and x.handleCode=0 )then 1 else 0 end ) as triggerDayCountRunning ," +
            "sum (case when  x.handleCode=200 then 1 else 0 end ) as triggerDayCountSuc  " +
            "from  XxlJobLogEntity  x where x.triggerTime between ?1 and  ?2 ")
    public Map<String,Object> findLogReport(Date from , Date to);

    @Transactional
    @Modifying
    @Query("delete from XxlJobLogEntity x where x.id in ?1")
    public int clearLog(List<Long> logIds);

    @Query("select x.id from XxlJobLogEntity x where not ((x.triggerCode in (0,200) and x.handleCode=0 ) or (x.handleCode=200)) and x.alarmStatus=0 order by x.id asc ")
    public List<Long> findFailJobLogIds( Pageable pageable);

    @Transactional
    @Modifying
    @Query("update XxlJobLogEntity x set x.alarmStatus=?3 where x.id=?1 and x.alarmStatus=?2 ")
    public int updateAlarmStatus(long logId,int oldAlarmStatus,int newAlarmStatus);

    @Query("select x.id from XxlJobLogEntity x where x.triggerCode = 200 and x.handleCode=0 and x.triggerTime <= ?1 and x.executorAddress not in (select r.registryValue from XxlJobRegistryEntity r)")
    public List<Long> findLostJobIds(Date losedTime);

}
