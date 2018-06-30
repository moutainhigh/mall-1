package com.yunxin.cb.mall.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by gonglei on 16/1/19.
 * 商品规格
 */
@Cacheable(true)
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class CommoditySpec implements java.io.Serializable {

    @EmbeddedId
    private CommoditySpecId id;

    private Commodity commodity;

    private Spec spec;

    private String value;

    @EmbeddedId
    @AttributeOverrides({@AttributeOverride(name = "commodityId", column = @Column(name = "COMMODITY_ID", nullable = false)), @AttributeOverride(name = "specId", column = @Column(name = "SPEC_ID", nullable = false))})
    public CommoditySpecId getId() {
        return id;
    }

    public void setId(CommoditySpecId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMODITY_ID", nullable = false, insertable = false, updatable = false)
    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SPEC_ID", nullable = false, insertable = false, updatable = false)
    public Spec getSpec() {
        return spec;
    }

    public void setSpec(Spec spec) {
        this.spec = spec;
    }

    @Column(nullable = true, length = 255)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
