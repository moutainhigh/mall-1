package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @title: 价格段VO
 * @auther: eleven
 * @date: 2018/7/24 17:32
 */
@ApiModel(value="价格段VO ",description="价格段VO 对象 PriceSection")
public class PriceSectionVo implements java.io.Serializable{

    private static final long serialVersionUID = -3691974480722456044L;

    /** 价格段id */
    @ApiModelProperty(value="价格段id",name="sectionId",example="1")
    private Integer sectionId;

    /** 结束价格 */
    @ApiModelProperty(value="结束价格",name="endPrice",example="239888")
    private Integer endPrice;

    /** 备注 */
    @ApiModelProperty(value="备注",name="remark",example="remark")
    private String remark;

    /** 价格段编号 */
    @ApiModelProperty(value="价格段编号",name="sectionNo",example="12225")
    private String sectionNo;

    /** 起始价格 */
    @ApiModelProperty(value="起始价格",name="startPrice",example="169888")
    private Integer startPrice;

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Integer endPrice) {
        this.endPrice = endPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSectionNo() {
        return sectionNo;
    }

    public void setSectionNo(String sectionNo) {
        this.sectionNo = sectionNo;
    }

    public Integer getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Integer startPrice) {
        this.startPrice = startPrice;
    }

    @Override
    public String toString() {
        return "PriceSectionVo{" +
                "sectionId=" + sectionId +
                ", endPrice=" + endPrice +
                ", remark='" + remark + '\'' +
                ", sectionNo='" + sectionNo + '\'' +
                ", startPrice=" + startPrice +
                '}';
    }
}
