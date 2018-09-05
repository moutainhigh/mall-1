package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.restful.ResponseResult;
import retrofit2.Call;
import retrofit2.http.*;

public interface SearchRestService {
    /**
     * 关键字搜索
     * @param keyword
     * @param page
     * @param size
     * @return
     */
    @FormUrlEncoded
    @POST(value = "mall/search/keywordSearch")
    Call<ResponseResult> keywordSearch(@Field("keyword") String keyword, @Field("page") int page, @Field("size") int size,
                                       @Field("lat") Double lat, @Field("lon") Double lon);
    /**
     * 分类/条件搜索
     *
     * @param
     * @param
     * @return
     * @throws Exception
     */
    @POST(value = "mall/search/categorySearch")
    Call <ResponseResult> categorySearch(@Body Object searchVo);

    /**
     * 查询所有规格属性等
     * @return
     */
    @GET(value = "mall/search/commodity")
    Call<ResponseResult> selectAll();
}
