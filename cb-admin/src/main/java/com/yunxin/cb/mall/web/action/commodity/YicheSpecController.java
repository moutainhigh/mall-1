package com.yunxin.cb.mall.web.action.commodity;

import com.yunxin.cb.mall.web.vo.ResponseResult;
import com.yunxin.cb.mall.web.vo.ResultType;
import com.yunxin.cb.util.HttpsUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tanggangyi
 * 商品规格通过爬虫从汽车报价大全匹配自动填充
 */
@Controller
@RequestMapping(value = "/commodity/specAuto")
public class YicheSpecController {

    private String BASE_URL = "http://car.m.yiche.com";
    private String SEARCH_URL = "http://so.m.yiche.com/chexing/";
    private String CONFIG_URL = "https://car.m.autohome.com.cn/ashx/car/GetModelConfig1.ashx?ids=";

    private Logger logger = LoggerFactory.getLogger(YicheSpecController.class);

    @RequestMapping(value = "yicheSpecs", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult yicheSpecs(@RequestParam("keyword") String keyword, @RequestParam(value = "specUrl", required = false) String specUrl) {
        try {
            if (StringUtils.isBlank(specUrl)) {
                Connection connect = Jsoup.connect(SEARCH_URL + keyword);
                //通过关键字搜索
                Connection.Response res = connect.ignoreContentType(true).method(Connection.Method.GET).execute();// 执行请求
                Document document = res.parse();

                Element selectFirst = document.selectFirst("#loadMoreList > li > div > div > a");
                if (selectFirst != null) {
                    String carDetailUrl = selectFirst.attr("href");
                    //通过搜索获取汽车详情url
                    Connection.Response typeRes = Jsoup.connect(carDetailUrl).ignoreContentType(true).method(Connection.Method.GET).execute();// 执行请求;
                    Element typeElement = typeRes.parse().selectFirst("#yearDiv0 > div.car-card > ul > li");
                    String typeUrl = BASE_URL + typeElement.select("a.car-info").attr("href");
                    //从汽车详情页获取第一个汽车版本
                    Connection.Response detailRes = Jsoup.connect(typeUrl).ignoreContentType(true).method(Connection.Method.GET).execute();// 执行请求;
                    Elements elements = detailRes.parse().select("div.first-tags > ul > li > a");
                    specUrl = BASE_URL + elements.get(2).attr("href");
                } else {
                    return new ResponseResult(ResultType.FAILURE, "未找到相关汽车信息");
                }
            }
            //汽车配置参数
            Connection.Response specRes = Jsoup.connect(specUrl).ignoreContentType(true).method(Connection.Method.GET).execute();// 执行请求;
            Elements elements = specRes.parse().select("div.chek-peizhi > table > tbody > tr");
            if (elements == null) {
                return new ResponseResult(ResultType.FAILURE, "未找到相关汽车参数配置信息");
            }
            Map<String, String> data = new HashMap<>();
            elements.forEach(element -> {
                Elements childrenTds = element.children();
                if(childrenTds != null && childrenTds.size() > 1){
                    data.put(childrenTds.get(0).text(), childrenTds.get(1).text());
                }
            });
            return new ResponseResult(data);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseResult(ResultType.FAILURE, "未找到相关汽车参数配置信息");
        }
    }

}
