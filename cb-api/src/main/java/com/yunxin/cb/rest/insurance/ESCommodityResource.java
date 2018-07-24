package com.yunxin.cb.rest.insurance;

import com.yunxin.cb.mall.service.IESCommodityService;
import com.yunxin.cb.vo.ResponseResult;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.yunxin.cb.cms.entity.Article_.author;

@RestController
@RequestMapping(value = "/es/commodity")
public class ESCommodityResource {

    @Autowired
    private IESCommodityService commodityService;
    @Autowired
    private TransportClient client;

    @PostMapping(value = "saveCommodity")
    public void saveCommodity()
    {
        commodityService.addCommodity();
    }
    @PostMapping(value = "findByCommodityName")
    public ResponseResult findByCommodityName(@RequestParam String commodityName)
    {
        return  new ResponseResult(commodityService.findByCommodityName(commodityName));
    }
    @PostMapping(value = "findByCommodityId")
    public ResponseResult findByCommodityId(@RequestParam String commodityId)
    {
//        SearchResponse response = client.prepareSearch().execute().actionGet();
//        GetResponse response = this.client.prepareGet("crystal_ball","commodity1","10").get();
//        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
/*        for(SearchHit hit:response.getSource()){
            result.add(hit.getSource());
        }*/
//        response.getSource();
//        SearchHits hits = response.getHits();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        String commodityName = "9";
        boolQuery.must(QueryBuilders.matchQuery("commodityName",commodityName));
        SearchRequestBuilder builder = this.client.prepareSearch("crystal_ball")
                .setTypes("commodity1")
                .setQuery(boolQuery.must(QueryBuilders.matchQuery("commodityName",commodityName)).must(QueryBuilders.matchQuery("commodityTitle",9)))
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setFrom(0).setSize(10)//分页from从零开始 size数据量
                .addSort("sellPrice", SortOrder.DESC);//排序
        System.out.println(builder);
        SearchResponse response = builder.get();
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        for(SearchHit hit:response.getHits()){
            result.add(hit.getSource());
        }
        return new ResponseResult(result);

    }
}
