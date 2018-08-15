package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * wangteng 个人中心数据统计
 */
@ApiModel(value="个人中心数据统计",description="个人中心数据统计 MyTotalVo")
public class MyTotalVo  implements java.io.Serializable {
    @ApiModelProperty(value="人脉",name="interpersonalTotal",example="38")
    private int interpersonalTotal;
    @ApiModelProperty(value="收藏",name="favoriteTotal",example="38")
    private int favoriteTotal;
    @ApiModelProperty(value="足迹",name="recordTotal",example="38")
    private int recordTotal;

    public int getInterpersonalTotal() {
        return interpersonalTotal;
    }

    public void setInterpersonalTotal(int interpersonalTotal) {
        this.interpersonalTotal = interpersonalTotal;
    }

    public int getFavoriteTotal() {
        return favoriteTotal;
    }

    public void setFavoriteTotal(int favoriteTotal) {
        this.favoriteTotal = favoriteTotal;
    }

    public int getRecordTotal() {
        return recordTotal;
    }

    public void setRecordTotal(int recordTotal) {
        this.recordTotal = recordTotal;
    }
}
