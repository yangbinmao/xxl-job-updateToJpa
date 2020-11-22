package com.xxl.job.admin.jpaCode.jpaDao;


import com.xxl.job.admin.jpaCode.model.XxlJobUserEntity;
import org.apache.ibatis.annotations.Mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author xuxueli 2019-05-04 16:44:59
 */
public interface JpaXxlJobUserDao extends JpaRepository<XxlJobUserEntity, Integer>, JpaSpecificationExecutor<XxlJobUserEntity> {


	@Query("select  x from XxlJobUserEntity x where x.username=?1")
	public XxlJobUserEntity loadByUserName(String username);


	@Transactional
	@Modifying
	@Query("update XxlJobUserEntity x set x.password =case when :#{#XxlJobUserEntity.password} IS NULL THEN x.password ELSE :#{#XxlJobUserEntity.password} END,x.role=:#{#XxlJobUserEntity.role},x.permission=:#{#XxlJobUserEntity.permission} where x.id=:#{#XxlJobUserEntity.id} ")
	public int update(@Param("XxlJobUserEntity")XxlJobUserEntity xxlJobUser);


	@Transactional
	@Modifying
	@Query("delete from XxlJobUserEntity x where x.id= ?1 ")
	public int delete( int id);

}
