/**
 *
 */
package com.yunxin.cb.mall.query;

import com.yunxin.cb.mall.entity.Catalog;
import com.yunxin.core.persistence.PageSpecification;


/**
 * @author tanggangyi
 */
public class MobileCategoryQuery extends PageSpecification<Catalog> {

    private String $callback;

    private String $inlinecount;

    private String $format;

    private int $top;

    private int $skip;

    private String $orderby;


    public MobileCategoryQuery() {
        setPage(1);
    }

    public String get$callback() {
        return $callback;
    }

    public void set$callback(String $callback) {
        this.$callback = $callback;
    }

    public String get$inlinecount() {
        return $inlinecount;
    }

    public void set$inlinecount(String $inlinecount) {
        this.$inlinecount = $inlinecount;
    }

    public String get$format() {
        return $format;
    }

    public void set$format(String $format) {
        this.$format = $format;
    }

    public int get$top() {
        return $top;
    }

    public void set$top(int $top) {
        this.$top = $top;
        this.setPageSize($top);
    }

    public int get$skip() {
        return $skip;
    }

    public void set$skip(int $skip) {
        this.$skip = $skip;
        this.setPage(($skip / $top) + 1);
    }

    public String get$orderby() {
        return $orderby;
    }

    public void set$orderby(String $orderby) {
        this.$orderby = $orderby;
    }

}
