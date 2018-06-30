/**
 *
 */
package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.OrderItem;

/**
 * @author qulei
 */
public class SalesYearVo extends SalesReportVo implements java.io.Serializable {


    private static final long serialVersionUID = 1L;

    private double sales0;
    private double sales1;
    private double sales2;
    private double sales3;
    private double sales4;
    private double sales5;
    private double sales6;
    private double sales7;
    private double sales8;
    private double sales9;
    private double sales10;
    private double sales11;

    public SalesYearVo(OrderItem orderItem) {
        super(orderItem);
    }

    /*	public SalesYearVo(){

        }

        public SalesYearVo(int productId, String productName, String productImg,Long totalSales, int calendar){
            super(productId, productName, productImg, totalSales, calendar);
        }
        */
    public double getSales0() {
        return sales0;
    }

    public void setSales0(double sales0) {
        this.sales0 = sales0;
    }

    public double getSales1() {
        return sales1;
    }

    public void setSales1(double sales1) {
        this.sales1 = sales1;
    }

    public double getSales2() {
        return sales2;
    }

    public void setSales2(double sales2) {
        this.sales2 = sales2;
    }

    public double getSales3() {
        return sales3;
    }

    public void setSales3(double sales3) {
        this.sales3 = sales3;
    }

    public double getSales4() {
        return sales4;
    }

    public void setSales4(double sales4) {
        this.sales4 = sales4;
    }

    public double getSales5() {
        return sales5;
    }

    public void setSales5(double sales5) {
        this.sales5 = sales5;
    }

    public double getSales6() {
        return sales6;
    }

    public void setSales6(double sales6) {
        this.sales6 = sales6;
    }

    public double getSales7() {
        return sales7;
    }

    public void setSales7(double sales7) {
        this.sales7 = sales7;
    }

    public double getSales8() {
        return sales8;
    }

    public void setSales8(double sales8) {
        this.sales8 = sales8;
    }

    public double getSales9() {
        return sales9;
    }

    public void setSales9(double sales9) {
        this.sales9 = sales9;
    }

    public double getSales10() {
        return sales10;
    }

    public void setSales10(double sales10) {
        this.sales10 = sales10;
    }

    public double getSales11() {
        return sales11;
    }

    public void setSales11(double sales11) {
        this.sales11 = sales11;
    }

}
