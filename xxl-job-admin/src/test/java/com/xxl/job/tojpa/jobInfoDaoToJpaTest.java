package com.xxl.job.tojpa;

import com.xxl.job.admin.XxlJobAdminApplication;
import com.xxl.job.admin.core.model.XxlJobInfo;

import com.xxl.job.admin.dao.XxlJobInfoDao;

import com.xxl.job.admin.jpaCode.jpaDao.JpaXxlJobInfoDao;

import com.xxl.job.admin.jpaCode.jpaServer.JpaXxlJobInfoServer;
import com.xxl.job.admin.jpaCode.model.XxlJobInfoEntity;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YBM on 2020/11/3 22:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= XxlJobAdminApplication.class)
public class jobInfoDaoToJpaTest {

    @Resource
    XxlJobInfoDao apiDao;
    @Autowired
    JpaXxlJobInfoDao dao;

    @Autowired
    JpaXxlJobInfoServer server;

    @Test
    public void pageList(){

        int offset=0;
        int pagesize=10;
        int jobGroup=0;
        int triggerStatus=0;
        String jobDesc=null;
        String executorHandler=null;
        String author=null;

        System.out.println(apiDao.pageList(offset,pagesize,jobGroup,triggerStatus,jobDesc,executorHandler,author));

        Specification querySpecifi = new Specification<XxlJobInfoEntity>() {
            @Override
            public Predicate toPredicate(Root<XxlJobInfoEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList();//这是条件集合
                List<Order> orders = new ArrayList();//这是排序集合
                //排序
                orders.add(criteriaBuilder.desc(root.get("id")));
                criteriaQuery.orderBy(orders);
                //条件
                if(jobGroup>0){
                    predicates.add(criteriaBuilder.equal(root.get("jobGroup"), jobGroup ));
                }
                if(triggerStatus>=0){
                    predicates.add(criteriaBuilder.equal(root.get("triggerStatus"),  triggerStatus));
                }
                if (StringUtils.isNotEmpty(jobDesc)){
                    predicates.add(criteriaBuilder.like(root.get("jobDesc"),"%"+jobDesc+"%"));
                }
                if (StringUtils.isNotEmpty(executorHandler)){
                    predicates.add(criteriaBuilder.like(root.get("executorHandler"),"%"+executorHandler+"%"));
                }
                if (StringUtils.isNotEmpty(author)){
                    predicates.add(criteriaBuilder.like(root.get("author"),"%"+author+"%"));
                }
                Predicate[] ps = new Predicate[predicates.size()];
                return criteriaQuery.where(predicates.toArray(ps)).getRestriction();
            }

        };

        Page all = dao.findAll(querySpecifi,  PageRequest.of(offset,pagesize));
        System.out.println(all.getContent());
        System.out.println(server.pageList(offset, pagesize, jobGroup, triggerStatus, jobDesc, executorHandler, author));
    }

    @Test
    public void pageListCount(){
        int offset=0;
        int pagesize=10;
        int jobGroup=0;
        int triggerStatus=0;
        String jobDesc=null;
        String executorHandler=null;
        String author=null;
        Specification querySpecifi = new Specification<XxlJobInfoEntity>() {
            @Override
            public Predicate toPredicate(Root<XxlJobInfoEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList();//这是条件集合
                List<Order> orders = new ArrayList();//这是排序集合
                //排序
                orders.add(criteriaBuilder.desc(root.get("id")));
                criteriaQuery.orderBy(orders);
                //条件
                if(jobGroup>0){
                    predicates.add(criteriaBuilder.equal(root.get("jobGroup"), jobGroup ));
                }
                if(triggerStatus>=0){
                    predicates.add(criteriaBuilder.equal(root.get("triggerStatus"),  triggerStatus));
                }
                if (StringUtils.isNotEmpty(jobDesc)){
                    predicates.add(criteriaBuilder.like(root.get("jobDesc"),"%"+jobDesc+"%"));
                }
                if (StringUtils.isNotEmpty(executorHandler)){
                    predicates.add(criteriaBuilder.like(root.get("executorHandler"),"%"+executorHandler+"%"));
                }
                if (StringUtils.isNotEmpty(author)){
                    predicates.add(criteriaBuilder.like(root.get("author"),"%"+author+"%"));
                }
                Predicate[] ps = new Predicate[predicates.size()];
                return criteriaQuery.where(predicates.toArray(ps)).getRestriction();
            }

        };
        System.out.println(dao.count(querySpecifi));
        System.out.println(apiDao.pageListCount(offset, pagesize, jobGroup, triggerStatus, jobDesc, executorHandler, author));

    }

    @Test
    public void save(){
        XxlJobInfoEntity save = dao.save(new XxlJobInfoEntity());
    }




    @Test
    public void loadById(){
        System.out.println(dao.loadById(1));
        System.out.println(apiDao.loadById(1));
    }

    @Test
    public void update(){
        XxlJobInfoEntity save = dao.save(new XxlJobInfoEntity());
    }
    @Test
    public void delete(){
        System.out.println(dao.delete(4));
        System.out.println(apiDao.delete(5));
    }

    @Test
    public void getJobsByGroup(){
        System.out.println(dao.getJobsByGroup(1));
        System.out.println(apiDao.getJobsByGroup(1));
    }
    @Test
    public void findAllCount(){
        System.out.println(dao.findAllCount());
        System.out.println(apiDao.findAllCount());

    }

    @Test
    public void scheduleJobQuery(){
        int pagesize=10;
        Pageable page = PageRequest.of(0, pagesize);
        System.out.println(dao.scheduleJobQuery(16045056000000L, page));
        System.out.println(apiDao.scheduleJobQuery(16045056000000L, pagesize));
    }

    @Test
    public void scheduleUpdate(){

        XxlJobInfoEntity xxlJobInfoEntity = new XxlJobInfoEntity();
        xxlJobInfoEntity.setId(2);
        xxlJobInfoEntity.setTriggerLastTime(2L);
        xxlJobInfoEntity.setTriggerNextTime(1L);
        xxlJobInfoEntity.setTriggerStatus(1);
//        int i = dao.scheduleUpdate(2L,2L,1,2);
        int i = dao.scheduleUpdate(xxlJobInfoEntity);
        System.out.println(i );
        XxlJobInfo xxlJobInfo = new XxlJobInfo();
        xxlJobInfo.setId(2);
        xxlJobInfo.setTriggerLastTime(3L);
        xxlJobInfo.setTriggerNextTime(2L);
        xxlJobInfo.setTriggerStatus(2);
        System.out.println(apiDao.scheduleUpdate(xxlJobInfo));
    }
}
