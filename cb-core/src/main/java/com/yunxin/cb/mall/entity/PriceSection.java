package com.yunxin.cb.mall.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Administrator on 2016/1/10.
 */
@Cacheable(true)
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class PriceSection implements java.io.Serializable {
    /***id*/
    private int sectionId;

    /**
     * 价格段编号
     */
    private String sectionNo;

    /**
     * 起始价格
     */
    private int startPrice;

    /**
     * 结束价格
     */
    private int endPrice;

    /**
     * 状态：true 启用；false 停用
     */
    private boolean enabled;

    /**
     * 备注
     */
    private String remark;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    @Column(nullable = false, length = 32)
    public String getSectionNo() {
        return sectionNo;
    }

    public void setSectionNo(String sectionNo) {
        this.sectionNo = sectionNo;
    }

    @Column(nullable = false, precision = 11)
    public int getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    @Column(nullable = false, precision = 11)
    public int getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(int endPrice) {
        this.endPrice = endPrice;
    }

    @Column(nullable = false, precision = 1)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enable) {
        this.enabled = enable;
    }

    @Column(length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
