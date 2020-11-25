package com.xxl.job.ybmOther;

import com.xxl.job.admin.XxlJobAdminApplication;
import com.xxl.job.admin.core.model.XxlJobRegistry;
import com.xxl.job.admin.dao.XxlJobRegistryDao;
import com.xxl.job.admin.jpaCode.jpaServer.JpaXxlJobRegistryServer;
import com.xxl.job.admin.jpaCode.model.XxlJobRegistryEntity;
import com.xxl.job.core.enums.RegistryConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by YBM on 2020/11/25 23:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= XxlJobAdminApplication.class)
public class other {

    @Autowired
    JpaXxlJobRegistryServer jpaxxlJobRegistryDao;

    @Resource
    XxlJobRegistryDao xxlJobRegistryDao;
    @Test
    public void findAll(){
        List<XxlJobRegistryEntity> list = jpaxxlJobRegistryDao.findAll(RegistryConfig.DEAD_TIMEOUT, new Date());
        System.out.println(list.size());
        List<XxlJobRegistry> list2 = xxlJobRegistryDao.findAll(RegistryConfig.DEAD_TIMEOUT, new Date());
        System.out.println(list2.size());
        System.out.println();

    }
}
