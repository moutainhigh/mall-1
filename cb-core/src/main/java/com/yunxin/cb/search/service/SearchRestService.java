package com.yunxin.cb.search.service;


import com.yunxin.cb.search.vo.CommodityVO;
import com.yunxin.cb.search.vo.ResponseResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 * @Author: tanggangyi
 **/
public interface SearchRestService {

    /**
     * 添加商品
     * @param commodityVo
     * @return
     */
    @POST("mall/search/addCommodity")
    Call<ResponseResult> addCommodity(@Body CommodityVO commodityVo);
    /**
     * 修改商品
     * @param commodityVo
     * @return
     */
    @POST("mall/search/updateCommodity")
    Call<ResponseResult> updateCommodity(@Body CommodityVO commodityVo);
    /**
     * 删除商品
     * @param commodityId
     * @return
     */
    @POST("mall/search/removeCommodity")
    Call<ResponseResult> removeCommodity(@Field("commodityId") int commodityId);
}