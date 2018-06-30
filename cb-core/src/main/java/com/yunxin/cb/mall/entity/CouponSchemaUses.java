package com.yunxin.cb.mall.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Administrator on 2016/1/7.
 * 优惠券方案用途
 */
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class CouponSchemaUses implements java.io.Serializable {

    private int useId;

    /**
     * 优惠券方案
     */
    private CouponSchema couponSchema;
    /**
     * 活动
     */
    private Activity activity;
    /**
     * 分类
     */
    private Category category;
    /**
     * 品牌
     */
    private Brand brand;
    /**
     * 商品
     */
    private Commodity commodity;
    /**
     * 客户
     */
    private Customer customer;
    /**
     * 客户等级
     */
    private Rank rank;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getUseId() {
        return useId;
    }

    public void setUseId(int useId) {
        this.useId = useId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHEME_ID")
    public CouponSchema getCouponSchema() {
        return couponSchema;
    }

    public void setCouponSchema(CouponSchema couponSchema) {
        this.couponSchema = couponSchema;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACTIVITY_ID")
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAND_ID")
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMODITY_ID")
    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RANK_ID")
    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
}
