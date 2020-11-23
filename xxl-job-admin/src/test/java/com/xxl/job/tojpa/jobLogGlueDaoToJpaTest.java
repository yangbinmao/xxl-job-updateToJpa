package com.xxl.job.tojpa;

import com.xxl.job.admin.XxlJobAdminApplication;
import com.xxl.job.admin.core.model.XxlJobLogGlue;
import com.xxl.job.admin.dao.XxlJobLogGlueDao;
import com.xxl.job.admin.jpaCode.jpaServer.JpaXxlJobLogGlueServer;
import com.xxl.job.admin.jpaCode.model.XxlJobLogGlueEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by YBM on 2020/11/22 19:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= XxlJobAdminApplication.class)
public class jobLogGlueDaoToJpaTest {

    @Resource
    XxlJobLogGlueDao apiDao;
    @Autowired
    JpaXxlJobLogGlueServer dao;

    @Test
    public void save(){
        XxlJobLogGlueEntity xxlJobLogGlueEntity = new XxlJobLogGlueEntity();
        System.out.println(dao.save(xxlJobLogGlueEntity));
        XxlJobLogGlue xxlJobLogGlue = new XxlJobLogGlue();
        System.out.println(apiDao.save(xxlJobLogGlue));
    }

    @Test
    public void findByJobId(){
        System.out.println(dao.findByJobId(1));
        System.out.println(apiDao.findByJobId(1));
    }

    @Test
    public void removeOld(){
        System.out.println(dao.removeOld(1, 5));
        System.out.println(apiDao.removeOld(1, 5));
    }

    @Test
    public void deleteByJobId(){
        System.out.println(dao.deleteByJobId(1));
        System.out.println(apiDao.deleteByJobId(1));
    }
}
