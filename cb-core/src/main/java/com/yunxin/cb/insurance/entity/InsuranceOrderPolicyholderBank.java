/*
 * Since 2015 - 2018
 */


package com.yunxin.cb.insurance.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

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
public class InsuranceOrderPolicyholderBank implements Serializable {

private static final long serialVersionUID = 1L;


    //columns START
        /**
        * 投保人银行ID
        */
        @Max(9999999999L)
        private int bankId;
        /**
        * 持卡人姓名
        */
        @NotBlank @Length(max=32)
        private String bankName;
        /**
        * 交易金额
        */
        @NotNull @Max(9999999999L)
        private int amount;
        /**
        * 手机号
        */
        @NotBlank @Length(max=11)
        private String bankMobile;
        /**
        * 开户行
        */
        @NotBlank @Length(max=32)
        private String accountBank;
        /**
        * 开户行省
        */
        @NotBlank @Length(max=6)
        private String bankProvince;
        /**
        * 开户行市
        */
        @NotBlank @Length(max=6)
        private String bankCity;
        /**
        * 账户类型
        */
        @NotBlank @Length(max=32)
        private String accountType;
        /**
        * 账户号
        */
        @NotBlank @Length(max=32)
        private String accountNo;
        /**
        * 银行卡照片
        */
        @NotBlank @Length(max=255)
        private String bankCardImg;
    //columns END


	public InsuranceOrderPolicyholderBank(){
	}

	public InsuranceOrderPolicyholderBank(
		int bankId
	){
		this.bankId = bankId;
	}


            @Id
            @GeneratedValue(strategy = IDENTITY)
            @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
            public int getBankId() {
            return this.bankId;
            }

            public void setBankId(int bankId) {
            this.bankId = bankId;
            }

        @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
        public String getBankName() {
        return this.bankName;
        }

        public void setBankName(String bankName) {
        this.bankName = bankName;
        }

        @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 10)
        public int getAmount() {
        return this.amount;
        }

        public void setAmount(int amount) {
        this.amount = amount;
        }

        @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 11)
        public String getBankMobile() {
        return this.bankMobile;
        }

        public void setBankMobile(String bankMobile) {
        this.bankMobile = bankMobile;
        }

        @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
        public String getAccountBank() {
        return this.accountBank;
        }

        public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
        }

        @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 6)
        public String getBankProvince() {
        return this.bankProvince;
        }

        public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince;
        }

        @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 6)
        public String getBankCity() {
        return this.bankCity;
        }

        public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
        }

        @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
        public String getAccountType() {
        return this.accountType;
        }

        public void setAccountType(String accountType) {
        this.accountType = accountType;
        }

        @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 32)
        public String getAccountNo() {
        return this.accountNo;
        }

        public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
        }

        @Column(unique = false, nullable = false, insertable = true, updatable = true, length = 255)
        public String getBankCardImg() {
        return this.bankCardImg;
        }

        public void setBankCardImg(String bankCardImg) {
        this.bankCardImg = bankCardImg;
        }


    private Set insuranceOrders = new HashSet(0);

    @OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "insuranceOrderPolicyholderBank")
    public Set<InsuranceOrder> getInsuranceOrders() {
    return insuranceOrders;
    }

    public void setInsuranceOrders(Set<InsuranceOrder> insuranceOrder){
    this.insuranceOrders = insuranceOrder;
    }



}