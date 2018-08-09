package com.yunxin.common;

/**
 * 常量类
 * @Auther: lxc
 * @Date: 2018/8/7 15:36
 * @Description:
 */
public interface ConstantsCB {

    static String FUNDS_POOL = "资金池";


    /**
     * FundsPoolLog类型,1累计,2报账,
     * @Date: 2018/8/7 15:36
     * @author lxc
     */
    enum FundsPoolLogType
    {
        GRAND("累计",1),REIMBURSE("报账",2);
        private String msg;
        private int status;
        FundsPoolLogType(String msg, int status)
        {
            this.msg = msg;
            this.status = status;
        }
        public String getMsg() {
            return msg;
        }
        public int getStatus() {
            return status;
        }
    }
}
