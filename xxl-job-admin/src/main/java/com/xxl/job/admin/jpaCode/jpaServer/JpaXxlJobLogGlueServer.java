package com.xxl.job.admin.jpaCode.jpaServer;


import com.xxl.job.admin.jpaCode.jpaDao.JpaXxlJobLogGlueDao;
import com.xxl.job.admin.jpaCode.model.XxlJobLogGlueEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by YBM on 2020/11/22 18:21
 */
@Service
public class JpaXxlJobLogGlueServer {
    //todo 这个类需要测试
    @Autowired
    JpaXxlJobLogGlueDao dao;

    public int save(XxlJobLogGlueEntity xxlJobLogGlue){
        return ObjectUtils.isNotEmpty(dao.save(xxlJobLogGlue))?1:0;
    }

    public List<XxlJobLogGlueEntity> findByJobId(int jobId){
        return dao.findByJobId(jobId);
    }

    public int removeOld(int jobId,int limit){
        Specification querySpecifi = new Specification<XxlJobLogGlueEntity>() {
            @Override
            public Predicate toPredicate(Root<XxlJobLogGlueEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList();//这是条件集合
                List<Order> orders = new ArrayList();//这是排序集合
                //排序
                orders.add(criteriaBuilder.desc(root.get("updateTime")));
                criteriaQuery.orderBy(orders);
                //条件

                predicates.add(criteriaBuilder.equal(root.get("jobId"), jobId));


                Predicate[] ps = new Predicate[predicates.size()];
                return criteriaQuery.where(predicates.toArray(ps)).getRestriction();
            }

        };
        List<XxlJobLogGlueEntity> all = dao.findAll(querySpecifi, PageRequest.of(0,limit)).getContent();
        List<Integer> ids = all.stream().map(XxlJobLogGlueEntity::getId).collect(Collectors.toList());
      return  dao.removeOld(ids,jobId);
    }

    public int deleteByJobId(int jobId){
        return dao.deleteByJobId(jobId);
    }

}
