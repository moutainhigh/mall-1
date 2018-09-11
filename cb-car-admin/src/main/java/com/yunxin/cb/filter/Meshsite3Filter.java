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
        //拦截规则，/decorator/default 会被转发
        builder.addDecoratorPath("*.do", "/layouts/default");
    }
}