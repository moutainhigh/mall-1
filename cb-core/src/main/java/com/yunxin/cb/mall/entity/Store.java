/**
 *
 */
package com.yunxin.cb.mall.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 仓库
 *
 * @author z001075
 */
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Store implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8849015737196097830L;

    /**
     * id
     */

    private int storeId;

    /**
     * 仓库编码
     */

    private String storeCode;

    /**
     * 仓库名
     */

    private String storeName;

    /**
     * 省
     */

    private String province;

    /**
     * 市
     */

    private String city;

    /**
     * 区
     */

    private String district;

    /**
     * 地址
     */

    private String address;

    /**
     * 邮编
     */

    private int post;

    /**
     * 备注
     */

    private String remark;

    /**
     * 状态：true 启用；false 停用
     */
    private boolean enabled;

    public Store() {

    }

    public Store(int storeId) {
        this.storeId = storeId;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int depositoryId) {
        this.storeId = depositoryId;
    }

    @Column(nullable = false, length = 32, unique = true)
    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String depositoryCode) {
        this.storeCode = depositoryCode;
    }

    @Column(nullable = false, length = 64)
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String depositoryName) {
        this.storeName = depositoryName;
    }

    @Column(nullable = false, length = 32)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(nullable = false, length = 32)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(nullable = false, length = 32)
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Column(nullable = false, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(nullable = true, precision = 6)
    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    @Column(nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(nullable = false, precision = 1)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enable) {
        this.enabled = enable;
    }

}
