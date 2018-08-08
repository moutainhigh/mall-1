package com.yunxin.cb.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="APP版本信息",description="APP版本信息 AppCheckUpdate")
public class AppCheckUpdate {
    @ApiModelProperty(value="版本号",name="versionCode",example="1.0")
    private int versionCode;

    @ApiModelProperty(value="版本名称",name="versionName",example="2.0")
    private String versionName;

    @ApiModelProperty(value="APP名称",name="name",example="云信")
    private String name;

    @ApiModelProperty(value="APP下载url",name="url",example="www.baidu.com")
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
