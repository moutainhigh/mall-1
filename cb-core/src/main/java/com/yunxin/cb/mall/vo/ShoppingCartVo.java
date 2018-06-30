package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.Product;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ShoppingCartVo implements Serializable {


    private List<ActivityVo> activityVos;

    //货品封装后的属性：值
    private List<ProductPropertyVo> propVls;

    private int cartId;

    /**
     * 货品
     */
    private Product product;
    /**
     * 货品类型
     */
    private int productType;
    /**
     * 客户
     */
    private Customer customer;

    /**
     * 数量
     */
    private int cartNum;

    /**
     * 创建时间
     */
    private Date createTime;

    private ActivityVo currenActivity;

    public List<ActivityVo> getActivityVos() {
        return activityVos;
    }

    public void setActivityVos(List<ActivityVo> activityVos) {
        this.activityVos = activityVos;
    }

    public List<ProductPropertyVo> getPropVls() {
        return propVls;
    }

    public void setPropVls(List<ProductPropertyVo> propVls) {
        this.propVls = propVls;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getCartNum() {
        return cartNum;
    }

    public void setCartNum(int cartNum) {
        this.cartNum = cartNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public ActivityVo getCurrenActivity() {
        return currenActivity;
    }

    public void setCurrenActivity(ActivityVo currenActivity) {
        this.currenActivity = currenActivity;
    }
}
