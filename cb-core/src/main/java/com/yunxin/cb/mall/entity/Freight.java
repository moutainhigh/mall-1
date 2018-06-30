package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author x001393
 * 配送
 */
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Freight implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int freightId;
    /**
     * 商家编号
     **/
    private Seller seller;
    /**
     * 商品
     **/
    private Commodity commodity;
    /**
     * 区域编号
     **/
    private String areaCode;
    /**
     * 首件价格
     **/
    private int firstPrice;
    /**
     * 多件价格
     **/
    private int morePrice;
    /**
     * 总价格
     **/
    private float totalPrice;
    /**
     * 创建时间
     **/
    private Date createTime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getFreightId() {
        return freightId;
    }

    public void setFreightId(int freightId) {
        this.freightId = freightId;
    }

    @Column(nullable = false, length = 12)
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMODITY_ID", nullable = false)
    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
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

    @Column(nullable = false, precision = 12, scale = 2)
    public int getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(int firstPrice) {
        this.firstPrice = firstPrice;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public int getMorePrice() {
        return morePrice;
    }

    public void setMorePrice(int morePrice) {
        this.morePrice = morePrice;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SELLER_ID", nullable = true)
    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
