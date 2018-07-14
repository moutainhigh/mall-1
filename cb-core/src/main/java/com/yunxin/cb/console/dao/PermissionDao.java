/*
 * Powered By [spring-boot-framework]
 * Since 2015 - 2018
 */

package com.yunxin.cb.console.dao;


import com.yunxin.cb.console.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 资源权限
 *
 * @author gonglei
 * @version 2017-11-04-0004 20:22
 */
public interface PermissionDao extends JpaRepository<Permission, Integer>, JpaSpecificationExecutor<Permission> {

    @Query("select distinct rr.privilegeCode from Permission rr left join rr.role role left join role.users user where user.userName=:userName")
    List<String> findPrivilegeCodesByUserName(@Param("userName") String userName);

    @Query("select distinct rr.privilegeCode from Permission rr left join rr.role role where role.roleId=:roleId")
    List<String> findPrivilegeCodesByRoleId(@Param("roleId") int roleId);


    //查询所有权限 关联role
    @Query("select  rr from Permission rr left join fetch rr.role  ")
    List<Permission> findDetailPermissions();

    List<Permission> findByRole_RoleId(int roleId);

    @Modifying
    void deleteByRole_RoleId(int roleId);
}
