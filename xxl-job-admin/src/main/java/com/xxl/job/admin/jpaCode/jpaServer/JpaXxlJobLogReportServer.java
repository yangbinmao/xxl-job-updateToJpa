package com.xxl.job.admin.jpaCode.jpaServer;

import com.xxl.job.admin.jpaCode.jpaDao.JpaXxlJobLogReportDao;
import com.xxl.job.admin.jpaCode.model.XxlJobLogReportEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by YBM on 2020/11/22 20:35
 */
@Service
public class JpaXxlJobLogReportServer {

    @Autowired
    JpaXxlJobLogReportDao dao;

    public int save(XxlJobLogReportEntity entity) {
        return ObjectUtils.isNotEmpty(dao.save(entity)) ? 1 : 0;
    }

    public int update(XxlJobLogReportEntity entity) {
        return dao.update(entity);
    }

    public List<XxlJobLogReportEntity> queryLogReport(Date triggerDayFrom, Date triggerDayTo) {
        return dao.queryLogReport(triggerDayFrom, triggerDayTo);
    }

}
