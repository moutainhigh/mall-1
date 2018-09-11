package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Auther: hfy
 * @Date: 2018/9/11 11:06
 * @Description: 填写物流信息请求VO
 */
@ApiModel(value="填写物流信息请求VO",description="填写物流信息请求VO FillLogisticInfoRequestVO")
public class FillLogisticInfoRequestVO implements Serializable {

    private static final long serialVersionUID = -3485457226158776134L;
    /** 手机号码 */
    @ApiModelProperty(value="手机号码",name="phone",example="135xxxxxxxx,  手机号码")
    private String phone;

    /** 收件人 */
    @ApiModelProperty(value="收件人",name="phone",example="张三,  收件人")
    private String name;

    /** 收件人地址 */
    @ApiModelProperty(value="收件人地址",name="phone",example="深圳市福田区少年宫, 收件人地址")
    private String address;

    /** 快递编号 */
    @ApiModelProperty(value="快递编号",name="logisticCode",example="12345642,  快递编号")
    private String logisticCode;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogisticCode() {
        return logisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        this.logisticCode = logisticCode;
    }
}
