package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.restful.ResponseResult;
import com.yunxin.cb.mall.vo.SearchResultVo;
import com.yunxin.cb.mall.vo.SearchVo;
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
    @POST(value = "mall/search/keywordSearch/{page}/{size}")
    Call<ResponseResult<SearchResultVo>> keywordSearch(@Field("keyword") String keyword, @Field("page") int page, @Field("size") int size);
    /**
     * 分类/条件搜索
     *
     * @param
     * @param
     * @return
     * @throws Exception
     */
    @POST(value = "mall/search/categorySearch/{page}/{size}")
    Call <ResponseResult<SearchResultVo>> categorySearch(@Body SearchVo searchVo, @Path("page") int page, @Path("size") int size) throws Exception;
}
