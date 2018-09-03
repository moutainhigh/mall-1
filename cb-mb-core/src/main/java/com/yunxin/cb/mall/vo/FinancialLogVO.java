package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.meta.FiaciaLogPayType;
import com.yunxin.cb.mall.entity.meta.FiaciaLogTransType;
import com.yunxin.cb.mall.entity.meta.OperationType;
import com.yunxin.cb.mall.entity.meta.PayState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @title: 账单VO
 * @auther: eleven
 * @date: 2018/8/9 17:19
 */
@ApiModel(value="账单VO",description="账单VO对象 FinancialLogVO")
public class FinancialLogVO implements java.io.Serializable{

    private static final long serialVersionUID = 6237700512736618247L;
    /**  */
    @ApiModelProperty(value="账单Id",name="logId",example="1")
    private Integer logId;

    /** 图片 */
    @ApiModelProperty(value="图片 ",name="image",example="goods.jpg")
    private String image;

    /** 交易金额 */
    @ApiModelProperty(value="交易金额",name="amount",example="100.00")
    private BigDecimal amount;

    /** 交易类型：0.收入，1.支出 */
    @ApiModelProperty(value="交易类型",name="type",example="1")
    private OperationType type;

    /** 交易类型：0.保险购买1.保险返利2.商品购买3.商品退货4.借款5.手动还款6.保险返利自动还款7.商品报帐自动还款 */
    @ApiModelProperty(value="交易类型",name="transactionType",example="1")
    private FiaciaLogTransType transactionType;

    /** 交易时间 */
    @ApiModelProperty(value="交易时间",name="createTime",example="2018-08-09 17:52:50")
    private Date createTime;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
