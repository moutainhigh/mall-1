package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Aidy_He on 16/1/27.
 * 物流价格
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class LogisticPrice implements Serializable {

    private int priceId;
    /***物流公司**/
    private Logistic logistic;

    /***市编码**/
    private String cityCodes;

    /***首重重量(克)**/
    private int weight;
    /***首重价格**/
    private float weightPrice;
    /***续重价格**/
    private float continuePrice;
    /***备注**/
    private String remark;

    private String[] cityCode;

    private Set<Commodity> commodities = new HashSet<>();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    @Column(length = 5000)
    public String getCityCodes() {
        return cityCodes;
    }

    public void setCityCodes(String city) {
        this.cityCodes = city;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public float getContinuePrice() {
        return continuePrice;
    }

    public void setContinuePrice(float continuePrice) {
        this.continuePrice = continuePrice;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOGISTIC_ID", nullable = false)
    public Logistic getLogistic() {
        return logistic;
    }

    public void setLogistic(Logistic logistic) {
        this.logistic = logistic;
    }

    @Column(length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(nullable = false, precision = 12)
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public float getWeightPrice() {
        return weightPrice;
    }

    public void setWeightPrice(float weightPrice) {
        this.weightPrice = weightPrice;
    }

    @Transient
    public String[] getCityCode() {
        return cityCode;
    }

    public void setCityCode(String[] cityCode) {
        this.cityCode = cityCode;
    }

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Commodity.class, mappedBy = "logisticPrices", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public Set<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(Set<Commodity> commodities) {
        this.commodities = commodities;
    }
}
