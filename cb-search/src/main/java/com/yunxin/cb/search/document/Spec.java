package com.yunxin.cb.search.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Parent;

/**
 * 商品规格
 */
@Document(indexName = "crystal_ball", type = "spec")
public class Spec implements java.io.Serializable {

    private static final long serialVersionUID = -5096401435274075567L;

    public static final String index_type="spec";

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String specName;

    @Field(type = FieldType.Keyword)
    private String value;

    @Parent(type = "commodity")
    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
