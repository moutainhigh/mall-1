package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.SpecFilter;
import com.yunxin.cb.mall.entity.SpecFilterItem;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by chenpeng 2018年8月20日
 */
public interface ISpecFilterService {

    SpecFilter addSpecFilter(SpecFilter filter);

    SpecFilter updateSpecFilter(SpecFilter filter);

    SpecFilter getSpecFilterById(Integer id);

    void removeSpecFilterById(Integer id);

    Page<SpecFilter> pageSpecFilters(PageSpecification<SpecFilter> prQuery);
}
