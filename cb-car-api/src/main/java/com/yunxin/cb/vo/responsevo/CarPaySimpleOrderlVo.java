package com.yunxin.cb.vo.responsevo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/** 
 * 支付订单页VO
 * @author lxc
 * @date	2018-09-10 11:38
 */
@ApiModel(value="支付订单页VO",description="支付订单页VO_VO对象 CarPaySimpleOrderlVo")
public class CarPaySimpleOrderlVo implements Serializable {

	private static final long serialVersionUID = 1l;

	//订单编号
	@ApiModelProperty(value="订单编号",name="orderCode",example="1")
	private String orderCode;
	//支付金额
	@ApiModelProperty(value="支付金额",name="earnest",example="5000")
	private BigDecimal earnest;
	//交易单号
	@ApiModelProperty(value="交易单号",name="transactionNum",example="3251148845412855")
	private String transactionNum;
	//剩余时间(格式:时间戳)
	@ApiModelProperty(value="剩余时间(格式:时间戳)",name="remainingTime",example="14212325152")
	private Long remainingTime;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public BigDecimal getEarnest() {
		return earnest;
	}

	public void setEarnest(BigDecimal earnest) {
		this.earnest = earnest;
	}

	public String getTransactionNum() {
		return transactionNum;
	}

	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}

	public Long getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(Long remainingTime) {
		this.remainingTime = remainingTime;
	}

	@Override
	public String toString() {
		return "CarPaySimpleOrderlVo{" +
				"orderCode='" + orderCode + '\'' +
				", earnest=" + earnest +
				", transactionNum='" + transactionNum + '\'' +
				", remainingTime=" + remainingTime +
				'}';
	}
}
