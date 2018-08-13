package com.yunxin.cb.rb.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.Catalog;
import com.yunxin.cb.mall.entity.Product;
import com.yunxin.core.web.json.deserializer.JsonTimestampDeserializer;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.DocumentId;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @Description:    资金池明细模型类
 * @author: lxc
 * @Return :
 * @DateTime: 2018/8/7 11:04
 */
@Entity
@Table(name = "rb_funds_pool_log")
@DynamicInsert
@DynamicUpdate
public class FundsPoolLog {
    /**  */
    private Integer poolLogId;

    /**  */
    private FundsPool fundsPool;

    /** 一级商品分类ID */
    @ApiModelProperty(value="一级商品分类ID",name="catalog",example="")
    private Catalog catalog;

    /** 分类名+“资金池” */
    private String poolName;

    /**  */
    private BigDecimal funds;

    /** 类型：1.累计，2.报账 */
    private Integer type;

    /** 交易ID,累计为订单号，报帐为报帐ID */
    private Integer transactionId;

    /** 类型为1为订单详情ID/类型为2为报账详情ID */
    private Integer itemId;

    /** 商品ID */
//    private Integer productId;
    private Product product;

    /** 金额 */
    private BigDecimal amount;

    /** 操作时间 */
    private Date createTime;

    /** 版本号 */
    private Integer version;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    @DocumentId
    public Integer getPoolLogId() {
        return poolLogId;
    }

    public void setPoolLogId(Integer poolLogId) {
        this.poolLogId = poolLogId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POOL_ID", nullable = false)
    public FundsPool getFundsPool() {
        return fundsPool;
    }

    public void setFundsPool(FundsPool fundsPool) {
        this.fundsPool = fundsPool;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATALOG_ID", nullable = false)
    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    @Column(nullable = false, length = 128)
    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName == null ? null : poolName.trim();
    }

    @Column(nullable = false, length = 20)
    public BigDecimal getFunds() {
        return funds;
    }

    public void setFunds(BigDecimal funds) {
        this.funds = funds;
    }

    @Column(nullable = false, length = 2)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(nullable = false, length = 25)
    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

//    @Column(nullable = false, length = 11)
//    public Integer getProductId() {
//        return productId;
//    }
//
//    public void setProductId(Integer productId) {
//        this.productId = productId;
//    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(nullable = false, length = 20)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 25, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @JsonDeserialize(using = JsonTimestampDeserializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(nullable = false, length = 11)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(nullable = false, length = 25)
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
}