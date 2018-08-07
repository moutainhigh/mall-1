package com.yunxin.cb.rb.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.DocumentId;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @Description:    资金池模型类
 * @author: lxc
 * @Return :
 * @DateTime: 2018/8/7 11:04
 */
@Entity
@Table(name = "rb_funds_pool")
@DynamicInsert
@DynamicUpdate
public class FundsPool  implements Serializable {
    /**  */
    private Integer poolId;

    /** 一级商品分类ID */
    private Integer catalogId;

    /** 分类名+“资金池” */
    private String poolName;

    /** 资金 */
    private BigDecimal funds;

    /** 版本号 */
    private Integer version;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    @DocumentId
    public Integer getPoolId() {
        return poolId;
    }

    public void setPoolId(Integer poolId) {
        this.poolId = poolId;
    }

    @Column(nullable = false, length = 11)
    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    @Column(nullable = false, length = 128)
    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName == null ? null : poolName.trim();
    }

    @Column(nullable = false, length = 20)
    public BigDecimal getFunds() {
        return funds;
    }

    public void setFunds(BigDecimal funds) {
        this.funds = funds;
    }

    @Column(nullable = false, length = 11)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}