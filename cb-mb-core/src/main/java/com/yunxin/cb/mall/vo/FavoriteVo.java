package com.yunxin.cb.mall.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yunxin.cb.mall.entity.Favorite;
import com.yunxin.cb.util.page.PageFinder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @title: 收藏夹VO
 * @auther: eleven
 * @date: 2018/7/24 15:01
 */
@ApiModel(value="收藏夹",description="收藏夹VO对象 Favorite")
public class FavoriteVo implements java.io.Serializable{

    private static final long serialVersionUID = -2695946271501714063L;

    /** 收藏Id */
    @ApiModelProperty(value="收藏Id",name="favoriteId",example="1")
    private Integer favoriteId;

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

    @ApiModelProperty(value="商品",name="commodity",example="commodity")
    private CommodityVo commodityVo;

    /** 客户id */
    @ApiModelProperty(value="客户id",name="customerId",example="1")
    private Integer customerId;

    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
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

    public CommodityVo getCommodityVo() {
        return commodityVo;
    }

    public void setCommodityVo(CommodityVo commodityVo) {
        this.commodityVo = commodityVo;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "FavoriteVo{" +
                "favoriteId=" + favoriteId +
                ", createTime=" + createTime +
                ", salePrice=" + salePrice +
                ", commodityId=" + commodityId +
                ", commodity=" + commodityVo +
                ", customerId=" + customerId +
                '}';
    }

    /**
     * 分页DO转换VO
     */
    public static PageFinder<FavoriteVo> dOconvertVOPage (PageFinder<Favorite> pageFinder){
        PageFinder<FavoriteVo> page = new PageFinder<FavoriteVo> (pageFinder.getPageNo(), pageFinder.getPageSize());
        if (pageFinder != null) {
            try {
                List<Favorite> list = pageFinder.getData();
                List<FavoriteVo> volist = new ArrayList<>();
                for (Favorite fa:list){
                    CommodityVo commodityVo=new CommodityVo();
                    BeanUtils.copyProperties(commodityVo,fa.getCommodity());
                    FavoriteVo favoriteVo=new FavoriteVo();
                    BeanUtils.copyProperties(favoriteVo,fa);
                    favoriteVo.setCommodityVo(commodityVo);
                    volist.add(favoriteVo);
                }
                page.setData(volist);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        page.setRowCount(pageFinder.getRowCount());//记录总数
        page.setPageCount(pageFinder.getPageCount());//总页数
        return page;
    }
}
