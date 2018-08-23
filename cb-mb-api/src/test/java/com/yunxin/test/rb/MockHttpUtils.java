package com.yunxin.test.rb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yunxin.cb.Application;
import com.yunxin.cb.jwt.JwtUtil;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.util.LogicUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Map;

/**
 * @Auther: yangzhen
 * @Date: 2018/8/22 16:26
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class MockHttpUtils {

    private static final Logger log = LoggerFactory.getLogger(MockHttpUtils.class);

    private final String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxODA4NjQ2MDAwMyIsImV4cCI6MTUzNTQ1MDY4NCwianRpIjoiNTMzIn0.FK3d6ofCa1kgr8wB1uyO50Xs7LqYmfLfLnvXnzSdA8TkMcLYl6CFaIPbG8q98W8fnYsiHm0zyr5m8fN9DSIfAg";

    protected MockMvc mvc;

    /**
     * 功能描述: POST请求
     *
     * @param url 请求路径
     * @param content 输入参数
     * @param contentType 数据格式
     * @param acceptStatus 期望请求结果码
     * @param map token
     * @return:
     * @auther: yangzhen
     * @date: 2018/8/22 17:47
     */
    public void commonMvcPerFormPost(String url,
                                     String content, String contentType, Object acceptStatus,Map<String,Object>map)throws Exception{
        commonMvcPerForm("post",url,content,contentType,acceptStatus,map);
    }

    /**
     * 功能描述: GET请求
     *
     * @param url 请求路径
     * @param content 输入参数
     * @param contentType 数据格式
     * @param acceptStatus 期望请求结果码
     * @param map token
     * @return:
     * @auther: yangzhen
     * @date: 2018/8/22 17:47
     */
    public void commonMvcPerFormGet(String url,
                                    String content,String contentType,Object acceptStatus,Map<String,Object>map)throws Exception{
        commonMvcPerForm("get",url,content,contentType,acceptStatus,map);
    }
    /**
     * 功能描述: PUT请求
     *
     * @param url 请求路径
     * @param content 输入参数
     * @param contentType 数据格式
     * @param acceptStatus 期望请求结果码
     * @param map token
     * @return:
     * @auther: yangzhen
     * @date: 2018/8/22 17:47
     */
    public void commonMvcPerFormPut(String url,
                                    String content,String contentType,Object acceptStatus,Map<String,Object>map)throws Exception{
        commonMvcPerForm("put",url,content,contentType,acceptStatus,map);

    }
    /**
     * 功能描述: DELETE请求
     *
     * @param url 请求路径
     * @param content 输入参数
     * @param contentType 数据格式
     * @param acceptStatus 期望请求结果码
     * @param map token
     * @return:
     * @auther: yangzhen
     * @date: 2018/8/22 17:47
     */
    public void commonMvcPerFormDelete(String url,
                                    String content,String contentType,Object acceptStatus,Map<String,Object>map)throws Exception{
        commonMvcPerForm("delete",url,content,contentType,acceptStatus,map);

    }


    public void commonMvcPerForm(String perFormType,String url,
                                     String content,String contentType,Object acceptStatus,Map<String,Object>map)throws Exception{

        MockHttpServletRequestBuilder requestBuilder;
        if(!StringUtils.isEmpty(perFormType) && "post".equals(perFormType)){
            requestBuilder = MockMvcRequestBuilders.post(url);
        }else if(!StringUtils.isEmpty(perFormType) && "get".equals(perFormType)){
            requestBuilder = MockMvcRequestBuilders.get(url);
        }else if(!StringUtils.isEmpty(perFormType) && "put".equals(perFormType)){
            requestBuilder = MockMvcRequestBuilders.put(url);
        }else{
            requestBuilder = MockMvcRequestBuilders.delete(url);
        }
        if(!StringUtils.isEmpty(content) || !"".equals(content)){
            requestBuilder.content(content);
        }
        requestBuilder.header(JwtUtil.HEADER_STRING,token);
        requestBuilder.contentType(contentType);
        if(null != map && !map.isEmpty()){
            for (String paramName:map.keySet()){
                requestBuilder.param(paramName,String.valueOf(map.get(paramName)));
            }
        }
        MvcResult mvcResult = mvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        JSONObject resultJson =  JSONObject.parseObject(result);
        Assert.assertEquals(acceptStatus, resultJson.get("result"));
        log.info("响应内容：" + mvcResult.getResponse().getContentAsString());

    }

}
