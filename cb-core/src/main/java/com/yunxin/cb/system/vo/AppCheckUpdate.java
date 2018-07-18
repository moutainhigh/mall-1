package com.yunxin.cb.system.vo;

public class AppCheckUpdate {

    private int versionCode;

    private String versionName;

    private String name;

    private String url;

    public AppCheckUpdate(){

    }

    public AppCheckUpdate(int versionCode, String versionName, String name, String url, String description) {
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.name = name;
        this.url = url;
        this.description = description;
    }

    private String description;

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
