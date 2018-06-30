package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class KeyWord implements java.io.Serializable {
    /**
     * 过滤关键字ID
     */
    private int keyWordId;
    /**
     * 关键字key值
     */
    private String keyKey;
    /**
     * 关键字value值
     */
    private String keyValue;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getKeyWordId() {
        return keyWordId;
    }

    public void setKeyWordId(int keyWordId) {
        this.keyWordId = keyWordId;
    }

    @Column(nullable = false, length = 12)
    public void setKeyKey(String keyKey) {
        this.keyKey = keyKey;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    @Column(nullable = false, length = 512)
    public String getKeyValue() {
        return keyValue;
    }

    public String getKeyKey() {
        return keyKey;
    }

}
