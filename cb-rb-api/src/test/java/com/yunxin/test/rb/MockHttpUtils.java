package com.yunxin.test.rb;

import com.alibaba.fastjson.JSONObject;
import com.yunxin.cb.Application;
import com.yunxin.cb.jwt.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

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

    @Autowired
    protected WebApplicationContext context;

    private final String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzQwNzE1MTUwNiIsImV4cCI6MTUzNjExNjQ5OCwianRpIjoiMzA2In0.qe35nHUZdu804DS3R9YaCE4c0Cm29ZKMhPiEhvOjBb149NY43OSp3Z2dmx5f7UgxbNKMzrOFDnXwGwk06w-6LQ";

    protected MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        //MockMvcBuilders使用构建MockMvc对象   （项目拦截器有效）
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    /**
     * 功能描述: POST请求
     *
     * @param url 请求路径
     * @param content json参数
     * @param contentType 数据格式
     * @param acceptStatus 期望请求结果码
     * @param params 表单参数
     * @return:
     * @auther: yangzhen
     * @date: 2018/8/22 17:47
     */
    public void commonMvcPerFormPost(String url,
                                     String content, String contentType, Object acceptStatus, MultiValueMap<String, String> params)throws Exception{
        commonMvcPerForm(HttpMethod.POST, url, content, contentType, acceptStatus, params);
    }

    /**
     * 功能描述: GET请求
     *
     * @param url 请求路径
     * @param content json参数
     * @param contentType 数据格式
     * @param acceptStatus 期望请求结果码
     * @param params 表单参数
     * @return:
     * @auther: yangzhen
     * @date: 2018/8/22 17:47
     */
    public void commonMvcPerFormGet(String url,
                                    String content,String contentType,Object acceptStatus,MultiValueMap<String, String> params)throws Exception{
        commonMvcPerForm(HttpMethod.GET, url, content, contentType, acceptStatus, params);
    }
    /**
     * 功能描述: PUT请求
     *
     * @param url 请求路径
     * @param content json参数
     * @param contentType 数据格式
     * @param acceptStatus 期望请求结果码
     * @param params 表单参数
     * @return:
     * @auther: yangzhen
     * @date: 2018/8/22 17:47
     */
    public void commonMvcPerFormPut(String url,
                                    String content,String contentType,Object acceptStatus,MultiValueMap<String, String> params)throws Exception{
        commonMvcPerForm(HttpMethod.PUT, url, content, contentType, acceptStatus, params);

    }
    /**
     * 功能描述: DELETE请求
     *
     * @param url 请求路径
     * @param content json参数
     * @param contentType 数据格式
     * @param acceptStatus 期望请求结果码
     * @param params 表单参数
     * @return:
     * @auther: yangzhen
     * @date: 2018/8/22 17:47
     */
    public void commonMvcPerFormDelete(String url,
                                       String content,String contentType,Object acceptStatus, MultiValueMap<String, String> params)throws Exception{
        commonMvcPerForm(HttpMethod.DELETE, url, content, contentType, acceptStatus, params);

    }

    /**
     * 功能描述: 请求
     *
     * @param url 请求路径
     * @param content json参数
     * @param contentType 数据格式
     * @param acceptStatus 期望请求结果码
     * @param params 表单参数
     * @return:
     * @auther: yangzhen
     * @date: 2018/8/22 17:47
     */
    public void commonMvcPerForm(HttpMethod httpMethod, String url,
                                 String content, String contentType, Object acceptStatus, MultiValueMap<String, String> params)throws Exception{
        MockHttpServletRequestBuilder requestBuilder;
        if(httpMethod != null){
            requestBuilder = MockMvcRequestBuilders.request(httpMethod, url);
        } else{
            requestBuilder = MockMvcRequestBuilders.post(url);
        }
        if(StringUtils.isNotBlank(content)){
            requestBuilder.content(content);
        }
        requestBuilder.header(JwtUtil.HEADER_STRING, token);
        requestBuilder.contentType(contentType);
        if (params != null && !params.isEmpty()) {
            requestBuilder.params(params);
        }
        MvcResult mvcResult = mvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        JSONObject resultJson =  JSONObject.parseObject(mvcResult.getResponse().getContentAsString());
        Assert.assertEquals(acceptStatus, resultJson.get("result"));
        log.info("响应内容：" + mvcResult.getResponse().getContentAsString());
    }

}
