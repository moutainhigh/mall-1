package com.yunxin.cb.mall.query;

import com.yunxin.cb.mall.entity.Seller;
import com.yunxin.core.persistence.PageSpecification;

/**
 * @author z001392
 */
public class HallEvalReplyQuery extends PageSpecification<Seller> {


    private int evaId;
    private int hallId;

    /**
     * @return the hallId
     */
    public int getHallId() {
        return hallId;
    }

    /**
     * @param hallId the hallId to set
     */
    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public int getEvaId() {
        return evaId;
    }

    public void setEvaId(int evaId) {
        this.evaId = evaId;
    }
}
