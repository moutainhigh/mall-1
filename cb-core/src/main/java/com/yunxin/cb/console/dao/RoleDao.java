package com.yunxin.cb.console.dao;

import com.yunxin.cb.console.entity.Role;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author x001393
 */

public interface RoleDao extends RolePlusDao, JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role>, BaseDao<Role> {

    Role findByRoleName(String roleName);

    Role findByRoleCode(String roleCode);

    Role findByRoleCodeAndRoleIdNot(String roleCode, int roleId);

    Role findByRoleNameAndRoleIdNot(String roleName, int roleId);

    @Query("select r from Role r left join fetch r.roleRescs where r.roleId=?1")
    Role findByRoleId1(int roleId);

}

interface RolePlusDao {
    List<Role> queryRolersByUser(int userId) throws Exception;

}
