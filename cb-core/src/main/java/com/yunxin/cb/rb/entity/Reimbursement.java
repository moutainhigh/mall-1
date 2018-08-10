package com.yunxin.cb.rb.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.rb.entity.meta.ReimbursementType;
import com.yunxin.cb.rb.entity.meta.RepaymentType;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author wangteng
 *
 */
@JsonAutoDetect
@Entity
@Table(name = "rb_reimbursement")
@DynamicInsert
@DynamicUpdate
@ApiModel(value="报账信息对象",description="报账信息Reimbursement")
public class Reimbursement  implements java.io.Serializable {
    @Max(9999999999L)
    private int reimbursementId;

    @ApiModelProperty(value="报账单号",name="reimbursementNo",example="78764654564")
    private String reimbursementNo;

    @NotNull
    private Customer customer;

    @ApiModelProperty(value="报账总金额",name="amount",example="88888")
    private Double amount;

    @ApiModelProperty(value="税",name="tax",example="2000")
    private Double tax;

    @ApiModelProperty(value="报账订单总金额",name="orderAmount",example="188888")
    private Double orderAmount;

    @ApiModelProperty(value="创建时间",name="createTime",example="2018-07-28")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;
    @ApiModelProperty(value="状态",name="orderState",example="")
    private ReimbursementType orderState;
    @ApiModelProperty(value="还款金额",name="repaymentAmount",example="100")
    private Double repaymentAmount;
    @ApiModelProperty(value="还款方式",name="repaymentType",example="")
    private RepaymentType repaymentType;
    private List<ReimbursementProcess> reimbursementProcess=new ArrayList<>();

    @ApiModelProperty(value="报账订单",name="reimbursementOrder",example="")
    private List<ReimbursementOrder> reimbursementOrder=new ArrayList<>();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public int getReimbursementId() {
        return reimbursementId;
    }

    public void setReimbursementId(int reimbursementId) {
        this.reimbursementId = reimbursementId;
    }
    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    public String getReimbursementNo() {
        return reimbursementNo;
    }

    public void setReimbursementNo(String reimbursementNo) {
        this.reimbursementNo = reimbursementNo;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CUSTOMER_ID", nullable = false, insertable = true, updatable = true)
    })
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }
    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "reimbursement")
    public List<ReimbursementOrder> getReimbursementOrder() {
        return reimbursementOrder;
    }

    public void setReimbursementOrder(List<ReimbursementOrder> reimbursementOrder) {
        this.reimbursementOrder = reimbursementOrder;
    }

    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.ORDINAL)
    public ReimbursementType getOrderState() {
        return orderState;
    }

    public void setOrderState(ReimbursementType orderState) {
        this.orderState = orderState;
    }

    @OneToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "reimbursement")
    public List<ReimbursementProcess> getReimbursementProcess() {
        return reimbursementProcess;
    }

    public void setReimbursementProcess(List<ReimbursementProcess> reimbursementProcess) {
        this.reimbursementProcess = reimbursementProcess;
    }
    @Column(nullable = false, length = 32)
    public Double getRepaymentAmount() {
        return repaymentAmount;
    }

    public void setRepaymentAmount(Double repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }
    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.ORDINAL)
    public RepaymentType getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(RepaymentType repaymentType) {
        this.repaymentType = repaymentType;
    }
}
