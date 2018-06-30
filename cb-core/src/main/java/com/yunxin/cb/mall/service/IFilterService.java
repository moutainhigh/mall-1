package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Category;
import com.yunxin.cb.mall.entity.FilterItem;
import com.yunxin.cb.mall.entity.PropertyFilter;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by chenxing on 2016/1/20.
 */
public interface IFilterService {


    PropertyFilter addPropertyFileter(PropertyFilter propertyFilter);


    PropertyFilter updatePropertyFilter(PropertyFilter propertyFilter);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    PropertyFilter getPropertyFilterById(int segId);

    void removePropertyFilterById(int segId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Page<PropertyFilter> pagePropertyFilters(PageSpecification<PropertyFilter> prQuery);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<PropertyFilter> getEnabledFiltersByCategory(Category category);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<FilterItem> getFilterItemsByCommodityCategory(int cocaId);
}
