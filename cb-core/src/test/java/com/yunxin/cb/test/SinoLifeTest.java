package com.yunxin.cb.test;

import com.yunxin.cb.sinolife.SinoLifeService;

public class SinoLifeTest {

    public static void main(String[] args) throws Exception{
        SinoLifeService sinoLifeService = new SinoLifeService();

        sinoLifeService.submitOrder(null);
    }
}
