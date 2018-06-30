package com.yunxin.security;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gonglei on 2014/11/17.
 */
public class Module {

    private String moduleName;

    private List<Resc> rescs = new ArrayList<>();

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<Resc> getRescs() {
        return rescs;
    }

    public void setRescs(List<Resc> rescs) {
        this.rescs = rescs;
    }
}
