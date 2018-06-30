/**
 *
 */
package com.yunxin.cb.mall.query;

import com.yunxin.cb.mall.entity.ProductEvaluateReply;
import com.yunxin.core.persistence.PageSpecification;

/**
 * @author j000101
 */
public class CommodityEvalReplyQuery extends PageSpecification<ProductEvaluateReply> {

    private int evaId;
    private int commodityId;

    /**
     * @return the commodityId
     */
    public int getCommodityId() {
        return commodityId;
    }

    /**
     * @param commodityId the commodityId to set
     */
    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getEvaId() {
        return evaId;
    }

    public void setEvaId(int evaId) {
        this.evaId = evaId;
    }

}
