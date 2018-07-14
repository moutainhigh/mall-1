package com.yunxin.cb.filter;


import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * sitemesh 自定义配置
 *
 * @author tanggangyi
 */
public class Meshsite3Filter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("*.do", "/layouts/default")//拦截规则，/decorator/default 会被转发
            .addExcludedPath("/index.do**") //白名单
            .addExcludedPath("/media/chooseMedias.do*")
            .addExcludedPath("/insurance/prints.do*");
    }
}