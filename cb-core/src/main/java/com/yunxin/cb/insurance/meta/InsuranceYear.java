package com.yunxin.cb.insurance.meta;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @author tanggangyi
 */
public enum InsuranceYear {

    TEN_YEAR("10年"),TWENTY_YEAR("20年"),LIFITIME("终生");

    private String i18nName;

    private String defaultValue;

    InsuranceYear(String defaultValue) {
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

    public static InsuranceYear[] toI18nArray(MessageSource messageSource, Locale locale) {
        InsuranceYear[] annexTypes = InsuranceYear.values();
        for (InsuranceYear treeType : annexTypes) {
            treeType.setI18nName(messageSource.getMessage(InsuranceYear.class.getSimpleName() + "_" + treeType.name(), null, locale));
        }
        return annexTypes;
    }

    @Override
    public String toString() {
        return super.toString() + "("+defaultValue+")";
    }
}