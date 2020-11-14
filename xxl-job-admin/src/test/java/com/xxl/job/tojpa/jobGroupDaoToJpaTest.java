package com.xxl.job.tojpa;

import com.xxl.job.admin.XxlJobAdminApplication;
import com.xxl.job.admin.core.model.XxlJobGroup;
import com.xxl.job.admin.dao.XxlJobGroupDao;
import com.xxl.job.admin.jpaCode.model.XxlJobGroupEntity;
import com.xxl.job.admin.jpaCode.jpaDao.JpaXxlJobGroupDao;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.management.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YBM on 2020/10/8 20:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=XxlJobAdminApplication.class)
public class jobGroupDaoToJpaTest {

    @Resource
     XxlJobGroupDao apiDao;
    @Autowired
    JpaXxlJobGroupDao dao;

    @Test
    public void findAll(){
        List<XxlJobGroupEntity> all = dao.findAll();
   all.forEach(entity->{
       System.out.println(entity);
   });
        System.out.println("================");
        List<XxlJobGroup> all1 = apiDao.findAll();
        all1.forEach(entity->{
            System.out.println(entity);
        });

    }

    @Test
    public void findByAddressType(){
        List<XxlJobGroupEntity> byAddressType = dao.findByAddressType(0);
        byAddressType.forEach(entity->{
            System.out.println(entity);
        });
        System.out.println("================");
        List<XxlJobGroup> byAddressType1 = apiDao.findByAddressType(0);
        byAddressType1.forEach(entity->{
            System.out.println(entity);
        });
    }


    @Test
    public void Save(){
        XxlJobGroupEntity xxlJobGroupEntity = new XxlJobGroupEntity();
        xxlJobGroupEntity.setTitle("新增测试1");
        xxlJobGroupEntity.setAppname("xxl-job-executor-sample");
        xxlJobGroupEntity.setAddressType(0);
//        xxlJobGroupEntity.setAddressList(null);
        System.out.println(dao.save(xxlJobGroupEntity));

        XxlJobGroup xxlJobGroup = new XxlJobGroup();
        xxlJobGroup.setTitle("新增测试2");
        xxlJobGroup.setAppname("xxl-job-executor-sample");
        xxlJobGroup.setAddressType(0);
//        xxlJobGroup.setAddressList(null);
        System.out.println("================");
        System.out.println(apiDao.save(xxlJobGroup));

    }

    @Test
    public void update(){

        List<XxlJobGroupEntity> all = dao.findAll();
        XxlJobGroupEntity xxlJobGroupEntity = all.get(1);
        xxlJobGroupEntity.setId(2);
        xxlJobGroupEntity.setTitle("修改测试11");
        xxlJobGroupEntity.setAppname("xxl-job-executor-sample2");
        System.out.println(dao.update(xxlJobGroupEntity));
        System.out.println("=====");
        System.out.println(dao.findAll());
    }

    @Test
    public void remove(){
        System.out.println(dao.remove(2));
    }


    @Test
    public void load(){
        System.out.println(dao.load(1));
    }
    @Test
    public void pageList(){

        String appname=null;
        String title=null;

        Specification querySpecifi = new Specification<XxlJobGroupEntity>() {
            @Override
            public Predicate toPredicate(Root<XxlJobGroupEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList();//这是条件集合
                List<Order> orders = new ArrayList();//这是排序集合
                //排序
                orders.add(criteriaBuilder.asc(root.get("appname")));
                orders.add(criteriaBuilder.asc(root.get("title")));
                orders.add(criteriaBuilder.asc(root.get("id")));
                criteriaQuery.orderBy(orders);
                //条件
                if(StringUtils.isNotEmpty(appname)){
                    predicates.add(criteriaBuilder.like(root.get("appname"),  "%" +appname+ "%" ));
                }
                if(StringUtils.isNotEmpty(title)){
                    predicates.add(criteriaBuilder.like(root.get("title"),  "%" +title +"%" ));
                }
                Predicate[] ps = new Predicate[predicates.size()];
                return criteriaQuery.where(predicates.toArray(ps)).getRestriction();
            }

        };

        Page all = dao.findAll(querySpecifi,  PageRequest.of(0,10));
        System.out.println(all.getContent());

        System.out.println(apiDao.pageList(0, 10, appname, title));

    }

    @Test
    public void pageListCount(){
        String appname=null;
        String title=null;

        Specification querySpecifi = new Specification<XxlJobGroupEntity>() {
            @Override
            public Predicate toPredicate(Root<XxlJobGroupEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList();//这是条件集合

                //条件
                if(StringUtils.isNotEmpty(appname)){
                    predicates.add(criteriaBuilder.like(root.get("appname"),  "%" +appname+ "%" ));
                }
                if(StringUtils.isNotEmpty(title)){
                    predicates.add(criteriaBuilder.like(root.get("title"),  "%" +title +"%" ));
                }
                Predicate[] ps = new Predicate[predicates.size()];
                return criteriaQuery.where(predicates.toArray(ps)).getRestriction();
            }

        };
        long count = dao.count(querySpecifi);
        System.out.println(count);

        System.out.println(apiDao.pageListCount(0, 10, appname, title));
    }
}
