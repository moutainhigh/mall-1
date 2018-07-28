package com.yunxin.cb.system.service.imp;

import com.yunxin.cb.system.meta.ProfileName;

public class Test {

    public static void main(String[] args) throws Exception {


        for (ProfileName e : ProfileName.values()) {
            System.out.println(e.getDefaultValue());
        }

    }
}
