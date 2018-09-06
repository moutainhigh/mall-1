package com.yunxin.cb.mall.web.action.rest;

import com.yunxin.cb.cms.entity.Article;
import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.exception.CommonException;
import com.yunxin.cb.mall.service.ISearchService;
import com.yunxin.cb.cms.entity.Article;
import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.exception.CommonException;
import com.yunxin.cb.mall.service.ISearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by gonglei on 16/2/11.
 */
@RestController
@RequestMapping(value = "/rest")
public class CommoditySearchController {

    @Resource
    private ISearchService searchService;

    @RequestMapping(value = "searchCommodities",method = RequestMethod.POST)
    @ResponseBody
    public Page<Commodity> searchCommodities(@RequestParam("text") String text, @RequestParam("page") int page , @RequestParam("pageSize") int  pageSize) {
        Page<Commodity> commodities = null;
        try {
            commodities = searchService.pageSearchCommodities(text,page,pageSize);
        } catch (CommonException e) {
            e.printStackTrace();
        }
        return commodities;
    }

    @RequestMapping(value = "searchArticles",method = RequestMethod.POST)
    @ResponseBody
    public Page<Article> searchArticles(@RequestParam("text") String text, @RequestParam("page") int page , @RequestParam("pageSize") int  pageSize) {
        Page<Article> commodities = null;
        try {
            commodities = searchService.pageSearchArticles(text,page,pageSize);
        } catch (CommonException e) {
            e.printStackTrace();
        }
        return commodities;
    }

}
