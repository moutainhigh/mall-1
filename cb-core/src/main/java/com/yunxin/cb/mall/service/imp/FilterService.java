package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.FilterItemDao;
import com.yunxin.cb.mall.dao.PropertyFilterDao;
import com.yunxin.cb.mall.entity.Category;
import com.yunxin.cb.mall.entity.FilterItem;
import com.yunxin.cb.mall.entity.PropertyFilter;
import com.yunxin.cb.mall.entity.PropertyFilter_;
import com.yunxin.cb.mall.service.IFilterService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 * Created by chenxing on 2016/1/20.
 */
@Service
@Transactional
public class FilterService implements IFilterService {

    @Resource
    private PropertyFilterDao propertyFilterDao;

    @Resource
    private FilterItemDao filterItemDao;

    @Override
    public PropertyFilter addPropertyFileter(PropertyFilter propertyFilter) {
        propertyFilter.setCreateTime(new Date());
        propertyFilter = propertyFilterDao.save(propertyFilter);
        String[] itemName = propertyFilter.getItemName();
        int[] itemSortOrder = propertyFilter.getItemSortOrder();
        if (itemName != null) {
            for (int i = 0; i < itemName.length; i++) {
                FilterItem filterItem = new FilterItem();
                filterItem.setItemName(itemName[i]);
                filterItem.setSortOrder((short) itemSortOrder[i]);
                filterItem.setPropertyFilter(propertyFilter);
                filterItemDao.save(filterItem);
            }
        }
        return propertyFilter;
    }


    @Override
    public PropertyFilter updatePropertyFilter(PropertyFilter propertyFilter) {
        PropertyFilter dbPropertyFilter = propertyFilterDao.findOne(propertyFilter.getFilterId());
        dbPropertyFilter.setFilterName(propertyFilter.getFilterName());
        dbPropertyFilter.setSortOrder(propertyFilter.getSortOrder());
        dbPropertyFilter.setCategory(propertyFilter.getCategory());
        dbPropertyFilter.setEnabled(propertyFilter.isEnabled());
        int[] itemId = propertyFilter.getItemId();
        String[] itemName = propertyFilter.getItemName();
        int[] itemSortOrder = propertyFilter.getItemSortOrder();
        if (itemName != null) {
            for (int i = 0; i < itemName.length; i++) {
                if (itemId[i] > 0) {
                    FilterItem filterItem = filterItemDao.findOne(itemId[i]);
                    filterItem.setItemName(itemName[i]);
                    filterItem.setSortOrder((short) itemSortOrder[i]);
                } else {
                    FilterItem filterItem = new FilterItem();
                    filterItem.setItemName(itemName[i]);
                    filterItem.setSortOrder((short) itemSortOrder[i]);
                    filterItem.setPropertyFilter(propertyFilter);
                    filterItemDao.save(filterItem);
                }
            }
        }
        return dbPropertyFilter;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PropertyFilter getPropertyFilterById(int segId) {
        PropertyFilter propertyFilter = propertyFilterDao.getFilterDetailById(segId);
        return propertyFilter;
    }

    @Override
    public void removePropertyFilterById(int segId) {
        propertyFilterDao.delete(segId);

    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<PropertyFilter> pagePropertyFilters(final PageSpecification<PropertyFilter> prQuery) {
        prQuery.setCustomSpecification(new CustomSpecification<PropertyFilter>() {

            public void buildFetch(Root<PropertyFilter> root) {
                root.fetch(PropertyFilter_.category, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<PropertyFilter> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
//                Path<Boolean> delPath = root.get(FilterItem_.);
//                predicates.add(builder.equal(delPath, false));
                query.orderBy(builder.desc(root.get(PropertyFilter_.sortOrder)));
            }
        });
        Page<PropertyFilter> pages = propertyFilterDao.findAll(prQuery, prQuery.getPageRequest());
        return pages;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<PropertyFilter> getEnabledFiltersByCategory(Category category) {
        return propertyFilterDao.getEnabledFiltersByCategory(category);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<FilterItem> getFilterItemsByCommodityCategory(int cocaId) {
        return filterItemDao.findByCommodityCategoryId(cocaId);
    }
}
