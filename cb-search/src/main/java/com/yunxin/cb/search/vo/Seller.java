package com.yunxin.cb.search.vo;

import com.yunxin.cb.search.vo.meta.SellerType;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 供应商
 */
public class Seller implements java.io.Serializable {
    /****/
    @ApiModelProperty(value="商家id",name="sellerId",example="1")
    private int sellerId;
    /**
     * 商家编码
     **/
    @ApiModelProperty(value="商家编码",name="sellerCode",example="store001")
    private String sellerCode;
    /**
     * 商家名称
     **/
    @ApiModelProperty(value="商家名称",name="sellerName",example="XX4s店")
    @Field(type = FieldType.Text,fielddata = true, searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String sellerName;

    /**
     * 商家类型
     **/
    @ApiModelProperty(value="商家类型",name="sellerType",example="枚举：SELLER(‘商家’), SELF_OPERATION(‘自营’)")
    private SellerType sellerType;

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public SellerType getSellerType() {
        return sellerType;
    }

    public void setSellerType(SellerType sellerType) {
        this.sellerType = sellerType;
    }
}
