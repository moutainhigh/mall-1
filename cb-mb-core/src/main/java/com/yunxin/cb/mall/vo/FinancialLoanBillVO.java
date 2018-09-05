package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.FinancialLoanBill;
import com.yunxin.cb.mall.entity.meta.CapitalType;
import com.yunxin.cb.mall.entity.meta.TransactionType;
import com.yunxin.cb.util.page.PageFinder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeansException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "用户负债交易VO", description = "用户负债交易VO FinancialLoanBillVO")
public class FinancialLoanBillVO {
    /**  */
    private Integer loanBillId;

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
    private LocalDateTime createTime;

    public Integer getLoanBillId() {
        return loanBillId;
    }

    public void setLoanBillId(Integer loanBillId) {
        this.loanBillId = loanBillId;
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
        this.transactionDesc = transactionDesc;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 分页DO转换VO
     */
    public static PageFinder<FinancialLoanBillVO> convertVOPage(PageFinder<FinancialLoanBill> pageFinder) {

        PageFinder<FinancialLoanBillVO> page = new PageFinder<>(pageFinder.getPageNo(), pageFinder.getPageSize());
        List<FinancialLoanBillVO> volist = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(pageFinder.getData())) {
            for (FinancialLoanBill fa : pageFinder.getData()) {
                FinancialLoanBillVO fbVo = new FinancialLoanBillVO();
                org.springframework.beans.BeanUtils.copyProperties(fa, fbVo);
                volist.add(fbVo);
            }
        }
        page.setData(volist);
        page.setRowCount(pageFinder.getRowCount());//记录总数
        page.setPageCount(pageFinder.getPageCount());//总页数
        return page;
    }
}