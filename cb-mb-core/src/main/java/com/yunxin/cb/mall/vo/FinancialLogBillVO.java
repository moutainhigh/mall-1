package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.meta.FiaciaLogPayType;
import com.yunxin.cb.mall.entity.meta.FiaciaLogTransType;
import com.yunxin.cb.mall.entity.meta.OperationType;
import com.yunxin.cb.mall.entity.meta.PayState;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class FinancialLogBillVO implements java.io.Serializable{
    private static final long serialVersionUID = 2424480263032914367L;

    /**  */
    @ApiModelProperty(value="账单Id",name="logId",example="1")
    private Integer logId;

    /** 用户id */
    @ApiModelProperty(value="用户id",name="customerId",example="1")
    private Integer customerId;

    /** 标题 */
    @ApiModelProperty(value="标题",name="title",example="某某商品购买")
    private String title;

    /** 图片 */
    @ApiModelProperty(value="图片 ",name="image",example="goods.jpg")
    private String image;

    /** 交易人 */
    @ApiModelProperty(value="交易人",name="customerName",example="张三")
    private String customerName;

    /** 交易金额 */
    @ApiModelProperty(value="交易金额",name="amount",example="100.00")
    private BigDecimal amount;

    /** 交易类型：0.收入，1.支出 */
    @ApiModelProperty(value="交易类型",name="type",example="1")
    private OperationType type;

    /** 交易类型：0.保险购买1.保险返利2.商品购买3.商品退货4.借款5.手动还款6.保险返利自动还款7.商品报帐自动还款 */
    @ApiModelProperty(value="交易类型",name="transactionType",example="1")
    private FiaciaLogTransType transactionType;

    /** 支付方式：0.微信，1.支付宝，2.报账，3.还款 */
    @ApiModelProperty(value="支付方式",name="payType",example="1")
    private FiaciaLogPayType payType;

    /** 交易时间 */
    @ApiModelProperty(value="交易时间",name="createTime",example="2018-08-09 17:52:50")
    private Date createTime;

    /** 交易状态 */
    @ApiModelProperty(value="交易状态",name="state",example="1")
    private PayState state;

    /** 交易订单号 */
    @ApiModelProperty(value="交易订单号",name="transactionNo",example="200012145")
    private String transactionNo;

    /** 交易描述 */
    @ApiModelProperty(value="交易描述",name="transactionDesc",example="描述")
    private String transactionDesc;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public FiaciaLogTransType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(FiaciaLogTransType transactionType) {
        this.transactionType = transactionType;
    }

    public FiaciaLogPayType getPayType() {
        return payType;
    }

    public void setPayType(FiaciaLogPayType payType) {
        this.payType = payType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public PayState getState() {
        return state;
    }

    public void setState(PayState state) {
        this.state = state;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    @Override
    public String toString() {
        return "FinancialLogBillVO{" +
                "logId=" + logId +
                ", customerId=" + customerId +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", customerName='" + customerName + '\'' +
                ", amount=" + amount +
                ", type=" + type +
                ", transactionType=" + transactionType +
                ", payType=" + payType +
                ", createTime=" + createTime +
                ", state=" + state +
                ", transactionNo='" + transactionNo + '\'' +
                ", transactionDesc='" + transactionDesc + '\'' +
                '}';
    }
}
