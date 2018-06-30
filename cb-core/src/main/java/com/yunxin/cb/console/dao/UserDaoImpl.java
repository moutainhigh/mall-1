package com.yunxin.cb.console.dao;

import com.yunxin.cb.console.entity.User;
import com.yunxin.core.orm.BaseDaoImpl;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.LogicUtils;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserPlusDao {

    @Override
    public List<User> findUsersByRoles(String roleCode, int start, int pageSize) {
        String sql = "select u from User u left join fetch u.roles r where r.roleCode=:c1";
        Query query = entityManager.createQuery(sql);
        query.setParameter("c1", roleCode);
        query.setFirstResult(start);
        query.setMaxResults(pageSize);
        return createActivityVos(query);
    }

    @Override
    public List<User> pageUsersByRole(PageSpecification<User> conQuery) {
//        @Query("from User u left join fetch u.roles r  where r.roleCode=?1")
        String userName = "";
        String realName = "";
//        if(LogicUtils.isNotNull(conQuery.getSearch1())) {
//            userName = conQuery.getSearch1();
//        }
//        if(LogicUtils.isNotNull(conQuery.getSearch2())) {
//            realName = conQuery.getSearch2();
//        }
        int pageSize = conQuery.getPageSize();
        int page = conQuery.getPage();
        String sql = "select u from User u left join fetch u.roles r where r.roleCode='ROLE_MASTER' and u.userName like :c1 and u.realName like :c2 ";
        Query query = entityManager.createQuery(sql);
        query.setParameter("c1", "%" + userName + "%");
        query.setParameter("c2", "%" + realName + "%");
        query.setFirstResult(pageSize * (page - 1));
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    private List<User> createActivityVos(Query query) {
        List<User> result = query.getResultList();
        List<User> activityVos = new ArrayList<>();
        if (LogicUtils.isNotNullAndEmpty(result)) {
            for (int i = 0; i < result.size(); i++) {
                User avo = new User();
                User ar = result.get(i);
                avo.setUserId(ar.getUserId());
                avo.setUserName(ar.getUserName());
                avo.setRealName(ar.getRealName());
                avo.setPassword(ar.getPassword());
                avo.setSex(ar.isSex());
                avo.setEmail(ar.getEmail());
                avo.setPosition(ar.getPosition());
                avo.setMobile(ar.getMobile());
                avo.setEnabled(ar.isEnabled());
                avo.setRoles(ar.getRoles());
                avo.setRemark(ar.getRemark());
                activityVos.add(avo);

            }
        }
        return activityVos;
    }

}
