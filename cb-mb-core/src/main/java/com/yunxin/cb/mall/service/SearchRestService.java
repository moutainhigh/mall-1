package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.vo.SearchVo;
import org.springframework.data.domain.Page;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

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
    Call <Page<Commodity>> keywordSearch(@Field("keyword") String keyword, @Field("page") int page, @Field("size") int size);
    /**
     * 分类/条件搜索
     *
     * @param
     * @param
     * @return
     * @throws Exception
     */
    @POST(value = "mall/search/categorySearch/{page}/{size}")
    Call <Page<Commodity>> categorySearch(@Body SearchVo searchVo,@Path("page") int page, @Path("size") int size) throws Exception;
}
