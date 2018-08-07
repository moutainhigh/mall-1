package com.yunxin.cb.rb.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

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

    public Integer getPoolId() {
        return poolId;
    }

    public void setPoolId(Integer poolId) {
        this.poolId = poolId;
    }

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName == null ? null : poolName.trim();
    }

    public BigDecimal getFunds() {
        return funds;
    }

    public void setFunds(BigDecimal funds) {
        this.funds = funds;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}