package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.FinancialLoan;
import com.yunxin.cb.mall.entity.meta.LoanState;
import com.yunxin.cb.mall.entity.meta.RepaymentState;
import com.yunxin.cb.util.page.PageFinder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "用户借款VO", description = "用户借款VO FinancialLoanVO")
public class FinancialLoanVO {
    private static final long serialVersionUID = -2695946271501714063L;

    @ApiModelProperty(value = "借款ID", name = "loanId", example = "1")
    private Integer loanId;

    @ApiModelProperty(value = "客户ID", name = "customerId", example = "1")
    private Integer customerId;

    @ApiModelProperty(value = "贷款金额", name = "amount", example = "100")
    private BigDecimal amount;

    @ApiModelProperty(value = "贷款周期", name = "term", example = "6")
    private Integer term;

    @ApiModelProperty(value = "贷款利率", name = "interestRate", example = "10")
    private BigDecimal interestRate;

    @ApiModelProperty(value = "贷款利息", name = "interest", example = "200")
    private BigDecimal interest;

    @ApiModelProperty(value = "最后还款日", name = "finalRepaymentTime", example = "2018-10-01")
    private LocalDate finalRepaymentTime;

    @ApiModelProperty(value = "应还总额", name = "repayAmount", example = "11000")
    private BigDecimal repayAmount;

    @ApiModelProperty(value = "实际已还金额", name = "readyAmount", example = "1000")
    private BigDecimal readyAmount;

    @ApiModelProperty(value = "剩余需还本金", name = "leftAmount", example = "10000")
    private BigDecimal leftAmount;

    @ApiModelProperty(value = "剩余需还利息", name = "leftInterest", example = "100")
    private BigDecimal leftInterest;

    @ApiModelProperty(value = "逾期次数", name = "overdueNumber", example = "1")
    private Integer overdueNumber;

    @ApiModelProperty(value = "逾期利息", name = "lateFee", example = "100")
    private BigDecimal lateFee;

    @ApiModelProperty(value = "贷款状态：WAIT_AUDIT-申请中(待审核)，AUDIT_PASS-审核通过(已审核)，AUDIT_REFUSE-审核不通过(已拒绝)" +
            "CANCELED-已取消，TRANSFERRED-已转账", name = "state", example = "WAIT_AUDIT")
    private LoanState state;

    @ApiModelProperty(value = "还款状态：NON_REPAYMENT-未还款，OVERDUE_REPAYMENT-已逾期，ALREADY_REPAYMENT-已还款",
            name = "repaymentState", example = "NON_REPAYMENT")
    private RepaymentState repaymentState;

    @ApiModelProperty(value = "贷款日期", name = "repayAmount", example = "2018-09-05 12:00:00")
    private LocalDateTime createTime;

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public LocalDate getFinalRepaymentTime() {
        return finalRepaymentTime;
    }

    public void setFinalRepaymentTime(LocalDate finalRepaymentTime) {
        this.finalRepaymentTime = finalRepaymentTime;
    }

    public LoanState getState() {
        return state;
    }

    public void setState(LoanState state) {
        this.state = state;
    }

    public BigDecimal getRepayAmount() {
        return repayAmount;
    }

    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }

    public BigDecimal getReadyAmount() {
        return readyAmount;
    }

    public void setReadyAmount(BigDecimal readyAmount) {
        this.readyAmount = readyAmount;
    }

    public BigDecimal getLeftAmount() {
        return leftAmount;
    }

    public void setLeftAmount(BigDecimal leftAmount) {
        this.leftAmount = leftAmount;
    }

    public BigDecimal getLeftInterest() {
        return leftInterest;
    }

    public void setLeftInterest(BigDecimal leftInterest) {
        this.leftInterest = leftInterest;
    }

    public Integer getOverdueNumber() {
        return overdueNumber;
    }

    public void setOverdueNumber(Integer overdueNumber) {
        this.overdueNumber = overdueNumber;
    }

    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    public RepaymentState getRepaymentState() {
        return repaymentState;
    }

    public void setRepaymentState(RepaymentState repaymentState) {
        this.repaymentState = repaymentState;
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
    public static PageFinder<FinancialLoanVO> convertVOPage(PageFinder<FinancialLoan> pageFinder) {

        PageFinder<FinancialLoanVO> page = new PageFinder<>(pageFinder.getPageNo(), pageFinder.getPageSize(), pageFinder.getRowCount());
        List<FinancialLoanVO> volist = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(pageFinder.getData())) {
            for (FinancialLoan model : pageFinder.getData()) {
                FinancialLoanVO vo = new FinancialLoanVO();
                org.springframework.beans.BeanUtils.copyProperties(model, vo);
                volist.add(vo);
            }
        }
        page.setData(volist);
        page.setRowCount(pageFinder.getRowCount());//记录总数
        page.setPageCount(pageFinder.getPageCount());//总页数
        return page;
    }
}