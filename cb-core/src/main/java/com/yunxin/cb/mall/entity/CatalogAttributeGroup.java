/**
 *
 */
package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author z001075
 */
@Cacheable(true)
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class CatalogAttributeGroup implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 454328574547151742L;


    private int groupId;


    private Catalog catalog;

    /**
     * 属性组名称
     */

    private String groupName;

    /**
     * 创建时间
     */

    private Date createTime;

    /**
     * 以图片方式显示
     */
    private boolean showAsImage = true;


    private Set<CatalogAttribute> catalogAttributes = new HashSet<CatalogAttribute>();


    /**
     * *******************
     */
    private String[] attributeName;

    private String[] imagePath;

    private int[] sortOrder;

    public CatalogAttributeGroup() {
    }

    public CatalogAttributeGroup(int groupId) {
        this.groupId = groupId;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Column(nullable = false, length = 64)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATALOG_ID", nullable = false)
    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "catalogAttributeGroup")
    @OrderBy(value = "sortOrder")
    public Set<CatalogAttribute> getCatalogAttributes() {
        return catalogAttributes;
    }

    public void setCatalogAttributes(Set<CatalogAttribute> properties) {
        this.catalogAttributes = properties;
    }


    @Column(nullable = false, precision = 1)
    public boolean isShowAsImage() {
        return showAsImage;
    }

    public void setShowAsImage(boolean showAsImage) {
        this.showAsImage = showAsImage;
    }

    @Transient
    @JsonIgnore
    public String[] getAttributeName() {
        return attributeName;
    }


    public void setAttributeName(String[] attributeName) {
        this.attributeName = attributeName;
    }

    @Transient
    @JsonIgnore
    public int[] getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int[] sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Transient
    @JsonIgnore
    public String[] getImagePath() {
        return imagePath;
    }

    public void setImagePath(String[] imagePath) {
        this.imagePath = imagePath;
    }


}
