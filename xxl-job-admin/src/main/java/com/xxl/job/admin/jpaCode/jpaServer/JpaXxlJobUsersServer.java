package com.xxl.job.admin.jpaCode.jpaServer;


import com.xxl.job.admin.jpaCode.jpaDao.JpaXxlJobUserDao;

import com.xxl.job.admin.jpaCode.model.XxlJobUserEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YBM on 2020/11/22 23:54
 */
@Service
public class JpaXxlJobUsersServer {

    @Autowired
    JpaXxlJobUserDao dao;

    public List<XxlJobUserEntity> pageList(int offset, int pagesize, String username, int role){
        Specification querySpecifi = new Specification<XxlJobUserEntity>() {
            @Override
            public Predicate toPredicate(Root<XxlJobUserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList();//这是条件集合
                List<Order> orders = new ArrayList();//这是排序集合
                //排序
                orders.add(criteriaBuilder.desc(root.get("username")));
                criteriaQuery.orderBy(orders);
                //条件
                if(StringUtils.isNotEmpty(username)){
                    predicates.add(criteriaBuilder.equal(root.get("username"), username ));
                }
                if (role>-1){
                    predicates.add(criteriaBuilder.equal(root.get("role"), role ));
                }

                Predicate[] ps = new Predicate[predicates.size()];
                return criteriaQuery.where(predicates.toArray(ps)).getRestriction();
            }

        };
        int page=0;
        if (offset!=0){
            page =(((offset/pagesize)-1)<0)?0:(offset/pagesize)-1;
        }
        return dao.findAll(querySpecifi, PageRequest.of(page,pagesize)).getContent();
    }

    public int pageListCount(int offset, int pagesize, String username, int role){
        Specification querySpecifi = new Specification<XxlJobUserEntity>() {
            @Override
            public Predicate toPredicate(Root<XxlJobUserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList();//这是条件集合

                //条件
                if(StringUtils.isNotEmpty(username)){
                    predicates.add(criteriaBuilder.equal(root.get("username"), username ));
                }
                if (role>-1){
                    predicates.add(criteriaBuilder.equal(root.get("role"), role ));
                }

                Predicate[] ps = new Predicate[predicates.size()];
                return criteriaQuery.where(predicates.toArray(ps)).getRestriction();
            }

        };
        return (int)dao.count(querySpecifi);
    }

    public XxlJobUserEntity loadByUserName(String username){
        return dao.loadByUserName(username);
    }

    public int save(XxlJobUserEntity entity){
        return ObjectUtils.isNotEmpty(dao.save(entity))?1:0;
    }

    public int update(XxlJobUserEntity entity){
        return dao.update(entity);
    }
    public int delete(int  id){
        return dao.delete(id);
    }
}
