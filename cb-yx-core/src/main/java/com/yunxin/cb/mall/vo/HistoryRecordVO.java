package com.yunxin.cb.mall.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yunxin.cb.mall.entity.HistoryRecord;
import com.yunxin.cb.util.page.PageFinder;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @title: 我的浏览VO
 * @auther: eleven
 * @date: 2018/8/14 11:36
 */
public class HistoryRecordVO implements Serializable {
    private static final long serialVersionUID = -1457381452137741650L;
    /**  */
    @ApiModelProperty(value="浏览id",name="recordId",example="1")
    private Integer recordId;

    /** 创建时间 */
    @ApiModelProperty(value="创建时间",name="createTime",example="2018-07-24 15:02:32")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 销售价 */
    @ApiModelProperty(value="销售价",name="salePrice",example="200.00")
    private Float salePrice;

    /** 商品id */
    @ApiModelProperty(value="商品id",name="commodityId",example="476")
    private Integer commodityId;

    /** 货品id */
    @ApiModelProperty(value="货品id",name="productId",example="476")
    private Integer productId;

    @ApiModelProperty(value="商品",name="commodity",example="commodity")
    private CommodityVo commodityVo;

    /** 客户id */
    @ApiModelProperty(value="客户id",name="customerId",example="1")
    private Integer customerId;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Float salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public CommodityVo getCommodityVo() {
        return commodityVo;
    }

    public void setCommodityVo(CommodityVo commodityVo) {
        this.commodityVo = commodityVo;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "HistoryRecordVO{" +
                "recordId=" + recordId +
                ", createTime=" + createTime +
                ", salePrice=" + salePrice +
                ", commodityId=" + commodityId +
                ", productId=" + productId +
                ", commodityVo=" + commodityVo +
                ", customerId=" + customerId +
                '}';
    }

    /**
     * 分页DO转换VO
     */
    public static PageFinder<HistoryRecordVO> dOconvertVOPage (PageFinder<HistoryRecord> pageFinder){
        List<HistoryRecordVO> volist = new ArrayList<>();
        if (pageFinder != null) {
            try {
                List<HistoryRecord> list = pageFinder.getData();
                for (HistoryRecord hr:list){
                    SellerVo sellerVo=new SellerVo();
                    BeanUtils.copyProperties(sellerVo,hr.getCommodity().getSeller());
                    CommodityVo commodityVo=new CommodityVo();
                    BeanUtils.copyProperties(commodityVo,hr.getCommodity());
                    HistoryRecordVO historyRecordVO=new HistoryRecordVO();
                    BeanUtils.copyProperties(historyRecordVO,hr);
                    commodityVo.setSellerVo(sellerVo);
                    historyRecordVO.setCommodityVo(commodityVo);
                    volist.add(historyRecordVO);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        PageFinder<HistoryRecordVO> page = new PageFinder<>(pageFinder.getPageNo(), pageFinder.getPageSize(), pageFinder.getRowCount());
        page.setData(volist);
        return page;
    }
}
