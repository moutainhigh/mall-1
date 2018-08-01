package com.yunxin.cb.search.service;


import com.yunxin.cb.search.vo.CommodityVO;
import com.yunxin.cb.search.vo.ResponseResult;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * @Author: tanggangyi
 **/
public interface SearchRestService {

    /**
     * 添加商品
     * @param commodityVo
     * @return
     */
    @POST("mall/search/commodity")
    Call<ResponseResult> addCommodity(@Body CommodityVO commodityVo);
    /**
     * 修改商品
     * @param commodityVo
     * @return
     */
    @PUT("mall/search/commodity")
    Call<ResponseResult> updateCommodity(@Body CommodityVO commodityVo);
    /**
     * 删除商品
     * @param commodityId
     * @return
     */
    @DELETE("mall/search/commodity/{commodityId}")
    Call<ResponseResult> removeCommodity(@Path("commodityId") int commodityId);


    @POST("mall/search/bulkIndex")
    Call<ResponseResult> bulkIndex(@Body List<CommodityVO> voList);

    /**
     * 查询商品
     * @param commodityId
     * @return
     */
    @GET("mall/search/commodity/{commodityId}")
    Call<ResponseResult<CommodityVO>> selectByCommodityId(@Path("commodityId") int commodityId);
}