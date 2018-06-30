package com.yunxin.cb.mall.web.action.cms;

public class Template {

    /**
     * 模板名
     */
    private String templateName;

    private String templatePath;

    /**
     * 模板内容
     */
    private String content;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }
}
