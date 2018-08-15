


package com.yunxin.cb.insurance.entity;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * *
 * 保险订单合同编号
 *
 * @author tanggangyi
 * @version 1.0
 * @since 1.0
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class InsuranceOrderCode implements Serializable {

    private static final long serialVersionUID = 1L;

    //columns START
    /**
     * 编码ID
     */
    @Max(9999999999L)
    @ApiModelProperty(value="编码ID",name="codeId",example="1")
    private int codeId;
    /**
     * 编码
     */
    @NotBlank
    @Length(max = 32)
    @ApiModelProperty(value="编码",name="codeNo",example="111111")
    private String codeNo;
    /**
     * 是否使用
     */
    @NotNull
    @Max(9999999999L)
    @ApiModelProperty(value="是否使用",name="useed",example="1")
    private int useed;
    /**
     * 创建时间
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value="创建时间",name="createTime",example="1990-10-12 20:10")
    private Date createTime;
    //columns END


    public InsuranceOrderCode() {
    }

    public InsuranceOrderCode(
            int codeId
    ) {
        this.codeId = codeId;
    }


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public int getCodeId() {
        return this.codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
    public String getCodeNo() {
        return this.codeNo;
    }

    public void setCodeNo(String codeNo) {
        this.codeNo = codeNo;
    }

    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    public int getUseed() {
        return this.useed;
    }

    public void setUseed(int useed) {
        this.useed = useed;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}