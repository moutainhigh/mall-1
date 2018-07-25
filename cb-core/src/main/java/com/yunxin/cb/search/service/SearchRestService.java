package com.yunxin.cb.search.service;


import com.yunxin.cb.monitor.entity.Device;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @Author: tanggangyi
 **/
public interface SearchRestService {

    @POST("rest/attend/addDevice")
    Call<Boolean> addCommodity(@Body Device device);


}