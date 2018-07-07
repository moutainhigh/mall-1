package com.yunxin.cb.config;

import com.yunxin.core.orm.HibernateAwareObjectMapper;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.nio.charset.Charset;

@Configuration
public class RestConfig {

    @Bean
    public FilterRegistrationBean restTokenFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new RestTokenFilter());
        registrationBean.addUrlPatterns("/customer/*");
        registrationBean.setName("restTokenFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public JsonFilterFactoryBean jsonFilterFactoryBean() {
        return new JsonFilterFactoryBean();
    }

    @Bean
    public HttpMessageConverters customConverters() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setObjectMapper(new HibernateAwareObjectMapper());
        //解决返回字符串时乱码问题
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setDefaultCharset(Charset.forName("utf-8"));
        return new HttpMessageConverters(jsonConverter, stringConverter);
    }
}
