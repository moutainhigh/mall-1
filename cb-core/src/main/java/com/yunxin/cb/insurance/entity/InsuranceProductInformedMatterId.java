

/*
 * Since 2015 - 2018
 */

package com.yunxin.cb.insurance.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * *
 *
 * @author tanggangyi
 * @version 1.0
 * @since 1.0
 */
@Embeddable
public class InsuranceProductInformedMatterId implements java.io.Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    private int prodId;
    private int matterId;

    public InsuranceProductInformedMatterId() {
    }

    public InsuranceProductInformedMatterId(
            int prodId,
            int matterId
    ) {
        this.prodId = prodId;
        this.matterId = matterId;
    }


    public void setProdId(int value) {
        this.prodId = value;
    }

    @Column(name = "PROD_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    public int getProdId() {
        return this.prodId;
    }

    public void setMatterId(int value) {
        this.matterId = value;
    }

    @Column(name = "MATTER_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
    public int getMatterId() {
        return this.matterId;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }
}