package com.yunxin.cb.search.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Parent;

import java.util.Objects;

/**
 * 商品价格段
 */
@Document(indexName = "crystal_ball", type = "price_section")
public class PriceSection implements java.io.Serializable {

    private static final long serialVersionUID = -8698539807425798151L;

    public static final String index_type="price_section";

    @Id
    private String id;

    /**
     * 起始价格
     */
    private int startPrice;

    /**
     * 结束价格
     */
    private int endPrice;

    @Parent(type = "commodity")
    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    public int getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(int endPrice) {
        this.endPrice = endPrice;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceSection that = (PriceSection) o;
        return startPrice == that.startPrice &&
                endPrice == that.endPrice;
    }

    @Override
    public int hashCode() {

        return Objects.hash(startPrice, endPrice);
    }
}
