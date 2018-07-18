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
    ANDROID_DESCRIPTION("")//安卓APP更新描述
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