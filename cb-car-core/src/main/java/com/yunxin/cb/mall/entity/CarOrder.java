package com.yunxin.cb.mall.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.yunxin.cb.mall.common.Entity;

/** 
 * 汽车订单表 数据实体类
 */
public class CarOrder extends Entity<Long> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	private Long id;
	//订单编号
	private String orderCode;
	//购买人
	private Integer customerId;
	//购买人手机号
	private String customerPhone;
	//购买类型(1全款,2置换)
	private Integer buyType;
	//商品名称
	private String carName;
	//商品总数量
	private Integer totalNum;
	//订单总额
	private BigDecimal totalPrice;
	//订金
	private BigDecimal earnest;
	//支付方式
	private Integer paymentType;
	//支付时间
	private Date paymentTime;
	//支付时间 时间范围起（查询用）
	private Date paymentTimeStart;
	//支付时间 时间范围止（查询用）
	private Date paymentTimeEnd;	
	//确认时间
	private Date confirmTime;
	//确认时间 时间范围起（查询用）
	private Date confirmTimeStart;
	//确认时间 时间范围止（查询用）
	private Date confirmTimeEnd;	
	//优惠总金额
	private BigDecimal discountTotal;
	//尾款
	private BigDecimal tailMoney;
	//尾款付款方式
	private Integer tailPaymentType;
	//尾款付款时间
	private Date tailPaymentTime;
	//尾款付款时间 时间范围起（查询用）
	private Date tailPaymentTimeStart;
	//尾款付款时间 时间范围止（查询用）
	private Date tailPaymentTimeEnd;	
	//订单状态(1未支付,2未确认,3未设置评估门店,              4未评估,5未设置二手车价格,6未付尾款,7未提车,              8交易成功.9已取消,10退款中,11退款审核通过,              12退款不通过,13确认退款)
	private Integer orderState;
	//支付状态(1未支付,2已支付,3已确认,4已设置评估门店,5已评估,              6已设置二手车价格,7已付尾款,8已提车,9已取消,              10退款中,11退款审核通过,              12退款不通过,13确认退款)
	private Integer paymentState;
	//新车ID
	private Integer carModelId;
	//裸车价
	private BigDecimal nakedCarPrice;
	//提车门店
	private Integer carAgencyLiftId;
	//车牌号
	private String carNum;
	//二手车实际评估价
	private BigDecimal usedCarActualPrice;
	//二手车信息ID
	private Integer carUsedId;
	//车辆评估门店
	private Integer carAgencyAssessId;
	//买家留言
	private String buyerMessage;
	//支付流水号
	private String paymentSequence;
	//订单完成时间
	private Date finishTime;
	//订单完成时间 时间范围起（查询用）
	private Date finishTimeStart;
	//订单完成时间 时间范围止（查询用）
	private Date finishTimeEnd;	
	//备注信息
	private String remark;
	//是否删除(0否,1是)
	private Integer isDelete;
	//CREATE_TIME
	private Date createTime;
	//CREATE_TIME 时间范围起（查询用）
	private Date createTimeStart;
	//CREATE_TIME 时间范围止（查询用）
	private Date createTimeEnd;	
	//UPDATE_TIME
	private Date updateTime;
	//UPDATE_TIME 时间范围起（查询用）
	private Date updateTimeStart;
	//UPDATE_TIME 时间范围止（查询用）
	private Date updateTimeEnd;	
	
	/*Auto generated properties end*/
	
	
	
	/*Customized properties start*/
	
	/*Customized properties end*/
	
	
	
	/*Auto generated methods start*/
	
	@Override
	public Long getPK(){
		return id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getOrderCode(){
		return orderCode;
	}
	
	public void setOrderCode(String orderCode){
		this.orderCode = orderCode;
	}
	
	public Integer getCustomerId(){
		return customerId;
	}
	
	public void setCustomerId(Integer customerId){
		this.customerId = customerId;
	}
	
	public String getCustomerPhone(){
		return customerPhone;
	}
	
	public void setCustomerPhone(String customerPhone){
		this.customerPhone = customerPhone;
	}
	
	public Integer getBuyType(){
		return buyType;
	}
	
	public void setBuyType(Integer buyType){
		this.buyType = buyType;
	}
	
	public String getCarName(){
		return carName;
	}
	
	public void setCarName(String carName){
		this.carName = carName;
	}
	
	public Integer getTotalNum(){
		return totalNum;
	}
	
	public void setTotalNum(Integer totalNum){
		this.totalNum = totalNum;
	}
	
	public BigDecimal getTotalPrice(){
		return totalPrice;
	}
	
	public void setTotalPrice(BigDecimal totalPrice){
		this.totalPrice = totalPrice;
	}
	
	public BigDecimal getEarnest(){
		return earnest;
	}
	
	public void setEarnest(BigDecimal earnest){
		this.earnest = earnest;
	}
	
	public Integer getPaymentType(){
		return paymentType;
	}
	
	public void setPaymentType(Integer paymentType){
		this.paymentType = paymentType;
	}
	
	public Date getPaymentTime(){
		return paymentTime;
	}
	
	public void setPaymentTime(Date paymentTime){
		this.paymentTime = paymentTime;
	}
	
	public Date getPaymentTimeStart(){
		return paymentTimeStart;
	}
	
	public void setPaymentTimeStart(Date paymentTimeStart){
		this.paymentTimeStart = paymentTimeStart;
	}
	
	public Date getPaymentTimeEnd(){
		return paymentTimeEnd;
	}
	
	public void setPaymentTimeEnd(Date paymentTimeEnd){
		this.paymentTimeEnd = paymentTimeEnd;
	}	
	
	public Date getConfirmTime(){
		return confirmTime;
	}
	
	public void setConfirmTime(Date confirmTime){
		this.confirmTime = confirmTime;
	}
	
	public Date getConfirmTimeStart(){
		return confirmTimeStart;
	}
	
	public void setConfirmTimeStart(Date confirmTimeStart){
		this.confirmTimeStart = confirmTimeStart;
	}
	
	public Date getConfirmTimeEnd(){
		return confirmTimeEnd;
	}
	
	public void setConfirmTimeEnd(Date confirmTimeEnd){
		this.confirmTimeEnd = confirmTimeEnd;
	}	
	
	public BigDecimal getDiscountTotal(){
		return discountTotal;
	}
	
	public void setDiscountTotal(BigDecimal discountTotal){
		this.discountTotal = discountTotal;
	}
	
	public BigDecimal getTailMoney(){
		return tailMoney;
	}
	
	public void setTailMoney(BigDecimal tailMoney){
		this.tailMoney = tailMoney;
	}
	
	public Integer getTailPaymentType(){
		return tailPaymentType;
	}
	
	public void setTailPaymentType(Integer tailPaymentType){
		this.tailPaymentType = tailPaymentType;
	}
	
	public Date getTailPaymentTime(){
		return tailPaymentTime;
	}
	
	public void setTailPaymentTime(Date tailPaymentTime){
		this.tailPaymentTime = tailPaymentTime;
	}
	
	public Date getTailPaymentTimeStart(){
		return tailPaymentTimeStart;
	}
	
	public void setTailPaymentTimeStart(Date tailPaymentTimeStart){
		this.tailPaymentTimeStart = tailPaymentTimeStart;
	}
	
	public Date getTailPaymentTimeEnd(){
		return tailPaymentTimeEnd;
	}
	
	public void setTailPaymentTimeEnd(Date tailPaymentTimeEnd){
		this.tailPaymentTimeEnd = tailPaymentTimeEnd;
	}	
	
	public Integer getOrderState(){
		return orderState;
	}
	
	public void setOrderState(Integer orderState){
		this.orderState = orderState;
	}
	
	public Integer getPaymentState(){
		return paymentState;
	}
	
	public void setPaymentState(Integer paymentState){
		this.paymentState = paymentState;
	}
	
	public Integer getCarModelId(){
		return carModelId;
	}
	
	public void setCarModelId(Integer carModelId){
		this.carModelId = carModelId;
	}
	
	public BigDecimal getNakedCarPrice(){
		return nakedCarPrice;
	}
	
	public void setNakedCarPrice(BigDecimal nakedCarPrice){
		this.nakedCarPrice = nakedCarPrice;
	}
	
	public Integer getCarAgencyLiftId(){
		return carAgencyLiftId;
	}
	
	public void setCarAgencyLiftId(Integer carAgencyLiftId){
		this.carAgencyLiftId = carAgencyLiftId;
	}
	
	public String getCarNum(){
		return carNum;
	}
	
	public void setCarNum(String carNum){
		this.carNum = carNum;
	}
	
	public BigDecimal getUsedCarActualPrice(){
		return usedCarActualPrice;
	}
	
	public void setUsedCarActualPrice(BigDecimal usedCarActualPrice){
		this.usedCarActualPrice = usedCarActualPrice;
	}
	
	public Integer getCarUsedId(){
		return carUsedId;
	}
	
	public void setCarUsedId(Integer carUsedId){
		this.carUsedId = carUsedId;
	}
	
	public Integer getCarAgencyAssessId(){
		return carAgencyAssessId;
	}
	
	public void setCarAgencyAssessId(Integer carAgencyAssessId){
		this.carAgencyAssessId = carAgencyAssessId;
	}
	
	public String getBuyerMessage(){
		return buyerMessage;
	}
	
	public void setBuyerMessage(String buyerMessage){
		this.buyerMessage = buyerMessage;
	}
	
	public String getPaymentSequence(){
		return paymentSequence;
	}
	
	public void setPaymentSequence(String paymentSequence){
		this.paymentSequence = paymentSequence;
	}
	
	public Date getFinishTime(){
		return finishTime;
	}
	
	public void setFinishTime(Date finishTime){
		this.finishTime = finishTime;
	}
	
	public Date getFinishTimeStart(){
		return finishTimeStart;
	}
	
	public void setFinishTimeStart(Date finishTimeStart){
		this.finishTimeStart = finishTimeStart;
	}
	
	public Date getFinishTimeEnd(){
		return finishTimeEnd;
	}
	
	public void setFinishTimeEnd(Date finishTimeEnd){
		this.finishTimeEnd = finishTimeEnd;
	}	
	
	public String getRemark(){
		return remark;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public Integer getIsDelete(){
		return isDelete;
	}
	
	public void setIsDelete(Integer isDelete){
		this.isDelete = isDelete;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTimeStart(){
		return createTimeStart;
	}
	
	public void setCreateTimeStart(Date createTimeStart){
		this.createTimeStart = createTimeStart;
	}
	
	public Date getCreateTimeEnd(){
		return createTimeEnd;
	}
	
	public void setCreateTimeEnd(Date createTimeEnd){
		this.createTimeEnd = createTimeEnd;
	}	
	
	public Date getUpdateTime(){
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTimeStart(){
		return updateTimeStart;
	}
	
	public void setUpdateTimeStart(Date updateTimeStart){
		this.updateTimeStart = updateTimeStart;
	}
	
	public Date getUpdateTimeEnd(){
		return updateTimeEnd;
	}
	
	public void setUpdateTimeEnd(Date updateTimeEnd){
		this.updateTimeEnd = updateTimeEnd;
	}	
	
	
	/*Auto generated methods end*/
	
	
	
	/*Customized methods start*/
	
	/*Customized methods end*/
	
	
	@Override
	public String toString() {
		return "CarOrder ["
		 + "id = " + id + ", orderCode = " + orderCode + ", customerId = " + customerId + ", customerPhone = " + customerPhone
		 + ", buyType = " + buyType + ", carName = " + carName + ", totalNum = " + totalNum + ", totalPrice = " + totalPrice
		 + ", earnest = " + earnest + ", paymentType = " + paymentType + ", paymentTime = " + paymentTime + ", paymentTimeStart = " + paymentTimeStart + ", paymentTimeEnd = " + paymentTimeEnd + ", confirmTime = " + confirmTime + ", confirmTimeStart = " + confirmTimeStart + ", confirmTimeEnd = " + confirmTimeEnd
		 + ", discountTotal = " + discountTotal + ", tailMoney = " + tailMoney + ", tailPaymentType = " + tailPaymentType + ", tailPaymentTime = " + tailPaymentTime + ", tailPaymentTimeStart = " + tailPaymentTimeStart + ", tailPaymentTimeEnd = " + tailPaymentTimeEnd
		 + ", orderState = " + orderState + ", paymentState = " + paymentState + ", carModelId = " + carModelId + ", nakedCarPrice = " + nakedCarPrice
		 + ", carAgencyLiftId = " + carAgencyLiftId + ", carNum = " + carNum + ", usedCarActualPrice = " + usedCarActualPrice + ", carUsedId = " + carUsedId
		 + ", carAgencyAssessId = " + carAgencyAssessId + ", buyerMessage = " + buyerMessage + ", paymentSequence = " + paymentSequence + ", finishTime = " + finishTime + ", finishTimeStart = " + finishTimeStart + ", finishTimeEnd = " + finishTimeEnd
		 + ", remark = " + remark + ", isDelete = " + isDelete + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd + ", updateTime = " + updateTime + ", updateTimeStart = " + updateTimeStart + ", updateTimeEnd = " + updateTimeEnd
		+"]";
	}
}
