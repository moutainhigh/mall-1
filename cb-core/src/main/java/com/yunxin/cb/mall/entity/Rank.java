/**
 *
 */
package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author z001075  等级表
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Rank implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4022996388160475094L;

    /**
     * ID
     */
    private int rankId;
    /**
     * 等级名字
     */
    private String rankName;
    /**
     * 积分
     */
    private int integral;
    /**
     * 积分公式
     */
    private String rule;
    /**
     * 默认等级
     */
    private boolean defaultRank;

    private String remark;

    private Set<Customer> customers = new HashSet<>(0);

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getRankId() {
        return rankId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    @Column(unique = true, nullable = false, length = 64)
    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    @Column(nullable = false, precision = 12)
    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    @Column(nullable = true, length = 255)
    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Column(nullable = false, precision = 1)
    public boolean isDefaultRank() {
        return defaultRank;
    }

    public void setDefaultRank(boolean defaultRank) {
        this.defaultRank = defaultRank;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rank", cascade = CascadeType.ALL)
    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
