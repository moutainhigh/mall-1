package com.yunxin.cb.mall.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by gonglei on 16/1/19.
 */
@Cacheable(true)
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Spec implements java.io.Serializable {

    private int specId;

    private String specName;

    private Catalog catalog;

    /**
     * 备注
     */
    private String remark;

    public Spec() {
    }

    public Spec(String specName, int catalog) {
        this.specName = specName;
        Catalog cata = new Catalog();
        cata.setCatalogId(catalog);
        this.catalog = cata;
    }

    public Spec(int specId) {
        this.specId = specId;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getSpecId() {
        return specId;
    }

    public void setSpecId(int specId) {
        this.specId = specId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATALOG_ID", nullable = false)
    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    @Column(nullable = false, length = 64)
    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    @Column(nullable = true, length = 512)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spec spec = (Spec) o;
        return Objects.equals(specName, spec.specName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(specName);
    }
}
