package com.yunxin.cb.mall.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.yunxin.cb.mall.common.Entity;

/** 
 * 代理商(卖家,商家) 数据实体类
 */
public class CarAgency extends Entity<Integer> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	//ID
	private Integer id;
	//商家名称
	private String agencyName;
	//联系人
	private String contacter;
	//联系电话
	private String phone;
	//邮箱
	private String email;
	//所属城市编码（关联开通城市表的主键）
	private String belongCityId;
	//所属城市名称
	private String belongCityName;
	//营业类型(有1:4S店,有2车辆评估; 1,2代表两种)。
	private String businessType;
	//代理费
	private BigDecimal agencyFee;
	//代理费是否已付
	private Boolean isPay;
	//详细地址
	private String detailAddress;
	//介绍
	private String introduction;
	//商家省编码
	private String provinceId;
	//商家市编码
	private String cityId;
	//商家区编码
	private String districtId;
	//商家经度
	private Double positionX;
	//商家纬度
	private Double positionY;
	//是否启用(0否,1是)
	private Boolean enabled;
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
	//营业执照名称
	private String busName;
	//营业执照注册号
	private String usslicenseNo;
	//是否逻辑删除（0：否；1：是）
	private Integer isDelete;
	//操作人ID
	private Integer operateId;
	
	/*Auto generated properties end*/
	
	
	
	/*Customized properties start*/
	
	/*Customized properties end*/
	
	
	
	/*Auto generated methods start*/
	
	@Override
	public Integer getPK(){
		return id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public String getAgencyName(){
		return agencyName;
	}
	
	public void setAgencyName(String agencyName){
		this.agencyName = agencyName;
	}
	
	public String getContacter(){
		return contacter;
	}
	
	public void setContacter(String contacter){
		this.contacter = contacter;
	}
	
	public String getPhone(){
		return phone;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getBelongCityId(){
		return belongCityId;
	}
	
	public void setBelongCityId(String belongCityId){
		this.belongCityId = belongCityId;
	}
	
	public String getBelongCityName(){
		return belongCityName;
	}
	
	public void setBelongCityName(String belongCityName){
		this.belongCityName = belongCityName;
	}
	
	public String getBusinessType(){
		return businessType;
	}
	
	public void setBusinessType(String businessType){
		this.businessType = businessType;
	}
	
	public BigDecimal getAgencyFee(){
		return agencyFee;
	}
	
	public void setAgencyFee(BigDecimal agencyFee){
		this.agencyFee = agencyFee;
	}
	
	public Boolean getIsPay(){
		return isPay;
	}
	
	public void setIsPay(Boolean isPay){
		this.isPay = isPay;
	}
	
	public String getDetailAddress(){
		return detailAddress;
	}
	
	public void setDetailAddress(String detailAddress){
		this.detailAddress = detailAddress;
	}
	
	public String getIntroduction(){
		return introduction;
	}
	
	public void setIntroduction(String introduction){
		this.introduction = introduction;
	}
	
	public String getProvinceId(){
		return provinceId;
	}
	
	public void setProvinceId(String provinceId){
		this.provinceId = provinceId;
	}
	
	public String getCityId(){
		return cityId;
	}
	
	public void setCityId(String cityId){
		this.cityId = cityId;
	}
	
	public String getDistrictId(){
		return districtId;
	}
	
	public void setDistrictId(String districtId){
		this.districtId = districtId;
	}
	
	public Double getPositionX(){
		return positionX;
	}
	
	public void setPositionX(Double positionX){
		this.positionX = positionX;
	}
	
	public Double getPositionY(){
		return positionY;
	}
	
	public void setPositionY(Double positionY){
		this.positionY = positionY;
	}
	
	public Boolean getEnabled(){
		return enabled;
	}
	
	public void setEnabled(Boolean enabled){
		this.enabled = enabled;
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
	
	public String getBusName(){
		return busName;
	}
	
	public void setBusName(String busName){
		this.busName = busName;
	}
	
	public String getUsslicenseNo(){
		return usslicenseNo;
	}
	
	public void setUsslicenseNo(String usslicenseNo){
		this.usslicenseNo = usslicenseNo;
	}
	
	public Integer getIsDelete(){
		return isDelete;
	}
	
	public void setIsDelete(Integer isDelete){
		this.isDelete = isDelete;
	}
	
	public Integer getOperateId(){
		return operateId;
	}
	
	public void setOperateId(Integer operateId){
		this.operateId = operateId;
	}
	
	
	/*Auto generated methods end*/
	
	
	
	/*Customized methods start*/
	
	/*Customized methods end*/
	
	
	@Override
	public String toString() {
		return "CarAgency ["
		 + "id = " + id + ", agencyName = " + agencyName + ", contacter = " + contacter + ", phone = " + phone
		 + ", email = " + email + ", belongCityId = " + belongCityId + ", belongCityName = " + belongCityName + ", businessType = " + businessType
		 + ", agencyFee = " + agencyFee + ", isPay = " + isPay + ", detailAddress = " + detailAddress + ", introduction = " + introduction
		 + ", provinceId = " + provinceId + ", cityId = " + cityId + ", districtId = " + districtId + ", positionX = " + positionX
		 + ", positionY = " + positionY + ", enabled = " + enabled + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd + ", updateTime = " + updateTime + ", updateTimeStart = " + updateTimeStart + ", updateTimeEnd = " + updateTimeEnd
		 + ", busName = " + busName + ", usslicenseNo = " + usslicenseNo + ", isDelete = " + isDelete + ", operateId = " + operateId
		+"]";
	}
}
