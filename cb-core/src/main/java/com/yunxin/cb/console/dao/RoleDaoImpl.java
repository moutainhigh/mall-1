package com.yunxin.cb.console.dao;

import com.yunxin.cb.console.entity.Role;
import com.yunxin.cb.console.entity.Role_;
import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.console.entity.User_;
import com.yunxin.core.orm.BaseDaoImpl;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RolePlusDao {

    @Override
    public List<Role> queryRolersByUser(int userId) throws Exception {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root<Role> root = criteriaQuery.from(Role.class);
        SetJoin<Role, User> depJoin = root.join(Role_.users, JoinType.LEFT);
        Predicate predicate = criteriaBuilder.equal(depJoin.get(User_.userId), userId);
        criteriaQuery.where(predicate);
        TypedQuery<Role> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Role> roles = typedQuery.getResultList();
        List<Role> roles1 = new ArrayList<>();
        for (Role r : roles) {

        }
        return roles1;
    }


}
