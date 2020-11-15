package com.xxl.job.tojpa;

import com.xxl.job.admin.XxlJobAdminApplication;
import com.xxl.job.admin.dao.XxlJobInfoDao;
import com.xxl.job.admin.dao.XxlJobLogDao;
import com.xxl.job.admin.jpaCode.jpaDao.JpaXxlJobInfoDao;
import com.xxl.job.admin.jpaCode.jpaDao.JpaXxlJobLogDao;
import com.xxl.job.admin.jpaCode.jpaServer.JpaXxlJobLogServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by YBM on 2020/11/15 23:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= XxlJobAdminApplication.class)
public class XxlJobLogDaoToJpaTest {

    @Resource
    XxlJobLogDao apiDao;
    @Autowired
    JpaXxlJobLogServer dao;

    @Test
    public void pageList(){
        System.out.println(apiDao.pageList(0, 5, 1, 0, null, null, 2));
        System.out.println(dao.pageList(1, 5, 0, 0, null, null, 2));
    }

}
