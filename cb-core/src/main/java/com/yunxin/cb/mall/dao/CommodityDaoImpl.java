package com.yunxin.cb.mall.dao;

import com.hankcs.lucene.HanLPAnalyzer;
import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.core.orm.BaseDaoImpl;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class CommodityDaoImpl extends BaseDaoImpl<Commodity> implements CommodityPlusDao {


    @Override
    public void indexAll(Stream<Commodity> commodityStream) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        int index = 0;
        commodityStream.forEach(p -> {
            fullTextEntityManager.index(p);
//			if (index % 50 == 0) {
//				fullTextEntityManager.flushToIndexes(); //apply changes to indexes
//				fullTextEntityManager.clear(); //free memory since the queue is processed
//			}
        });
    }

    @Override
    public List<Commodity> searchCommodities(String text) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        //在字段content中检索
        QueryParser queryParser = new QueryParser("commodityName", new HanLPAnalyzer());
        queryParser.setDefaultOperator(QueryParser.AND_OPERATOR);
        org.apache.lucene.search.Query luceneqQuery = null;
        try {
            //检索含有“大风”的信息
            luceneqQuery = queryParser.parse(text);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        //执行检索，得到结果集
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneqQuery, Commodity.class);
        List<Commodity> pageinfos = fullTextQuery.getResultList();
        //查看结果做验证
        for (Iterator iterator = pageinfos.iterator(); iterator.hasNext(); ) {
            Commodity pageinfo = (Commodity) iterator.next();
            System.out.println(pageinfo.getCommodityName());
        }
        return pageinfos;
    }

    @Override
    public Page<Commodity> pageSearchCommodities(String text, Pageable pageable) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        //在字段content中检索
        String[] properties = new String[]{"commodityName", "shortName", "commodityTitle", "brand.brandName"};
        MultiFieldQueryParser queryParser = new MultiFieldQueryParser(properties, new HanLPAnalyzer());
        queryParser.setDefaultOperator(QueryParser.OR_OPERATOR);
        org.apache.lucene.search.Query luceneqQuery = null;
        try {
            //检索含有“大风”的信息
            luceneqQuery = queryParser.parse(text);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        //执行检索，得到结果集
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneqQuery, Commodity.class);
        fullTextQuery.setMaxResults(pageable.getPageSize());
        fullTextQuery.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        List<Commodity> commodities = fullTextQuery.getResultList();
        Page<Commodity> commodityPage = new PageImpl<Commodity>(commodities, pageable, fullTextQuery.getResultSize());
        return commodityPage;
    }
}
