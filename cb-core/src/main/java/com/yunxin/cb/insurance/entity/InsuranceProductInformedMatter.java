/*
 * Since 2015 - 2018
 */


package com.yunxin.cb.insurance.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
*  *
 * @author tanggangyi
 * @version 1.0
 * @since 1.0
 *
*/
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class InsuranceProductInformedMatter implements Serializable {

private static final long serialVersionUID = 1L;


    private InsuranceProductInformedMatterId id;


    public InsuranceProductInformedMatter(){
    }
    public InsuranceProductInformedMatter(InsuranceProductInformedMatterId id) {
    this.id = id;
    }

    @EmbeddedId
    public InsuranceProductInformedMatterId getId() {
    return this.id;
    }

    public void setId(InsuranceProductInformedMatterId id) {
    this.id = id;
    }


    private InsuranceInformedMatter insuranceInformedMatter;
    public void setInsuranceInformedMatter(InsuranceInformedMatter insuranceInformedMatter){
    this.insuranceInformedMatter = insuranceInformedMatter;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "MATTER_ID",nullable = false, insertable = true, updatable = true) 
    })
    public InsuranceInformedMatter getInsuranceInformedMatter() {
    return insuranceInformedMatter;
    }

    private InsuranceProduct insuranceProduct;
    public void setInsuranceProduct(InsuranceProduct insuranceProduct){
    this.insuranceProduct = insuranceProduct;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "PROD_ID",nullable = false, insertable = true, updatable = true) 
    })
    public InsuranceProduct getInsuranceProduct() {
    return insuranceProduct;
    }



}