/**
 *
 */
package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.core.web.json.serializer.JsonDateSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 商品目录（商品分类）
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Catalog implements java.io.Serializable {

    /***/
    private static final long serialVersionUID = -246892951432015076L;

    /**
     * ID
     */
    private int catalogId;
    /**
     * 父节点
     */
    private Catalog parentCatalog;
    /**
     * 分类编码
     */
    private String catalogCode;
    /**
     * 分类名称
     */
    private String catalogName;
    /**
     * 叶子节点
     */
    private boolean leaf;
    /**
     * 排序
     */
    private int sortOrder;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 状态：true 启用；false 停用
     */
    private boolean enabled = true;

    /**
     * 是否支持开具增值税发票
     */
    private boolean supportAddedTax;

    /**
     * 备注
     */
    private String remark;

    /**
     * 子分类
     */
    private List<Catalog> catalogs = new ArrayList<Catalog>(0);

    /**
     * 属性组
     */
    private List<CatalogAttributeGroup> catalogAttributeGroups = new ArrayList<CatalogAttributeGroup>();

    private Set<Spec> specs = new HashSet<>();

    /** 分类比例配置 */
    private BigDecimal ratio;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int categoryId) {
        this.catalogId = categoryId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_CATALOG_ID", nullable = false)
    @JsonIgnore
    public Catalog getParentCatalog() {
        return this.parentCatalog;
    }

    public void setParentCatalog(Catalog parentCatalog) {
        this.parentCatalog = parentCatalog;
    }

    @Column(nullable = false, length = 64)
    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catName) {
        this.catalogName = catName;
    }

    @Column(nullable = false, length = 32, unique = true)
    public String getCatalogCode() {
        return catalogCode;
    }

    public void setCatalogCode(String catCode) {
        this.catalogCode = catCode;
    }

    @Column(nullable = false, precision = 1)
    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    @Column(nullable = false, precision = 3)
    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int seqNum) {
        this.sortOrder = seqNum;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(nullable = false, precision = 1)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(nullable = false, precision = 1)
    public boolean isSupportAddedTax() {
        return supportAddedTax;
    }

    public void setSupportAddedTax(boolean supportAddedTax) {
        this.supportAddedTax = supportAddedTax;
    }

    @Column(nullable = true, length = 512)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCatalog", cascade = CascadeType.REMOVE)
    @OrderBy(value = "sortOrder")
    public List<Catalog> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(List<Catalog> categories) {
        this.catalogs = categories;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "catalog", cascade = CascadeType.REMOVE)
    public List<CatalogAttributeGroup> getCatalogAttributeGroups() {
        return catalogAttributeGroups;
    }

    public void setCatalogAttributeGroups(List<CatalogAttributeGroup> propertieGroups) {
        this.catalogAttributeGroups = propertieGroups;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "catalog", cascade = CascadeType.REMOVE)
    public Set<Spec> getSpecs() {
        return specs;
    }

    public void setSpecs(Set<Spec> specs) {
        this.specs = specs;
    }

    @Column(nullable = false, length = 20)
    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    @Transient
    public Integer getParentCatalogId() {
        if (parentCatalog == null) {
            return null;
        }
        return parentCatalog.getCatalogId();
    }


    public TreeViewItem cloneTreeItem() {
        TreeViewItem catalog = new TreeViewItem();
        catalog.setId(String.valueOf(catalogId));
        catalog.setText(catalogName);
        catalog.setRatio(ratio);
        return catalog;
    }
}
