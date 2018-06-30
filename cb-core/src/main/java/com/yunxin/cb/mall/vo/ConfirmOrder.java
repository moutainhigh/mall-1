package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.meta.DeliveryType;
import com.yunxin.cb.mall.entity.meta.InvoiceTitleType;
import com.yunxin.cb.mall.entity.meta.InvoiceType;

/**
 * Created by gonglei on 16/1/27.
 */
public class ConfirmOrder {

    private int addressId;

    private int[] cartId;

    private int[] productId;

    private String[] productBuyerMessage;

    private int[] quantity;

    private int[] activityId;

    private Customer customer;

    private boolean useIntegralDeduction;

    /**
     * 递送方式
     */
    private DeliveryType deliveryType;

    /**
     * 买家留言
     */
    private String buyerMessage;

    //################发票信息
    /**
     * 是否需要发票
     */
    private boolean needInvoice;
    /**
     * 发票类型(1普通,2增值税)
     */
    private InvoiceType invoiceType;
    /**
     * 抬头类型(1个人,2单位...)
     */
    private InvoiceTitleType invoiceTitleType;
    /**
     * 发票抬头(个人或单位名称)
     */
    private String invoiceTitle;
    /**
     * 纳税人识别号
     */
    private String taxpayerNo;
    /**
     * 注册地址
     */
    private String registerAddress;
    /**
     * 注册电活
     */
    private String registerPhone;
    /**
     * 开户行
     */
    private String bankName;
    /**
     * 银行账户
     */
    private String bankAccount;
    /**
     * 发票内容(1.明细,2办公用品(附件清单),3.电脑配件,4.耗材等)
     */
    private String content;
    /**
     * 发票金额
     */
    private String invoiceAmount;

    /**
     * 增值税单位名称
     */
    private String addedTaxInvoiceTitle;

    /**
     * 增值税发票内容
     */
    private String addedTaxContent;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int[] getCartId() {
        return cartId;
    }

    public void setCartId(int[] cartId) {
        this.cartId = cartId;
    }

    public int[] getProductId() {
        return productId;
    }

    public void setProductId(int[] productId) {
        this.productId = productId;
    }

    public String[] getProductBuyerMessage() {
        return productBuyerMessage;
    }

    public void setProductBuyerMessage(String[] productBuyerMessage) {
        this.productBuyerMessage = productBuyerMessage;
    }

    public int[] getQuantity() {
        return quantity;
    }

    public void setQuantity(int[] quantity) {
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public int[] getActivityId() {
        return activityId;
    }

    public void setActivityId(int[] activityId) {
        this.activityId = activityId;
    }

    public boolean isUseIntegralDeduction() {
        return useIntegralDeduction;
    }

    public void setUseIntegralDeduction(boolean useIntegralDeduction) {
        this.useIntegralDeduction = useIntegralDeduction;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public InvoiceTitleType getInvoiceTitleType() {
        return invoiceTitleType;
    }

    public void setInvoiceTitleType(InvoiceTitleType invoiceTitleType) {
        this.invoiceTitleType = invoiceTitleType;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getTaxpayerNo() {
        return taxpayerNo;
    }

    public void setTaxpayerNo(String taxpayerNo) {
        this.taxpayerNo = taxpayerNo;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String getRegisterPhone() {
        return registerPhone;
    }

    public void setRegisterPhone(String registerPhone) {
        this.registerPhone = registerPhone;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getAddedTaxInvoiceTitle() {
        return addedTaxInvoiceTitle;
    }

    public void setAddedTaxInvoiceTitle(String addedTaxInvoiceTitle) {
        this.addedTaxInvoiceTitle = addedTaxInvoiceTitle;
    }

    public String getAddedTaxContent() {
        return addedTaxContent;
    }

    public void setAddedTaxContent(String addedTaxContent) {
        this.addedTaxContent = addedTaxContent;
    }

    public boolean isNeedInvoice() {
        return needInvoice;
    }

    public void setNeedInvoice(boolean needInvoice) {
        this.needInvoice = needInvoice;
    }
}
