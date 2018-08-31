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

/**
 * @author tanggangyi
 * 商品规格通过爬虫从汽车之家匹配自动填充
 */
@Controller
@RequestMapping(value = "/commodity/specAuto")
public class AutohomeSpecController {

    private String BASE_URL = "https://m.autohome.com.cn";
    private String SEARCH_URL = "https://sou.m.autohome.com.cn/zhaoche?q=";
    private String CONFIG_URL = "https://car.m.autohome.com.cn/ashx/car/GetModelConfig1.ashx?ids=";

    private Logger logger = LoggerFactory.getLogger(AutohomeSpecController.class);

    @RequestMapping(value = "autohomeSpecs", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult autohomeSpecs(@RequestParam("keyword") String keyword, @RequestParam(value = "specUrl", required = false) String specUrl) {
        try {
            if (StringUtils.isBlank(specUrl)) {
                Connection connect = Jsoup.connect(SEARCH_URL + keyword);
                //通过关键字搜索
                Connection.Response res = connect.ignoreContentType(true).method(Connection.Method.GET).execute();// 执行请求
                Document document = res.parse();

                Element selectFirst = document.selectFirst("li.brandsale > a.info");
                if (selectFirst != null) {
                    String carDetailUrl = selectFirst.attr("href");
                    //通过搜索获取汽车详情url
                    Connection.Response typeRes = Jsoup.connect(carDetailUrl).ignoreContentType(true).method(Connection.Method.GET).execute();// 执行请求;
                    Element typeElement = typeRes.parse().selectFirst("div.summary-cartype");
                    String typeUrl = BASE_URL + typeElement.select("a.caption").attr("href");
                    //从汽车详情页获取第一个汽车版本
                    Connection.Response detailRes = Jsoup.connect(typeUrl).ignoreContentType(true).method(Connection.Method.GET).execute();// 执行请求;
                    Elements elements = detailRes.parse().select("div.module > div > a.item");
                    specUrl = "https:" + elements.get(2).attr("href");
                } else {
                    return new ResponseResult(ResultType.FAILURE, "未找到相关汽车信息");
                }
            }
            //汽车配置参数
            String configUrl = CONFIG_URL + specUrl.substring(specUrl.indexOf("spec") + 5, specUrl.indexOf(".html"));
            String response = HttpsUtils.doGet(configUrl);
            JSONObject jsonObject = JSONObject.fromObject(response);
            String data = jsonObject.get("data").toString();
            if (data == null) {
                return new ResponseResult(ResultType.FAILURE, "未找到相关汽车参数配置信息");
            }
//            JSONArray jsonArray = JSONObject.fromObject(data).getJSONArray("param");
//            Collection collection = JSONArray.toCollection(jsonArray, AutohomeParam.class);


            return new ResponseResult(delHtmlTag(data));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseResult(ResultType.FAILURE, "未找到相关汽车参数配置信息");
        }
    }

    public static String delHtmlTag(String str){
        String newstr = "";
        newstr = str.replaceAll("<[.[^>]]*>","");
        newstr = newstr.replaceAll(" ", "");
        return newstr;
    }


}
