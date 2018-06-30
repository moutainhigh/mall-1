package com.yunxin.cb.mall.web.action.system;

import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.service.ISearchService;
import com.yunxin.cb.cms.entity.Article;
import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.service.ISearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gonglei on 16/2/11.
 */
@Controller
@RequestMapping(value = "/system")
public class SearchController {

    @Resource
    private ISearchService searchService;

    @RequestMapping(method = RequestMethod.GET)
    public String searchEngine(ModelMap modelMap) {

        return "system/searchEngine";
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public int startIndex( ) {
        searchService.indexAllCommodities();
        return 1;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public List<Commodity> searchCommodities(@RequestParam("text") String text) {
        List<Commodity> commodities = searchService.searchCommodities(text);
        return commodities;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public int startArticleIndex( ) {
        searchService.indexAllArticles();
        return 1;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public List<Article> searchArticles(@RequestParam("text") String text) {
        List<Article> articles = searchService.searchArticles(text);
        return articles;
    }

}
