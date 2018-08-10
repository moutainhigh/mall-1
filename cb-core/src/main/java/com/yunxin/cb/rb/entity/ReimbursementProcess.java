package com.yunxin.cb.rb.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.rb.entity.meta.ReimbursementProcessType;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@JsonAutoDetect
@Entity
@Table(name = "rb_Reimbursement_process")
@DynamicInsert
@DynamicUpdate
@ApiModel(value="报账审批对象",description="报账审批ReimbursementProcess")
public class ReimbursementProcess  implements java.io.Serializable {
    @Max(9999999999L)
    private int processId;
    @ApiModelProperty(value="报账信息",name="reimbursement",example="78764654564")
    private Reimbursement reimbursement;
    @ApiModelProperty(value="操作用户",name="user",example="user")
    private User user;
    @ApiModelProperty(value="备注",name="remarks",example="通过")
    private String remarks;
    private ReimbursementProcessType orderState;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "REIMBURSEMENT_ID", nullable = false, insertable = true, updatable = true)
    })
    public Reimbursement getReimbursement() {
        return reimbursement;
    }

    public void setReimbursement(Reimbursement reimbursement) {
        this.reimbursement = reimbursement;
    }
    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "USER_ID", nullable = false, insertable = true, updatable = true)
    })
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 220)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.ORDINAL)
    public ReimbursementProcessType getOrderState() {
        return orderState;
    }

    public void setOrderState(ReimbursementProcessType orderState) {
        this.orderState = orderState;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
