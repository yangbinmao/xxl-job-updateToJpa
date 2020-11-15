package com.xxl.job.admin.jpaCode.jpaDao;

import com.xxl.job.admin.jpaCode.model.XxlJobLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by YBM on 2020/11/15 22:46
 */
public interface JpaXxlJobLogDao extends JpaRepository<XxlJobLogEntity,Long>, JpaSpecificationExecutor<XxlJobLogEntity> {
}
