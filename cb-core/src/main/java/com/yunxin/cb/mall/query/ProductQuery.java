/**
 *
 */
package com.yunxin.cb.mall.query;

import com.yunxin.cb.mall.entity.Product;
import com.yunxin.cb.mall.entity.meta.ProductState;
import com.yunxin.core.persistence.PageSpecification;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author gonglei
 */
public class ProductQuery extends PageSpecification<Product> {

    private ProductState status;

    private List<ProductState> statusList;

    //1普通货品 2定制货品
    private int productType;

    private Set<Integer> supplierIds = new HashSet<>();

    public Set<Integer> getSupplierIds() {
        return supplierIds;
    }

    public void setSupplierIds(Set<Integer> supplierIds) {
        this.supplierIds = supplierIds;
    }

    //    public List<Integer> getSupplierIds() {
//        return supplierIds;
//    }
//
//    public void setSupplierIds(List<Integer> supplierIds) {
//        this.supplierIds = supplierIds;
//    }


    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public List<ProductState> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<ProductState> statusList) {
        this.statusList = statusList;
    }

    /**
     * @return the status
     */
    public ProductState getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ProductState status) {
        this.status = status;
    }

}
