package com.yunxin.cb.test;

import com.yunxin.cb.ruokuai.RuoKuai;

public class RuoKuaiTest {

    public static void main(String[] args) {
        String code = RuoKuai.createByUrl("107803","6cd4293bae4c4522ad83b5b56d19ad00","3040","tanggangyi","tgy123456","https://www.sino-life.com/elogin/getVerifyCode.do");
        System.out.println(code);
    }
}
