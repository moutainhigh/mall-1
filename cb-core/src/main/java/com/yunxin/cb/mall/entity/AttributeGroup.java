/**
 *
 */
package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonDateSerializer;
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
@NamedEntityGraph(name = "AttributeGroup.attributes", attributeNodes = @NamedAttributeNode("attributes"))
public class AttributeGroup implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 454328574547151742L;


    private int groupId;


    private Commodity commodity;

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


    private Set<Attribute> attributes = new HashSet<Attribute>();


    /**
     * *******************
     */
    private int[] attributeId;
    private String[] attributeName;

    private String[] imagePath;

    private int[] sortOrder;

    public AttributeGroup() {
    }

    public AttributeGroup(int groupId) {
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
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMODITY_ID", nullable = false)
    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "attributeGroup")
    @OrderBy(value = "sortOrder")
    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Attribute> properties) {
        this.attributes = properties;
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

    @Transient
    @JsonIgnore
    public int[] getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int[] attributeId) {
        this.attributeId = attributeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttributeGroup that = (AttributeGroup) o;

        return groupId == that.groupId;

    }

    @Override
    public int hashCode() {
        return groupId;
    }
}
