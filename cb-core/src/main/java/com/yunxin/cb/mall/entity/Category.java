package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 运营分类
 */
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY,region="mycache")
@Cacheable(true)
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Category implements java.io.Serializable {

    /**
     * ID
     */
    private int categoryId;

    /**
     * 分类编号
     */
    private String categoryNo;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类关键字
     */
    private String categoryKey;

    /**
     * 上级分类
     */
    private Category parentCategory;

    /**
     * 排序号
     */
    private int sortOrder;

    /**
     * 分类描述
     */
    private String description;

    /**
     * SEO关键字
     */
    private String seoKey;

    /**
     * SEO标题
     */
    private String seoTitle;

    /**
     * SEO描述
     */
    private String seoDescription;

    /**
     * 图标路径
     */
    private String iconPath;

    /**
     * 是否启用
     */
    private boolean enabled = true;

    /**
     * 是否推荐
     */
    private boolean recommend;

    /**
     * 创建时间
     */
    private Date createTime;

    private String remark;

    /**
     * 分类级别作为限制层级用途
     */
    private int level;

    /**
     * 子分类
     */
    private List<Category> categories = new ArrayList<Category>(0);

    private Set<Activity> activities = new HashSet<Activity>();

    private Set<Brand> brands = new HashSet<Brand>();

    /**
     * 查询过滤器
     */
    private Set<PropertyFilter> propertyFilters = new HashSet<PropertyFilter>();

    /**
     * 商品分类
     */
    private Set<CommodityCategory> commodityCategories = new HashSet<>();


    public Category() {
    }

    public Category(int categoryId) {
        this.categoryId = categoryId;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int operaId) {
        this.categoryId = operaId;
    }

    @Column(nullable = false, length = 32)
    public String getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(String operaNo) {
        this.categoryNo = operaNo;
    }

    @Column(nullable = false, length = 64)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String operaName) {
        this.categoryName = operaName;
    }

    @Column(nullable = false, length = 128)
    public String getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(String operaKey) {
        this.categoryKey = operaKey;
    }

    @Column(nullable = false, precision = 4)
    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortNo) {
        this.sortOrder = sortNo;
    }

    @Column(length = 512)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false, length = 128)
    public String getSeoKey() {
        return seoKey;
    }

    public void setSeoKey(String seoKey) {
        this.seoKey = seoKey;
    }

    @Column(nullable = false, length = 255)
    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    @Column(length = 512)
    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    @Column(nullable = false, length = 255)
    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String operaImgPath) {
        this.iconPath = operaImgPath;
    }

    @Column(length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(nullable = false, precision = 1)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(nullable = false, precision = 1)
    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommand) {
        this.recommend = recommand;
    }

    @Column(nullable = false, precision = 1)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "CATEGORY_ACTIVITY", joinColumns = {@JoinColumn(name = "CATEGORY_ID", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "ACTIVITY_ID", nullable = false, updatable = false)})
    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    public Set<Brand> getBrands() {
        return brands;
    }

    public void setBrands(Set<Brand> brands) {
        this.brands = brands;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_CATEGORY_ID", nullable = false)
    @JsonIgnore
    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCategory", cascade = CascadeType.REMOVE)
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> operationCategories) {
        this.categories = operationCategories;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.REMOVE)
    public Set<CommodityCategory> getCommodityCategories() {
        return commodityCategories;
    }

    public void setCommodityCategories(Set<CommodityCategory> commodityCategories) {
        this.commodityCategories = commodityCategories;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.REMOVE)
    public Set<PropertyFilter> getPropertyFilters() {
        return propertyFilters;
    }

    public void setPropertyFilters(Set<PropertyFilter> propertyFilters) {
        this.propertyFilters = propertyFilters;
    }

    @Transient
    public Integer getParentCategoryId() {
        if (parentCategory == null) {
            return null;
        }
        return parentCategory.getCategoryId();
    }

    public TreeViewItem cloneTreeItem() {
        TreeViewItem category = new TreeViewItem();
        category.setId(String.valueOf(categoryId));
        category.setText(categoryName);
        return category;
    }
}
