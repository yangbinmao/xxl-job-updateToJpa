package com.xxl.job.tojpa;

import com.xxl.job.admin.XxlJobAdminApplication;
import com.xxl.job.admin.core.model.XxlJobLog;

import com.xxl.job.admin.dao.XxlJobLogDao;

import com.xxl.job.admin.jpaCode.jpaDao.JpaXxlJobLogDao;
import com.xxl.job.admin.jpaCode.jpaServer.JpaXxlJobLogServer;
import com.xxl.job.admin.jpaCode.model.XxlJobLogEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Id;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.xml.ws.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @Autowired
    JpaXxlJobLogDao daoS;
    @Test
    public void findLogReport() throws ParseException {
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date from =sdf.parse("2020-10-21 23:59:51");
        Date to =sdf.parse("2020-11-21 23:59:51");
        System.out.println(apiDao.findLogReport(from, to));
        Map<String, Object> logReport = dao.findLogReport(from, to);
        System.out.println(dao.findLogReport(from, to));
        System.out.println(logReport);
        System.out.println(logReport.get("triggerDayCount"));

    }
    @Test
    public void  findClearLogIds(){
        System.out.println(apiDao.findClearLogIds(0, 0, null, 1, 10));

        System.out.println(dao.findClearLogIds(0, 0, null, 1, 10));
    }

    @Test
    public void clearLog(){
        List<Long> longs = new ArrayList<>();
        longs.add(3l);
        longs.add(4l);
        System.out.println(dao.clearLog(longs));
        List<Long> longs1 = new ArrayList<>();

        longs1.add(6l);
        longs1.add(5l);
        System.out.println(apiDao.clearLog(longs1));

    }
}
