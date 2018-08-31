package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class AddReimbursementRequestVO implements Serializable {
    private static final long serialVersionUID = 7383428194946721725L;
    @ApiModelProperty(value="可报账ID",name="reimbursementQueryId",example="1")
    private int reimbursementQueryId;

    public int getReimbursementQueryId() {
        return reimbursementQueryId;
    }

    public void setReimbursementQueryId(int reimbursementQueryId) {
        this.reimbursementQueryId = reimbursementQueryId;
    }

    @Override
    public String toString() {
        return "AddReimbursementRequestVO{" +
                "reimbursementQueryId=" + reimbursementQueryId +
                '}';
    }
}
