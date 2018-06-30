package com.yunxin.cb.mall.query;

import com.yunxin.cb.mall.entity.Freight;
import com.yunxin.core.persistence.PageSpecification;

public class FreightQuery extends PageSpecification<Freight> {
    private int freightId;
    private String areaCode;

    public int getFreightId() {
        return freightId;
    }

    public void setFreightId(int freightId) {
        this.freightId = freightId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

}
