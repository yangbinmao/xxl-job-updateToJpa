package com.xxl.job.admin.jpaCode.jpaServer;

import com.xxl.job.admin.jpaCode.jpaDao.JpaXxlJobRegistryDao;
import com.xxl.job.admin.jpaCode.model.XxlJobRegistryEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by YBM on 2020/11/22 21:37
 */
@Service
public class JpaXxlJobRegistryServer {

    @Autowired
    JpaXxlJobRegistryDao dao;

    public List<Integer> findDead(int timeout, Date nowTime) {

        Specification querySpecifi = new Specification<XxlJobRegistryEntity>() {
            @Override
            public Predicate toPredicate(Root<XxlJobRegistryEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList();//这是条件集合
                //条件
                Calendar nowTimeCal = Calendar.getInstance();
                nowTimeCal.setTime(nowTime);
                nowTimeCal.add(Calendar.SECOND, -timeout);
                predicates.add(criteriaBuilder.lessThan(root.get("updateTime"), nowTimeCal.getTime()));
                Predicate[] ps = new Predicate[predicates.size()];
                return criteriaQuery.where(predicates.toArray(ps)).getRestriction();
            }
        };
        List<XxlJobRegistryEntity> all = dao.findAll(querySpecifi);
        return all.stream().map(XxlJobRegistryEntity::getId).collect(Collectors.toList());
    }

    public int removeDead(List<Integer> ids) {
        return dao.removeDead(ids);
    }

    public List<XxlJobRegistryEntity> findAll(int timeout, Date nowTime) {
        Specification querySpecifi = new Specification<XxlJobRegistryEntity>() {
            @Override
            public Predicate toPredicate(Root<XxlJobRegistryEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList();//这是条件集合
                //条件
                Calendar nowTimeCal = Calendar.getInstance();
                nowTimeCal.setTime(nowTime);
                nowTimeCal.add(Calendar.SECOND, -timeout);
                predicates.add(criteriaBuilder.greaterThan(root.get("updateTime"), nowTimeCal.getTime()));
                Predicate[] ps = new Predicate[predicates.size()];
                return criteriaQuery.where(predicates.toArray(ps)).getRestriction();
            }
        };
        return dao.findAll(querySpecifi);
    }

    public int registryUpdate(String registryGroup, String registryKey, String registryValue, Date updateTime) {
        return dao.registryUpdate(registryGroup, registryKey, registryValue, updateTime);
    }

    public int registrySave(String registryGroup, String registryKey, String registryValue, Date updateTime) {
        XxlJobRegistryEntity entity = new XxlJobRegistryEntity();
        entity.setRegistryGroup(registryGroup);
        entity.setRegistryKey(registryKey);
        entity.setRegistryValue(registryValue);
        entity.setUpdateTime(updateTime);
        return ObjectUtils.isNotEmpty(dao.save(entity)) ? 1 : 0;
    }

    public int registryDelete(String registryGroup, String registryKey, String registryValue) {
        return dao.registryDelete(registryGroup, registryKey, registryValue);
    }


}
