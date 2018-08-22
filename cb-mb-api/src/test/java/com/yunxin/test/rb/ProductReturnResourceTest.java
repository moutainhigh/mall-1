package com.yunxin.test.rb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunxin.cb.Application;
import com.yunxin.cb.mall.entity.meta.ReturnReason;
import com.yunxin.cb.mall.vo.ProductReturnApplyVO;
import com.yunxin.cb.rest.mall.ProductReturnResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 商城退货接口 测试类
 * add by chenpeng 2018年8月22日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class ProductReturnResourceTest {

    private static final Logger log = LoggerFactory.getLogger(ProductReturnResourceTest.class);
    @Autowired
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ProductReturnResource productReturnResource;
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(productReturnResource).build();
    }

    @Test
    public void getProductReturnData() throws Exception {
        log.info("退货申请页数据获取 V1 start");
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.get("/v1/mall/productReturn/apply/372")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiI4ODg4ODgiLCJleHAiOjE1MzU0MjcyMTMsImp0aSI6IjEifQ.NskhiSw4EO_JlWBqEkQJmXTHiFwQLXHy8GUZsouSpfUAGl5VXH4MhHbXgPbqvurk2AUuDk0az0wHcZhNbhTQpg"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("退货申请页数据获取 V1 end result : " + content);
    }

    @Test
    public void addProductReturn() throws Exception {
        log.info("退货申请 V1 start");

        ProductReturnApplyVO vo = new ProductReturnApplyVO();
        vo.setOrderId(1);
        vo.setItemId(new Integer[]{1, 2});
        vo.setReturnReason(ReturnReason.INVOICE_PROBLEM);
        vo.setReason("我不想买了");

        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.post("/v1/mall/productReturn")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiI4ODg4ODgiLCJleHAiOjE1MzU0MjcyMTMsImp0aSI6IjEifQ.NskhiSw4EO_JlWBqEkQJmXTHiFwQLXHy8GUZsouSpfUAGl5VXH4MhHbXgPbqvurk2AUuDk0az0wHcZhNbhTQpg")
                        .content(new ObjectMapper().writeValueAsString(vo)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("商品分类搜索 V1 end result : " + content);
    }

    @Test
    public void pageProductReturn() throws Exception {
        log.info("退货分页列表 V1 start");
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.post("/v1/mall/productReturn/pageList")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiI4ODg4ODgiLCJleHAiOjE1MzU0MjcyMTMsImp0aSI6IjEifQ.NskhiSw4EO_JlWBqEkQJmXTHiFwQLXHy8GUZsouSpfUAGl5VXH4MhHbXgPbqvurk2AUuDk0az0wHcZhNbhTQpg")
                        .param("pageNo", "0").param("pageSize", "10"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("退货分页列表 V1 end result : " + content);
    }

    @Test
    public void getProductReturn() throws Exception {
        log.info("退货详情 V1 start");
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.get("/v1/mall/productReturn/1")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiI4ODg4ODgiLCJleHAiOjE1MzU0MjcyMTMsImp0aSI6IjEifQ.NskhiSw4EO_JlWBqEkQJmXTHiFwQLXHy8GUZsouSpfUAGl5VXH4MhHbXgPbqvurk2AUuDk0az0wHcZhNbhTQpg"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("退货详情 V1 end result : " + content);
    }


}
