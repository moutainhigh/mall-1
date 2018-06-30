package com.yunxin.cb.mall.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by Aidy_He on 16/2/23.
 */
@Cacheable(true)
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class ActivityCommodity implements java.io.Serializable {

    @EmbeddedId
    private ActivityCommodityId id;

    private Activity activity;

    private Commodity commodity;

    private int limitAmountSize;

    @EmbeddedId
    @AttributeOverrides({@AttributeOverride(name = "activityId", column = @Column(name = "ACTIVITY_ID", nullable = false)), @AttributeOverride(name = "commodityId", column = @Column(name = "COMMODITY_ID", nullable = false))})
    public ActivityCommodityId getId() {
        return id;
    }

    public void setId(ActivityCommodityId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTIVITY_ID", nullable = false, insertable = false, updatable = false)
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMODITY_ID", nullable = false, insertable = false, updatable = false)
    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    @Column(nullable = false, precision = 11)
    public int getLimitAmountSize() {
        return limitAmountSize;
    }

    public void setLimitAmountSize(int limitAmountSize) {
        this.limitAmountSize = limitAmountSize;
    }
}
