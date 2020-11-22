package com.xxl.job.admin.jpaCode.jpaDao;



import com.xxl.job.admin.core.model.XxlJobGroup;
import com.xxl.job.admin.jpaCode.model.XxlJobGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ybm on 20/10/27.
 */

public interface JpaXxlJobGroupDao extends JpaRepository<XxlJobGroupEntity,Integer>, JpaSpecificationExecutor<XxlJobGroupEntity> {

    @Query("select x from XxlJobGroupEntity x order by  x.appname ,x.title,x.id asc ")
    public List<XxlJobGroupEntity> findAll();

    @Query("select x from XxlJobGroupEntity x WHERE x.addressType = ?1 order by  x.appname ,x.title,x.id asc ")
    public List<XxlJobGroupEntity> findByAddressType(int addressType);


    @Transactional
    @Modifying
    @Query("update XxlJobGroupEntity x set x.appname=:#{#xxlJobGroupEntity.appname},x.title=:#{#xxlJobGroupEntity.title},x.addressType=:#{#xxlJobGroupEntity.addressType},x.addressList=:#{#xxlJobGroupEntity.addressList} WHERE x.id =:#{#xxlJobGroupEntity.id}")
    public int update(@Param("xxlJobGroupEntity") XxlJobGroupEntity xxlJobGroupEntity);

    @Transactional
    @Modifying
    @Query("delete from XxlJobGroupEntity x where x.id=?1")
    public int remove(@Param("id") int id);

    @Query("select x from XxlJobGroupEntity x where x.id=?1")
    public XxlJobGroupEntity load(@Param("id") int id);



}
