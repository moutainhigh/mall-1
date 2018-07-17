
package com.yunxin.cb.insurance.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * *
 * 保险订单异地投保
 * @author tanggangyi
 * @version 1.0
 * @since 1.0
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class InsuranceOrderOffsite implements Serializable {

    private static final long serialVersionUID = 1L;


    //columns START
    /**
     * 异地投保ID
     */
    @Max(9999999999L)
    private int offsiteId;
    /**
     * 户籍
     */
    @NotBlank
    @Length(max = 64)
    private String sensue;
    /**
     * 工作相关
     */
    @Length(max = 512)
    private String workplace;
    /**
     * 离开投保地的原因
     */
    @NotBlank
    @Length(max = 512)
    private String leaveReason;
    /**
     * 逗留时间
     */
    @NotBlank
    @Length(max = 512)
    private String stayTime;
    /**
     * 异地地址
     */
    @NotBlank
    @Length(max = 512)
    private String offsiteAddress;
    /**
     * 其他事项
     */
    @Length(max = 512)
    private String otherMatter;
    //columns END


    public InsuranceOrderOffsite() {
    }

    public InsuranceOrderOffsite(
            int offsiteId
    ) {
        this.offsiteId = offsiteId;
    }


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public int getOffsiteId() {
        return this.offsiteId;
    }

    public void setOffsiteId(int offsiteId) {
        this.offsiteId = offsiteId;
    }


    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 64)
    public String getSensue() {
        return this.sensue;
    }

    public void setSensue(String sensue) {
        this.sensue = sensue;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 512)
    public String getWorkplace() {
        return this.workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 512)
    public String getLeaveReason() {
        return this.leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 512)
    public String getStayTime() {
        return this.stayTime;
    }

    public void setStayTime(String stayTime) {
        this.stayTime = stayTime;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 512)
    public String getOffsiteAddress() {
        return this.offsiteAddress;
    }

    public void setOffsiteAddress(String offsiteAddress) {
        this.offsiteAddress = offsiteAddress;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 512)
    public String getOtherMatter() {
        return this.otherMatter;
    }

    public void setOtherMatter(String otherMatter) {
        this.otherMatter = otherMatter;
    }


}