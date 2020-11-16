package com.xxl.job.admin.jpaCode.jpaDao;

import com.xxl.job.admin.core.model.XxlJobLog;
import com.xxl.job.admin.jpaCode.model.XxlJobLogEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

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
}
