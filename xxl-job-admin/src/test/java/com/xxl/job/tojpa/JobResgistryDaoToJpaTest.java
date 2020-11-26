package com.xxl.job.tojpa;

import com.xxl.job.admin.XxlJobAdminApplication;
import com.xxl.job.admin.dao.XxlJobRegistryDao;
import com.xxl.job.admin.jpaCode.jpaServer.JpaXxlJobRegistryServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by YBM on 2020/11/26 20:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= XxlJobAdminApplication.class)
public class JobResgistryDaoToJpaTest {
    @Autowired
    JpaXxlJobRegistryServer dao;
    @Resource
    XxlJobRegistryDao apidao;

    @Test
    public void findDead(){

        System.out.println(apidao.findDead(90, new Date()));
        System.out.println(dao.findDead(90, new Date()));

        System.out.println(apidao.findAll(90, new Date()));
        System.out.println(dao.findAll(90, new Date()));
    }
}
