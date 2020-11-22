package com.xxl.job.admin.jpaCode.jpaDao;

import com.xxl.job.admin.core.model.XxlJobLogGlue;
import com.xxl.job.admin.jpaCode.model.XxlJobLogEntity;
import com.xxl.job.admin.jpaCode.model.XxlJobLogGlueEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * job log for glue
 * @author xuxueli 2016-5-19 18:04:56
 */

public interface JpaXxlJobLogGlueDao extends JpaRepository<XxlJobLogGlueEntity,Integer>, JpaSpecificationExecutor<XxlJobLogGlueEntity> {


	@Query("select x from XxlJobLogGlueEntity  x where x.jobId= ?1 order by x.id desc")
	public List<XxlJobLogGlueEntity> findByJobId( int jobId);


	@Transactional
	@Modifying
	@Query("delete FROM XxlJobLogGlueEntity x where x.id NOT IN ?1 and x.jobId=?2")
	public int removeOld(List<Integer> ids,int jobId);

	@Transactional
	@Modifying
	@Query("delete from XxlJobLogGlueEntity  x where x.jobId=?1")
	public int deleteByJobId(int jobId);
	
}
