package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Auther: hfy
 * @Date: 2018/9/10 15:34
 * @Description: 售后服务详情VO
 */
@ApiModel(value="售后服务详情VO",description="售后服务详情VO AfterSaleServeDetailVO")
public class AfterSaleServeDetailVO implements Serializable {


    private static final long serialVersionUID = 8606288410429799768L;

    public AfterSaleServeDetailVO() {
    }

    public AfterSaleServeDetailVO(Integer afterSaleServeId, Integer afterSaleServeCode, String sellerName, Integer sellerId, List<AfterSaleCommodityVO> afterSaleCommodityList, Integer afterSaleServeState, Date createTime, Date disposeEndTime, Integer afterSaleServeType, String reason, String problemDescription, String auditMessage, Date auditTime, String deliveryAddress, String deliveryPerson, String deliveryPhone) {
        this.afterSaleServeId = afterSaleServeId;
        this.afterSaleServeCode = afterSaleServeCode;
        this.sellerName = sellerName;
        this.sellerId = sellerId;
        this.afterSaleCommodityList = afterSaleCommodityList;
        this.afterSaleServeState = afterSaleServeState;
        this.createTime = createTime;
        this.disposeEndTime = disposeEndTime;
        this.afterSaleServeType = afterSaleServeType;
        this.reason = reason;
        this.problemDescription = problemDescription;
        this.auditMessage = auditMessage;
        this.auditTime = auditTime;
        this.deliveryAddress = deliveryAddress;
        this.deliveryPerson = deliveryPerson;
        this.deliveryPhone = deliveryPhone;
    }

    /** 售后服务ID */
    @ApiModelProperty(value="售后ID",name="afterSaleServeId",example="售后ID：1")
    private Integer afterSaleServeId;

    /** 售后服务单号 */
    @ApiModelProperty(value="售后服务单号",name="afterSaleServeCode",example="售后服务单号：232211112323")
    private Integer afterSaleServeCode;

    /** 商家名称 **/
    @ApiModelProperty(value="商家名称",name="sellerName",example="商家名称：汽车店")
    private String sellerName;

    /** 商家ID **/
    @ApiModelProperty(value="商家ID",name="sellerId",example="商家ID：1")
    private Integer sellerId;

    /** 售后商品列表 **/
    @ApiModelProperty(value="售后商品列表",name="afterSaleCommodityList",example="售后商品集合")
    private List<AfterSaleCommodityVO> afterSaleCommodityList;

    /** 售后服务状态 0：申请售后，1：售后中，2：售后完成，3：审核不通过 */
    @ApiModelProperty(value="售后服务状态",name="afterSaleServeState",example="0：申请售后，1：售后中，2：售后完成，3：审核不通过")
    private Integer afterSaleServeState;

    /** 创建时间（申请时间） */
    @ApiModelProperty(value="申请时间",name="createTime",example="申请时间：xxxx-xx-xx xx:xx:xx")
    private Date createTime;

    /** 卖家处理结束时间 */
    @ApiModelProperty(value="卖家处理结束时间",name="disposeEndTime",example="卖家处理结束时间:xxxx-xx-xx xx:xx:xx")
    private Date disposeEndTime;

    /** 售后服务类型 退款：0,退货：1，换货：2 */
    @ApiModelProperty(value="售后服务类型",name="afterSaleServeType",example="售后服务类型 退款：0,退货：1，换货：2")
    private Integer afterSaleServeType;

    /** 售后原因 */
    @ApiModelProperty(value="售后原因",name="reason",example="售后原因：不买了")
    private String reason;

    /** 问题描述 */
    @ApiModelProperty(value="问题描述",name="problemDescription",example="问题描述：质量有问题")
    private String problemDescription;

    /** 审核留言 **/
    @ApiModelProperty(value="审核留言",name="auditMessage",example="审核留言：审核不给通过啊")
    private String auditMessage;

    /** 审核时间 **/
    @ApiModelProperty(value="审核时间",name="auditTime",example="审核时间：xxxx-xx-xx xx:xx:xx")
    private Date auditTime;

    /** 收货地址 **/
    @ApiModelProperty(value="收货地址",name="deliveryAddress",example="收货地址：广东省深圳市福田区少年宫")
    private String deliveryAddress;

    /** 收货人 **/
    @ApiModelProperty(value="收货人",name="deliveryPerson",example="收货人：张三")
    private String deliveryPerson;

    /** 收货人手机号码 **/
    @ApiModelProperty(value="收货人手机号码",name="deliveryPhone",example="收货人手机号码：13608706654")
    private String deliveryPhone;

    /** 换货成功物流公司编码 */
    @ApiModelProperty(value="换货成功物流公司编码",name="courierNumber",example="换货成功物流公司编码：1233123")
    private String courierNumber;

    public Integer getAfterSaleServeId() {
        return afterSaleServeId;
    }

    public void setAfterSaleServeId(Integer afterSaleServeId) {
        this.afterSaleServeId = afterSaleServeId;
    }

    public Integer getAfterSaleServeCode() {
        return afterSaleServeCode;
    }

    public void setAfterSaleServeCode(Integer afterSaleServeCode) {
        this.afterSaleServeCode = afterSaleServeCode;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public List<AfterSaleCommodityVO> getAfterSaleCommodityList() {
        return afterSaleCommodityList;
    }

    public void setAfterSaleCommodityList(List<AfterSaleCommodityVO> afterSaleCommodityList) {
        this.afterSaleCommodityList = afterSaleCommodityList;
    }

    public Integer getAfterSaleServeState() {
        return afterSaleServeState;
    }

    public void setAfterSaleServeState(Integer afterSaleServeState) {
        this.afterSaleServeState = afterSaleServeState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDisposeEndTime() {
        return disposeEndTime;
    }

    public void setDisposeEndTime(Date disposeEndTime) {
        this.disposeEndTime = disposeEndTime;
    }

    public Integer getAfterSaleServeType() {
        return afterSaleServeType;
    }

    public void setAfterSaleServeType(Integer afterSaleServeType) {
        this.afterSaleServeType = afterSaleServeType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getAuditMessage() {
        return auditMessage;
    }

    public void setAuditMessage(String auditMessage) {
        this.auditMessage = auditMessage;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(String deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    public String getDeliveryPhone() {
        return deliveryPhone;
    }

    public void setDeliveryPhone(String deliveryPhone) {
        this.deliveryPhone = deliveryPhone;
    }

    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber;
    }
}
