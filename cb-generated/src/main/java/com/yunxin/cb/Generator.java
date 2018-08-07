package com.yunxin.cb;


import cn.org.rapid_framework.generator.GeneratorFacade;

/**
 * Created by tanggangyi
 */
public class Generator {

    public static void main(String[] args) throws Exception {
        GeneratorFacade g = new GeneratorFacade();
        g.deleteOutRootDir();
        g.getGenerator().setTemplateRootDir("cb-generated/templates");
//        g.generateByAllTable();
        g.generateByTable("customer_friend_request");
    }
}
