package com.xxl.job.tojpa;

import com.xxl.job.admin.XxlJobAdminApplication;
import com.xxl.job.admin.core.model.XxlJobLogReport;
import com.xxl.job.admin.dao.XxlJobLogReportDao;
import com.xxl.job.admin.jpaCode.jpaServer.JpaXxlJobLogReportServer;
import com.xxl.job.admin.jpaCode.model.XxlJobLogReportEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by YBM on 2020/11/23 22:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= XxlJobAdminApplication.class)
public class JobLogReportToJpaTest {

    @Autowired
    JpaXxlJobLogReportServer dao;

    @Resource
    XxlJobLogReportDao apidao;

    @Test
    public void queryLogReportTotal(){
        XxlJobLogReport xxlJobLogReport = apidao.queryLogReportTotal();
        System.out.println(xxlJobLogReport);
        XxlJobLogReportEntity xxlJobLogReportEntity = dao.queryLogReportTotal();
        System.out.println(xxlJobLogReportEntity);
    }
}
