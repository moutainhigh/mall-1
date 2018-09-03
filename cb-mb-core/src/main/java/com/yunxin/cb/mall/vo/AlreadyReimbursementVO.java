package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.meta.ReimbursementState;
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
    private ReimbursementState orderState;
    @ApiModelProperty(value="报账商品总数量",name="sum",example="1")
    private int sum;
    @ApiModelProperty(value="合计",name="orderAmount",example="111")
    private BigDecimal orderAmount;
    @ApiModelProperty(value="到账金额",name="amount",example="111")
    private BigDecimal amount;
    @ApiModelProperty(value="税",name="tax",example="111")
    private BigDecimal tax;
    @ApiModelProperty(value="创建时间",name="createTime",example="1533691732000")
    private Date createTime;
    @ApiModelProperty(value="货品信息",name="list",example="货品信息")
    private List<ReimbursementProductVO> list;
    @ApiModelProperty(value="纳税点",name="taxPoint",example="23%")
    private String taxPoint;
    @ApiModelProperty(value="操作时间",name="operationTime",example="1533691732000")
    private Date operationTime;
    @ApiModelProperty(value="审批时间",name="examinationTime",example="1533691732000")
    private Date examinationTime;
    @ApiModelProperty(value="报账单号",name="reimbursementNo",example="52525525")
    private String reimbursementNo;
    @ApiModelProperty(value="备注",name="remarks",example="该订单不符合报账")
    private String remarks;
    @ApiModelProperty(value="商家店名",name="sellerName",example="深圳市福田店")
    private String sellerName;
    @ApiModelProperty(value="商品名",name="commodityName",example="2018 宝马")
    private String commodityName;


    public int getReimbursementId() {
        return reimbursementId;
    }

    public void setReimbursementId(int reimbursementId) {
        this.reimbursementId = reimbursementId;
    }

    public ReimbursementState getOrderState() {
        return orderState;
    }

    public void setOrderState(ReimbursementState orderState) {
        this.orderState = orderState;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public Date getExaminationTime() {
        return examinationTime;
    }

    public void setExaminationTime(Date examinationTime) {
        this.examinationTime = examinationTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
}
