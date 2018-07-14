/*
 * Powered By [spring-boot-framework]
 * Web Site: http://www.gpcyun.com
 * Since 2015 - 2017
 */

package com.yunxin.cb.console.entity;


import com.yunxin.cb.security.IPermission;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 资源权限
 *
 * @author gonglei
 * @version 2.0
 * @since 2.0
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class Permission implements Serializable, IPermission {

    private static final long serialVersionUID = 5454155825314635342L;

    /**
     * ID
     */
    private int permissionId;

    /**
     * 角色外键
     */
    @NotNull
    private Role role;

    /**
     * 菜单编码
     */
    @NotBlank
    @Size(min = 1, max = 16)
    private String privilegeCode;

    public Permission() {

    }

    public Permission(int permissionId) {
        this.permissionId = permissionId;
    }

    public Permission(Role role, String privilegeCode) {
        this.role = role;
        this.privilegeCode = privilegeCode;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, length = 10)
    public int getPermissionId() {
        return this.permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    @Column(unique = false, nullable = false, length = 16)
    public String getPrivilegeCode() {
        return this.privilegeCode;
    }

    public void setPrivilegeCode(String privilegeCode) {
        this.privilegeCode = privilegeCode;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({@JoinColumn(name = "ROLE_ID", nullable = false)})
    public Role getRole() {
        return role;
    }

    public void setRole(Role roleInfo) {
        this.role = roleInfo;
    }

    @Transient
    public String getRoleCode() {
        return role.getRoleCode();
    }
}