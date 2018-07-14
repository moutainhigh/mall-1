package com.yunxin.cb.console.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.Seller;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 角色
 *
 * @author x001393
 */
@JsonAutoDetect
@Entity
@Table(name = "role_info")
@DynamicInsert
@DynamicUpdate
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4618041387665629531L;

    /**
     * ID
     */
    private int roleId;

    private Seller seller;
    /**
     * 名称
     */
    private String roleName;

    /**
     * 编码
     */
    private String roleCode;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;


    private Set<Permission> permissions = new HashSet<>();

    private Set<User> users = new HashSet<User>(0);

    private String rescCodes;

    public Role() {
        super();
    }

    public Role(int roleId) {
        this.roleId = roleId;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SELLER_ID", nullable = false)
    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }


    @Column(nullable = false, length = 32)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Column(nullable = false, length = 64)
    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Column(nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }


    @ManyToMany(fetch = FetchType.LAZY, targetEntity = User.class, mappedBy = "roles", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Transient
    public String getRescCodes() {
        return rescCodes;
    }

    public void setRescCodes(String rescCodes) {
        this.rescCodes = rescCodes;
    }


}
