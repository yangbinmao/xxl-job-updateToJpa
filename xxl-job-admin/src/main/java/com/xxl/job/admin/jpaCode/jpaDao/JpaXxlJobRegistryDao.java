package com.xxl.job.admin.jpaCode.jpaDao;

import com.xxl.job.admin.core.model.XxlJobRegistry;
import com.xxl.job.admin.jpaCode.model.XxlJobLogReportEntity;
import com.xxl.job.admin.jpaCode.model.XxlJobRegistryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by xuxueli on 16/9/30.
 */
public interface JpaXxlJobRegistryDao extends JpaRepository<XxlJobRegistryEntity, Integer>, JpaSpecificationExecutor<XxlJobRegistryEntity> {


    @Transactional
    @Modifying
    @Query("delete from XxlJobRegistryEntity x where x.id in ?1")
    public int removeDead(List<Integer> ids);


    @Transactional
    @Modifying
    @Query("update XxlJobRegistryEntity x set x.updateTime=?4 where x.registryGroup=?1 and x.registryKey=?2 and x.registryValue=?3")
    public int registryUpdate( String registryGroup,String registryKey, String registryValue, Date updateTime);


    @Transactional
    @Modifying
    @Query("delete from XxlJobRegistryEntity x where x.registryGroup=?1 and x.registryKey=?2 and x.registryValue=?3")
    public int registryDelete(@Param("registryGroup") String registryGroup,
                              @Param("registryKey") String registryKey,
                              @Param("registryValue") String registryValue);

}
