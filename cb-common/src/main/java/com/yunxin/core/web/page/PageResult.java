package com.yunxin.core.web.page;

import java.util.List;
import java.util.Map;

public class PageResult<T> {
    private long total;

    private List<T> data;

    private Map<String, Object> aggregates;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Map<String, Object> getAggregates() {
        return aggregates;
    }

    public void setAggregates(Map<String, Object> aggregates) {
        this.aggregates = aggregates;
    }
}
