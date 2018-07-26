package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.meta.PaymentType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

/**
 * @title: 临时订单VO
 * @auther: gws
 * @date: 2018/7/24 16:00
 */
@ApiModel(value="临时订单VO",description="临时订单VO TempOrderVo")
public class TempOrderVO implements java.io.Serializable{

    private static final long serialVersionUID = 1872359766814469998L;

    /** 商品ID */
    @ApiModelProperty(value="商品ID",name="commodityId",example="1")
    private Integer commodityId;

    /** 商品编码 */
    @ApiModelProperty(value="商品编码",name="commodityCode",example="121233485")
    private String commodityCode;

    /** 商品名 */
    @ApiModelProperty(value="商品名",name="commodityName",example="BMWX1")
    private String commodityName;

    /** 商品标题 */
    @ApiModelProperty(value="商品标题",name="commodityTitle",example="BMWX1")
    private String commodityTitle;

    /** 简称 */
    @ApiModelProperty(value="简称",name="shortName",example="BMW")
    private String shortName;

    /** 规格及参数 */
    @ApiModelProperty(value="规格及参数",name="specs",example="厂商：华晨宝马")
    private Map specs;

    /** 支付方式 */
    @ApiModelProperty(value="支付方式",name="paymentType",example="0：全款购车")
    private PaymentType paymentType;

    /** 购买货品信息 */
    @ApiModelProperty(value="购买货品信息",name="productVo",example="货品")
    private TempOrderItemVO tempOrderItemVO;

    /** 商家 */
    @ApiModelProperty(value="商家",name="sellerVo",example="商家")
    private SellerVo sellerVo;

    /** 默认收货地址 */
    @ApiModelProperty(value="默认收货地址",name="deliveryAddressVO",example="默认收货地址")
    private DeliveryAddressVO deliveryAddressVO;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityTitle() {
        return commodityTitle;
    }

    public void setCommodityTitle(String commodityTitle) {
        this.commodityTitle = commodityTitle;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Map getSpecs() {
        return specs;
    }

    public void setSpecs(Map specs) {
        this.specs = specs;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public TempOrderItemVO getTempOrderItemVO() {
        return tempOrderItemVO;
    }

    public void setTempOrderItemVO(TempOrderItemVO tempOrderItemVO) {
        this.tempOrderItemVO = tempOrderItemVO;
    }

    public SellerVo getSellerVo() {
        return sellerVo;
    }

    public void setSellerVo(SellerVo sellerVo) {
        this.sellerVo = sellerVo;
    }

    public DeliveryAddressVO getDeliveryAddressVO() {
        return deliveryAddressVO;
    }

    public void setDeliveryAddressVO(DeliveryAddressVO deliveryAddressVO) {
        this.deliveryAddressVO = deliveryAddressVO;
    }

    @Override
    public String toString() {
        return "TempOrderVO{" +
                "commodityId=" + commodityId +
                ", commodityCode='" + commodityCode + '\'' +
                ", commodityName='" + commodityName + '\'' +
                ", commodityTitle='" + commodityTitle + '\'' +
                ", shortName='" + shortName + '\'' +
                ", specs=" + specs +
                ", paymentType=" + paymentType +
                ", tempOrderItemVO=" + tempOrderItemVO +
                ", sellerVo=" + sellerVo +
                ", deliveryAddressVO=" + deliveryAddressVO +
                '}';
    }
}
