package com.xxl.job.admin.jpaCode.jpaServer;

import com.xxl.job.admin.jpaCode.jpaDao.JpaXxlJobGroupDao;
import com.xxl.job.admin.jpaCode.model.XxlJobGroupEntity;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YBM on 2020/11/15 22:01
 */
@Service
public class JpaXxlJobGroupServer {

    @Autowired
    JpaXxlJobGroupDao jobGroupDao;

    public List<XxlJobGroupEntity> findAll(){
        return jobGroupDao.findAll();
    }

    public List<XxlJobGroupEntity> findByAddressType(int addressType){
        return jobGroupDao.findByAddressType(addressType);
    }

    public Integer save(XxlJobGroupEntity xxlJobGroupEntity){
        if (ObjectUtils.isNotEmpty(jobGroupDao.save(xxlJobGroupEntity))){
            return 1;
        }
        else return 0;
    }

    public int update(XxlJobGroupEntity xxlJobGroupEntity){
        return jobGroupDao.update(xxlJobGroupEntity);
    }

    public int remove( int id){
        return jobGroupDao.remove(id);
    }


    public XxlJobGroupEntity load( int id){
        return jobGroupDao.load(id);
    }


    public List<XxlJobGroupEntity> pageList( int offset,int pagesize,String appname,String title){
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

        Page all = jobGroupDao.findAll(querySpecifi,  PageRequest.of(0,10));
        return all.getContent();
    }



    public int pageListCount( int offset,int pagesize,String appname,String title){
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
       return (int) jobGroupDao.count(querySpecifi);
    }

}
