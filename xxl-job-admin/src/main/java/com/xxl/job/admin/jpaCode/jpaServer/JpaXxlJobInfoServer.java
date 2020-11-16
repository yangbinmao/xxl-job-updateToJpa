package com.xxl.job.admin.jpaCode.jpaServer;

import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.jpaCode.jpaDao.JpaXxlJobInfoDao;
import com.xxl.job.admin.jpaCode.model.XxlJobInfoEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YBM on 2020/11/15 22:19
 */
@Service
public class JpaXxlJobInfoServer {

    @Autowired
    JpaXxlJobInfoDao jobInfoDao;

    public List<XxlJobInfo> pageList( int offset,int pagesize,int jobGroup,int triggerStatus,String jobDesc,String executorHandler,String author){
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

        return jobInfoDao.findAll(querySpecifi,  PageRequest.of(offset,pagesize)).getContent();
    }


    public int pageListCount( int offset,int pagesize,int jobGroup,int triggerStatus,String jobDesc,String executorHandler,String author) {
        Specification querySpecifi = new Specification<XxlJobInfoEntity>() {
            @Override
            public Predicate toPredicate(Root<XxlJobInfoEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList();//这是条件集合
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
        return (int)jobInfoDao.count(querySpecifi);
    }



    public int save(XxlJobInfoEntity entity){
        if (ObjectUtils.isNotEmpty(jobInfoDao.save(entity))){
            return 1;
        }
        return 0;
    }


    public XxlJobInfoEntity loadById( int id){
        return jobInfoDao.loadById(id);
    }

    public int update(XxlJobInfoEntity entity){
        if (entity.getId()==0){
            return 0;
        }
        if (!jobInfoDao.existsById(entity.getId())){
            return 0;
        }
        if (ObjectUtils.isNotEmpty(jobInfoDao.save(entity))){
            return 1;
        }
        else return 0;
    }

    public long delete( int id){
       return jobInfoDao.delete(id);
    }

    public List<XxlJobInfoEntity> getJobsByGroup( int jobGroup){
        return jobInfoDao.getJobsByGroup(jobGroup);
    }

    public int findAllCount(){
        return jobInfoDao.findAllCount();
    }

    public List<XxlJobInfoEntity> scheduleJobQuery(long maxNextTime, int  pagesize){
        return jobInfoDao.scheduleJobQuery(maxNextTime,PageRequest.of(0, pagesize));
    }


    public int scheduleUpdate(XxlJobInfoEntity xxlJobInfoEntity){
        return jobInfoDao.scheduleUpdate(xxlJobInfoEntity);
    }
}
