package com.yunxin.core.web.json.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.yunxin.core.orm.HibernateAwareObjectMapper;

/**
 * @author ALIENWARE E-mail: moxinchn@163.com
 * @version 2017-12-16-0016 20:40
 */
public class CustomerJsonSerializer {

    private ObjectMapper mapper;

    /**
     * @param filter 转换时过滤哪些字段
     */
    public void filter(String filter) {
        mapper = Squiggly.init(new HibernateAwareObjectMapper(), filter);
    }

    public String toJson(Object object) {
        return SquigglyUtils.stringify(mapper, object);
    }
}
