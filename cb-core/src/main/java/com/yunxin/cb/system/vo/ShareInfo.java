package com.yunxin.cb.system.vo;

public class ShareInfo {

    private String sharePath;//分享地址

    private String shareTitle;//分享标题

    private String shareIcon;//分享图标

    private String shareDescription;//分享描述

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
