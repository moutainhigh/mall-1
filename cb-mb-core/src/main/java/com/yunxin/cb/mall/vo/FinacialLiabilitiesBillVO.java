package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.FinacialLiabilitiesBill;
import com.yunxin.cb.mall.entity.meta.CapitalType;
import com.yunxin.cb.mall.entity.meta.TransactionType;
import com.yunxin.cb.util.page.PageFinder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeansException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel(value = "用户负债交易VO", description = "用户负债交易VO FinacialLiabilitiesBillVO")
public class FinacialLiabilitiesBillVO {
    /**  */
    private Integer financialFreezingId;

    @ApiModelProperty(value = "客户ID", name = "customerId", example = "1")
    private Integer customerId;

    @ApiModelProperty(value = "资金类型", name = "type", example = "ADD")
    private CapitalType type;

    @ApiModelProperty(value = "交易类型", name = "transactionType", example = "INSURANCE_REBATE")
    private TransactionType transactionType;

    @ApiModelProperty(value = "交易描述", name = "transactionDesc", example = "手动还款")
    private String transactionDesc;

    @ApiModelProperty(value = "交易金", name = "amount", example = "5000")
    private BigDecimal amount;

    @ApiModelProperty(value = "交易时间 ", name = "createTime", example = "2018-08-08")
    private Date createTime;

    public Integer getFinancialFreezingId() {
        return financialFreezingId;
    }

    public void setFinancialFreezingId(Integer financialFreezingId) {
        this.financialFreezingId = financialFreezingId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public CapitalType getType() {
        return type;
    }

    public void setType(CapitalType type) {
        this.type = type;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc == null ? null : transactionDesc.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "FinacialLiabilitiesBillVO{" +
                "financialFreezingId=" + financialFreezingId +
                ", customerId=" + customerId +
                ", type=" + type +
                ", transactionType=" + transactionType +
                ", transactionDesc='" + transactionDesc + '\'' +
                ", amount=" + amount +
                ", createTime=" + createTime +
                '}';
    }

    /**
     * 分页DO转换VO
     */
    public static PageFinder<FinacialLiabilitiesBillVO> dOconvertVOPage (PageFinder<FinacialLiabilitiesBill> pageFinder){
        PageFinder<FinacialLiabilitiesBillVO> page = new PageFinder<FinacialLiabilitiesBillVO> (pageFinder.getPageNo(), pageFinder.getPageSize());
        if (pageFinder != null) {
            try {
                List<FinacialLiabilitiesBill> list = pageFinder.getData();
                List<FinacialLiabilitiesBillVO> volist = new ArrayList<>();
                for (FinacialLiabilitiesBill fa : list) {
                    FinacialLiabilitiesBillVO fbVo = new FinacialLiabilitiesBillVO();
                    org.springframework.beans.BeanUtils.copyProperties(fa, fbVo);
                    volist.add(fbVo);
                }
                page.setData(volist);
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
        page.setRowCount(pageFinder.getRowCount());//记录总数
        page.setPageCount(pageFinder.getPageCount());//总页数
        return page;
    }
}