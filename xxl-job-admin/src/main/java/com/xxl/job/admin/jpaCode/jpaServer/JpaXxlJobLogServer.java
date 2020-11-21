package com.xxl.job.admin.jpaCode.jpaServer;


import com.xxl.job.admin.core.model.XxlJobLog;
import com.xxl.job.admin.jpaCode.jpaDao.JpaXxlJobLogDao;
import com.xxl.job.admin.jpaCode.model.XxlJobInfoEntity;
import com.xxl.job.admin.jpaCode.model.XxlJobLogEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by YBM on 2020/11/15 22:48
 */
@Service
public class JpaXxlJobLogServer {

    @Autowired
    JpaXxlJobLogDao xxlJobLogDao;


    public List<XxlJobLogEntity> pageList(int offset, int pagesize, int jobGroup, int jobId, Date triggerTimeStart, Date triggerTimeEnd, int logStatus) {
        Specification querySpecifi = new Specification<XxlJobLogEntity>() {
            @Override
            public Predicate toPredicate(Root<XxlJobLogEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList();//这是条件集合
                List<Order> orders = new ArrayList();//这是排序集合
                //排序
                orders.add(criteriaBuilder.desc(root.get("triggerTime")));
                criteriaQuery.orderBy(orders);
                //条件
                if (jobId == 0 && jobGroup > 0) {
                    predicates.add(criteriaBuilder.equal(root.get("jobGroup"), jobGroup));
                }
                if (jobId > 0) {
                    predicates.add(criteriaBuilder.equal(root.get("jobId"), jobId));
                }
                if (ObjectUtils.isNotEmpty(triggerTimeStart)){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("triggerTime"), triggerTimeStart));
                }
                if (ObjectUtils.isNotEmpty(triggerTimeEnd)){
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("triggerTime"), triggerTimeEnd));
                }
                if (logStatus==1){
                    predicates.add(criteriaBuilder.equal(root.get("handleCode"), 200));
                }
                if (logStatus==2){
                    //TODO 这里可能有问题。not in 需要测试确认（好像没有问题了，需要在观察一下）
                    Predicate triggerCode = criteriaBuilder.not(criteriaBuilder.between(root.get("triggerCode"), 0, 200));
                    Predicate handleCode = criteriaBuilder.not(criteriaBuilder.between(root.get("handleCode"), 0, 200));
                    predicates.add(criteriaBuilder.or(triggerCode,handleCode));
                }
                if (logStatus==3){
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("triggerCode"), 200));
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("handleCode"), 0));
                }

                Predicate[] ps = new Predicate[predicates.size()];
                return criteriaQuery.where(predicates.toArray(ps)).getRestriction();
            }

        };
        return xxlJobLogDao.findAll(querySpecifi,  PageRequest.of(offset,pagesize)).getContent();

    }


    public int pageListCount(int offset, int pagesize, int jobGroup, int jobId, Date triggerTimeStart, Date triggerTimeEnd, int logStatus) {

        Specification querySpecifi = new Specification<XxlJobLogEntity>() {
            @Override
            public Predicate toPredicate(Root<XxlJobLogEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList();//这是条件集合

                //条件
                if (jobId == 0 && jobGroup > 0) {
                    predicates.add(criteriaBuilder.equal(root.get("jobGroup"), jobGroup));
                }
                if (jobId > 0) {
                    predicates.add(criteriaBuilder.equal(root.get("jobId"), jobId));
                }
                if (ObjectUtils.isNotEmpty(triggerTimeStart)){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("triggerTime"), triggerTimeStart));
                }
                if (ObjectUtils.isNotEmpty(triggerTimeEnd)){
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("triggerTime"), triggerTimeEnd));
                }
                if (logStatus==1){
                    predicates.add(criteriaBuilder.equal(root.get("handleCode"), 200));
                }
                if (logStatus==2){
                    //TODO 这里可能有问题。not in 需要测试确认（好像没有问题了，需要在观察一下）
                    Predicate triggerCode = criteriaBuilder.not(criteriaBuilder.between(root.get("triggerCode"), 0, 200));
                    Predicate handleCode = criteriaBuilder.not(criteriaBuilder.between(root.get("handleCode"), 0, 200));
                    predicates.add(criteriaBuilder.or(triggerCode,handleCode));
                }
                if (logStatus==3){
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("triggerCode"), 200));
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("handleCode"), 0));
                }

                Predicate[] ps = new Predicate[predicates.size()];
                return criteriaQuery.where(predicates.toArray(ps)).getRestriction();
            }

        };
        return (int) xxlJobLogDao.count(querySpecifi);
    }

    public XxlJobLogEntity load(long id){
        return  xxlJobLogDao.load(id);
    }


    public int save(XxlJobLogEntity entity){
        if (ObjectUtils.isNotEmpty(xxlJobLogDao.save(entity))){
            return 1;
        }else {
            return 0;
        }
    }


    public int updateTriggerInfo(XxlJobLogEntity entity){
        return xxlJobLogDao.updateTriggerInfo(entity);
    }


    public int delete(int jobId){
        return xxlJobLogDao.delete(jobId);
    }

    public Map<String, Object> findLogReport(Date from,Date to){
        return  xxlJobLogDao.findLogReport(from,to);
    }

    public List<Long> findClearLogIds( int jobGroup, int jobId, Date clearBeforeTime,int clearBeforeNum, int pagesize){
       //todo  需要等数据多了在测试一下
        Specification querySpecifi = new Specification<XxlJobLogEntity>() {
            @Override
            public Predicate toPredicate(Root<XxlJobLogEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList();//这是条件集合
                List<Order> orders = new ArrayList();//这是排序集合
                //排序
                orders.add(criteriaBuilder.asc(root.get("id")));
                criteriaQuery.orderBy(orders);
                //条件
                if (jobGroup > 0) {
                    predicates.add(criteriaBuilder.equal(root.get("jobGroup"), jobGroup));
                }
                if (jobId > 0) {
                    predicates.add(criteriaBuilder.equal(root.get("jobId"), jobId));
                }
                if (ObjectUtils.isNotEmpty(clearBeforeTime)) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("triggerTimeEnd"), clearBeforeTime));
                }

                if (clearBeforeNum > 0) {

                    Specification querySpecifiIn = new Specification<XxlJobLogEntity>() {
                        @Override
                        public Predicate toPredicate(Root<XxlJobLogEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                            List<Predicate> predicates = new ArrayList();//这是条件集合
                            List<Order> orders = new ArrayList();//这是排序集合
                            //排序
                            orders.add(criteriaBuilder.desc(root.get("triggerTime")));
                            criteriaQuery.orderBy(orders);
                            //条件
                            if (jobGroup > 0) {
                                predicates.add(criteriaBuilder.equal(root.get("jobGroup"), jobGroup));
                            }
                            if (jobId > 0) {
                                predicates.add(criteriaBuilder.equal(root.get("jobId"), jobId));
                            }
                            Predicate[] ps = new Predicate[predicates.size()];
                            return criteriaQuery.where(predicates.toArray(ps)).getRestriction();
                        }
                    };
                    Page all = xxlJobLogDao.findAll(querySpecifiIn, PageRequest.of(0, clearBeforeNum));
                    List<XxlJobLogEntity> ids = all.getContent();
                    List<Long> collect = ids.stream().map(XxlJobLogEntity::getId).collect(Collectors.toList());
                    predicates.add(criteriaBuilder.not(criteriaBuilder.in(root.get("id")).value(collect)));

                }


                Predicate[] ps = new Predicate[predicates.size()];
                return criteriaQuery.where(predicates.toArray(ps)).getRestriction();

            }

        };
        List<XxlJobLogEntity> content = xxlJobLogDao.findAll(querySpecifi, PageRequest.of(0, pagesize)).getContent();
        return  content.stream().map(XxlJobLogEntity::getId).collect(Collectors.toList());

    }

    public int clearLog( List<Long> logIds){
        return xxlJobLogDao.clearLog(logIds);
    }

    }
