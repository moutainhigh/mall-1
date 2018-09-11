package com.yunxin.cb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(value="结算",description="结算VO对象")
public class SettlementVO {

    /**
     * 地址ID
     */
    @ApiModelProperty(value="地址ID",name="addressId", required = true)
    private int addressId;

    /**
     * 商家信息
     */
    @NotNull(message = "商家信息不能为空")
    @ApiModelProperty(value="商家信息",name="sellerListVO", required = true)
    private List<SettlementSellerVO> sellerListVO;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public List<SettlementSellerVO> getSellerListVO() {
        return sellerListVO;
    }

    public void setSellerListVO(List<SettlementSellerVO> sellerListVO) {
        this.sellerListVO = sellerListVO;
    }

    @Override
    public String toString() {
        return "SettlementVO{" +
                "addressId=" + addressId +
                ", sellerListVO=" + sellerListVO +
                '}';
    }
}
