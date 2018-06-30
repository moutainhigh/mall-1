package com.yunxin.cb.cms.dao;

import com.hankcs.lucene.HanLPAnalyzer;
import com.yunxin.cb.cms.entity.Article;
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

/**
 * Created by chenxing on 2016/1/18.
 */
public class ArticleDaoImpl extends BaseDaoImpl<Article> implements ArticlePlusDao {

    @Override
    public void indexAll(Stream<Article> articleStream) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        int index = 0;
        articleStream.forEach(p -> {
            //System.out.println("索引项:"+p.getArticleTitle());
            fullTextEntityManager.index(p);
//			if (index % 50 == 0) {
//				fullTextEntityManager.flushToIndexes(); //apply changes to indexes
//				fullTextEntityManager.clear(); //free memory since the queue is processed
//			}
        });
    }

    @Override
    public List<Article> searchArticles(String text) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        //在字段content中检索
        String[] properties = new String[]{"articleTitle", "shortTitle", "summary", "content", "programa.programaName"};
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
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneqQuery, Article.class);
        List<Article> pageinfos = fullTextQuery.getResultList();
        //查看结果做验证
        for (Iterator iterator = pageinfos.iterator(); iterator.hasNext(); ) {
            Article pageinfo = (Article) iterator.next();
            System.out.println(pageinfo.getArticleTitle());
        }
        return pageinfos;
    }

    @Override
    public Page<Article> pageSearchArticles(String text, Pageable pageable) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        //在字段content中检索
        String[] properties = new String[]{"articleTitle", "shortTitle", "summary", "content", "programa.programaName"};
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
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneqQuery, Article.class);
        fullTextQuery.setMaxResults(pageable.getPageSize());
        fullTextQuery.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        List<Article> articles = fullTextQuery.getResultList();
        Page<Article> articlePage = new PageImpl<Article>(articles, pageable, fullTextQuery.getResultSize());
        return articlePage;
    }
}
