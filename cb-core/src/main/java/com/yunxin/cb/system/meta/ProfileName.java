package com.yunxin.cb.system.meta;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @author tanggangyi
 */
public enum ProfileName {

    ANDROID_VERSION_CODE("1"),//安卓版本编码
    ANDROID_VERSION_NAME("v1.0.0"),//安卓版本名称
    ANDROID_APP_NAME("水晶球"),//安卓APP名称
    ANDROID_URL(""),//安卓APP下载地址
    ANDROID_DESCRIPTION(""),//安卓APP更新描述
    ANDROID_FORCE_UPGRADE("false"),//安卓APP是否强制更新
    GIVE_THE_THUMBS_UP("0.05"),//点赞推荐人及所有上级加5%的授信额度
    LOAN_EXPECTED_RETURN_FIFTY("0.5"),//下单推荐人增加50%的贷款预期收益
    SHARE_PATH(""),//分享地址
    SHARE_TITLE(""),//分享标题
    SHARE_ICON(""),//分享图标
    SHARE_DESCRIPTION(""),//分享描述
    SHARE_SHORTMESSAGE_CONTENT(""),//分享短信内容
    ;

    private String i18nName;

    private String defaultValue;

    ProfileName(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getI18nName() {
        return i18nName;
    }

    public void setI18nName(String i18nName) {
        this.i18nName = i18nName;
    }

    public static ProfileName[] toI18nArray(MessageSource messageSource, Locale locale) {
        ProfileName[] annexTypes = ProfileName.values();
        for (ProfileName treeType : annexTypes) {
            treeType.setI18nName(messageSource.getMessage(ProfileName.class.getSimpleName() + "_" + treeType.name(), null, locale));
        }
        return annexTypes;
    }
}