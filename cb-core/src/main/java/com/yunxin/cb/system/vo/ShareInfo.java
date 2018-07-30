package com.yunxin.cb.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="分享信息",description="分享信息 ShareInfo")
public class ShareInfo {

    @ApiModelProperty(value="分享地址",name="sharePath",example="www.xiazai.com")
    private String sharePath;//分享地址
    @ApiModelProperty(value="分享标题",name="shareTitle",example="标题")
    private String shareTitle;//分享标题
    @ApiModelProperty(value="分享图标",name="shareIcon",example="http://oty8vd0fp.bkt.clouddn.com/Fg3G4rDKqjD0D0f5b4TSGgocMOp5")
    private String shareIcon;//分享图标
    @ApiModelProperty(value="分享描述",name="shareDescription",example="分享描述")
    private String shareDescription;//分享描述
    @ApiModelProperty(value="分享短信内容",name="shareShortmessageContent",example="分享短信内容")
    private String shareShortmessageContent;//分享短信内容

    public ShareInfo() {

    }

    public ShareInfo(String sharePath, String shareTitle, String shareIcon, String shareDescription, String shareShortmessageContent) {
        this.sharePath = sharePath;
        this.shareTitle = shareTitle;
        this.shareIcon = shareIcon;
        this.shareDescription = shareDescription;
        this.shareShortmessageContent = shareShortmessageContent;
    }

    public String getSharePath() {
        return sharePath;
    }

    public void setSharePath(String sharePath) {
        this.sharePath = sharePath;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareIcon() {
        return shareIcon;
    }

    public void setShareIcon(String shareIcon) {
        this.shareIcon = shareIcon;
    }

    public String getShareDescription() {
        return shareDescription;
    }

    public void setShareDescription(String shareDescription) {
        this.shareDescription = shareDescription;
    }

    public String getShareShortmessageContent() {
        return shareShortmessageContent;
    }

    public void setShareShortmessageContent(String shareShortmessageContent) {
        this.shareShortmessageContent = shareShortmessageContent;
    }
}
