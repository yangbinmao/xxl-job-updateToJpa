package com.xxl.job.tojpa;

import com.xxl.job.admin.XxlJobAdminApplication;
import com.xxl.job.admin.core.model.XxlJobLog;

import com.xxl.job.admin.dao.XxlJobLogDao;

import com.xxl.job.admin.jpaCode.jpaServer.JpaXxlJobLogServer;
import com.xxl.job.admin.jpaCode.model.XxlJobLogEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Id;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

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
        System.out.println(dao.pageList(0, 5, 1, 0, null, null, 2));
    }

    @Test
    public void pageListCount(){
        System.out.println(apiDao.pageListCount(0, 5, 1, 0, null, null, 2));
        System.out.println(dao.pageListCount(0, 5, 1, 0, null, null, 2));
    }


    @Test
    public void load(){

        System.out.println(apiDao.load(1));
        System.out.println(dao.load(1));
    }

    @Test
    public void updateTriggerInfo(){
        XxlJobLog xxlJobLog = new XxlJobLog();
        xxlJobLog.setHandleTime(new Date());
        xxlJobLog.setHandleCode(1);
        xxlJobLog.setHandleMsg("111");
        xxlJobLog.setId(1l);
        System.out.println(apiDao.updateTriggerInfo(xxlJobLog));
        XxlJobLogEntity xxlJobLogEntity = new XxlJobLogEntity();
        xxlJobLogEntity.setHandleTime(new Date());
        xxlJobLogEntity.setHandleCode(2);
        xxlJobLogEntity.setHandleMsg("222");
        xxlJobLogEntity.setId(1l);
        System.out.println(dao.updateTriggerInfo(xxlJobLogEntity));
    }

    @Test
    public void delete(){

        System.out.println(apiDao.delete(2));

        System.out.println(dao.delete(3));
    }
}
