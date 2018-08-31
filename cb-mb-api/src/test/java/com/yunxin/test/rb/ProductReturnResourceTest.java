package com.yunxin.test.rb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunxin.cb.mall.entity.meta.ReturnReason;
import com.yunxin.cb.mall.vo.ProductReturnApplyVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.mall.ProductReturnResource;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * 商城退货接口 测试类
 * add by chenpeng 2018年8月22日
 */
public class ProductReturnResourceTest extends MockHttpUtils{

    private static final Logger log = LoggerFactory.getLogger(ProductReturnResourceTest.class);
    @Autowired
    private ProductReturnResource productReturnResource;
    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(productReturnResource).build();
    }

    @Test
    public void getProductReturnData() throws Exception {
        log.info("退货申请页数据获取 V1 start");

        String url = "/v1/mall/productReturn/apply/372";
        String acceptStatus = Result.FAILURE.name();
        commonMvcPerFormGet(url, "", MediaType.APPLICATION_FORM_URLENCODED_VALUE, acceptStatus, null);
        log.info("退货申请页数据获取 V1 end ");
    }

    @Test
    public void addProductReturn() throws Exception {
        log.info("退货申请 V1 start");

        ProductReturnApplyVO vo = new ProductReturnApplyVO();
        vo.setOrderId(1);
        vo.setItemId(new Integer[]{1, 2});
        vo.setReturnReason(ReturnReason.INVOICE_PROBLEM);
        vo.setReason("我不想买了");

        String url = "/v1/mall/productReturn";
        String acceptStatus = Result.FAILURE.name();
        commonMvcPerFormPost(url, new ObjectMapper().writeValueAsString(vo), MediaType.APPLICATION_JSON_UTF8_VALUE, acceptStatus, null);
        log.info("商品分类搜索 V1 end");
    }

    @Test
    public void pageProductReturn() throws Exception {
        log.info("退货分页列表 V1 start");

        String url = "/v1/mall/productReturn/pageList";
        String acceptStatus = Result.SUCCESS.name();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("pageNo", "0");
        map.add("pageSize", "10");
        commonMvcPerFormPost(url, null, MediaType.APPLICATION_FORM_URLENCODED_VALUE, acceptStatus, map);
        log.info("退货分页列表 V1 end");
    }

    @Test
    public void getProductReturn() throws Exception {
        log.info("退货详情 V1 start");

        String url = "/v1/mall/productReturn/1";
        String acceptStatus = Result.SUCCESS.name();
        commonMvcPerFormGet(url, "", MediaType.APPLICATION_FORM_URLENCODED_VALUE, acceptStatus, null);
        log.info("退货详情 V1 end ");
    }


}
