/**
 *
 */
package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.meta.ProductState;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.core.web.json.serializer.JsonDateSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author z001075  货品
 */
@Cacheable(true)
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Product implements java.io.Serializable {

    private int productId;
    /**
     * 商品
     */
    private Commodity commodity;
    /**
     * 货品编码
     */
    private String productNo;
    /**
     * 货品名称：由 商品名称+上规格值 组成  用&分隔
     */
    private String productName;
    /**
     * 进货价
     */
    private float costPrice;
    /**
     * 销售价
     */
    private float salePrice;
    /**
     * 市场价
     */
    private float marketPrice;
    /**
     * 状态
     */
    private ProductState productState;
    /**
     * 货品上下架
     */
    private PublishState publishState;
    /**
     * 重量
     */
    private float weight;
    /**
     * 体积
     */
    private float volume;
    /**
     * 默认图片路径
     */
    private String defaultPicPath;
    /**
     * 库存数量
     */
    private int storeNum;
    /**
     * 仓库
     */
    private Store store;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 上架时间
     */
    private Date addTime;

    /**
     * 审核未通过原因
     */
    private String remark;

    private int[] attributeIds;

    public List<ProductAttribute> productAttributes = new ArrayList<ProductAttribute>();


    public Product() {
    }

    public Product(int productId) {
        this.productId = productId;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMODITY_ID", nullable = false)
    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    @Column(nullable = false, length = 32)
    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    @Column(nullable = false, length = 64)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Column(nullable = false, precision = 11, scale = 2)
    public float getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(float costPrice) {
        this.costPrice = costPrice;
    }

    @Column(nullable = false, precision = 11, scale = 2)
    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    @Column(nullable = false, precision = 11, scale = 2)
    public float getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(float marketPrice) {
        this.marketPrice = marketPrice;
    }

    @Column(nullable = false, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public ProductState getProductState() {
        return productState;
    }

    public void setProductState(ProductState status) {
        this.productState = status;
    }

    @Column(nullable = false, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public PublishState getPublishState() {
        return publishState;
    }

    public void setPublishState(PublishState publishState) {
        this.publishState = publishState;
    }

    @Column(nullable = true, precision = 11, scale = 2)
    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Column(nullable = true, precision = 11, scale = 2)
    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    @Column(nullable = true, length = 512)
    public String getDefaultPicPath() {
        return defaultPicPath;
    }

    public void setDefaultPicPath(String defaultPicPath) {
        this.defaultPicPath = defaultPicPath;
    }

    @Column(nullable = true, precision = 12)
    public int getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(int storeNum) {
        this.storeNum = storeNum;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID", nullable = false)
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = true)
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.REMOVE)
//	@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    public List<ProductAttribute> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(List<ProductAttribute> properties) {
        this.productAttributes = properties;
    }

    @Column(nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Transient
    public int[] getAttributeIds() {
        return attributeIds;
    }

    public void setAttributeIds(int[] attributeIds) {
        this.attributeIds = attributeIds;
    }
}
