package com.xxl.job.admin.jpaCode.jpaDao;


import com.xxl.job.admin.jpaCode.model.XxlJobInfoEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

import java.util.List;

/**
 * Created by YBM on 2020/11/3 22:31
 */
public interface JpaXxlJobInfoDao extends JpaRepository<XxlJobInfoEntity,Integer>, JpaSpecificationExecutor<XxlJobInfoEntity> {

    @Query("select x from XxlJobInfoEntity x where x.id=?1")
    public XxlJobInfoEntity loadById(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("delete from XxlJobInfoEntity x where x.id=?1")
    public int delete(@Param("id") int id);

    @Query("select x from XxlJobInfoEntity x where x.jobGroup = ?1 ")
    public List<XxlJobInfoEntity> getJobsByGroup(@Param("jobGroup") int jobGroup);

    @Query("select count(x) from  XxlJobInfoEntity x" )
    public int findAllCount();

    @Query("select x from XxlJobInfoEntity x where x.triggerStatus=1 and x.triggerNextTime <= ?1 order by x.id asc ")
    public List<XxlJobInfoEntity> scheduleJobQuery(@Param("maxNextTime") long maxNextTime, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update XxlJobInfoEntity x set x.triggerLastTime=:#{#xxlJobInfoEntity.triggerLastTime},x.triggerNextTime=:#{#xxlJobInfoEntity.triggerNextTime},x.triggerStatus=:#{#xxlJobInfoEntity.triggerStatus} where x.id=:#{#xxlJobInfoEntity.id}")
    public int scheduleUpdate(@Param("xxlJobInfoEntity")XxlJobInfoEntity xxlJobInfoEntity);

}
