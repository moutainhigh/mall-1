package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.yunxin.cb.mall.entity.meta.CombinationType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by x001393 on 2014/11/7.
 * 商品组合推荐
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Combination implements java.io.Serializable {

    /**
     * 商品组合Id
     */
    private int combinationId;

    /**
     * 组合类型
     */
    private CombinationType combinationType;

    /**
     * 关联商品
     */
    private Commodity commodity;

    /**
     * 组合关联的商品
     */
    private Commodity combinedCommodity;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getCombinationId() {
        return combinationId;
    }

    public void setCombinationId(int commodityCombinationId) {
        this.combinationId = commodityCombinationId;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public CombinationType getCombinationType() {
        return combinationType;
    }

    public void setCombinationType(CombinationType combinationType) {
        this.combinationType = combinationType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMODITY_ID", nullable = false)
    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMBINED_COMMODITY_ID", nullable = false)
    public Commodity getCombinedCommodity() {
        return combinedCommodity;
    }

    public void setCombinedCommodity(Commodity combinedCommodity) {
        this.combinedCommodity = combinedCommodity;
    }
}
