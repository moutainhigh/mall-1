package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@ApiModel(value="已报账对象",description="已报账对象 AlreadyReimbursementVO")
public class AlreadyReimbursementVO implements Serializable {
    private static final long serialVersionUID = 6837914078188601588L;
    @ApiModelProperty(value="报账信息ID",name="reimbursementId",example="1")
    private int reimbursementId;
    @ApiModelProperty(value="报账信息状态",name="orderState",example="1")
    private int orderState;
    @ApiModelProperty(value="报账商品总数量",name="sum",example="1")
    private int sum;
    @ApiModelProperty(value="合计",name="accountSalePrice",example="111")
    private float accountSalePrice;
    @ApiModelProperty(value="到账金额",name="accountAmount",example="111")
    private BigDecimal accountAmount;
    @ApiModelProperty(value="税",name="tax",example="111")
    private BigDecimal tax;
    @ApiModelProperty(value="创建时间",name="createTime",example="2018-08-08 15:20:20")
    private Date createTime;
    @ApiModelProperty(value="货品信息",name="list",example="货品信息")
    private List<ReimbursementProductVO> list;
    @ApiModelProperty(value="纳税点",name="taxPoint",example="23%")
    private String taxPoint;
    @ApiModelProperty(value="操作时间",name="operationTime",example="2018-08-08 15:20:20")
    private Date operationTime;
    @ApiModelProperty(value="报账单号",name="reimbursementNo",example="52525525")
    private String reimbursementNo;


    public int getReimbursementId() {
        return reimbursementId;
    }

    public void setReimbursementId(int reimbursementId) {
        this.reimbursementId = reimbursementId;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public float getAccountSalePrice() {
        return accountSalePrice;
    }

    public void setAccountSalePrice(float accountSalePrice) {
        this.accountSalePrice = accountSalePrice;
    }

    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<ReimbursementProductVO> getList() {
        return list;
    }

    public void setList(List<ReimbursementProductVO> list) {
        this.list = list;
    }

    public String getTaxPoint() {
        return taxPoint;
    }

    public void setTaxPoint(String taxPoint) {
        this.taxPoint = taxPoint;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getReimbursementNo() {
        return reimbursementNo;
    }

    public void setReimbursementNo(String reimbursementNo) {
        this.reimbursementNo = reimbursementNo;
    }
}
