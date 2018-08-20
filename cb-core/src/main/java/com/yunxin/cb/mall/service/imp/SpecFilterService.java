package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.SpecFilterDao;
import com.yunxin.cb.mall.dao.SpecFilterItemDao;
import com.yunxin.cb.mall.entity.SpecFilter;
import com.yunxin.cb.mall.entity.SpecFilterItem;
import com.yunxin.cb.mall.entity.SpecFilter_;
import com.yunxin.cb.mall.service.ISpecFilterService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * Created by chenpeng on 2018年8月20日.
 */
@Service
public class SpecFilterService implements ISpecFilterService {

    @Resource
    private SpecFilterDao specFilterDao;

    @Resource
    private SpecFilterItemDao specFilterItemDao;


    @Override
    @Transactional
    public SpecFilter addSpecFilter(SpecFilter filter) {
        SpecFilter specFilter = specFilterDao.save(filter);
        String[] itemName = filter.getItemName();
        int[] itemSortOrder = filter.getItemSortOrder();
        if (itemName != null) {
            for (int i = 0; i < itemName.length; i++) {
                SpecFilterItem filterItem = new SpecFilterItem();
                filterItem.setItemValue(itemName[i]);
                filterItem.setSortOrder(itemSortOrder[i]);
                filterItem.setSpecFilter(specFilter);
                specFilterItemDao.save(filterItem);
            }
        }
        return specFilter;
    }

    @Override
    @Transactional
    public SpecFilter updateSpecFilter(SpecFilter filter) {
        filter.setUpdateTime(new Date());
        SpecFilter specFilter = specFilterDao.save(filter);
        int[] itemId = filter.getItemId();
        String[] itemName = filter.getItemName();
        int[] itemSortOrder = filter.getItemSortOrder();
        if (itemName != null) {
            for (int i = 0; i < itemName.length; i++) {
                if (itemId[i] > 0) {
                    SpecFilterItem filterItem = specFilterItemDao.findOne(itemId[i]);
                    filterItem.setItemValue(itemName[i]);
                    filterItem.setSortOrder(itemSortOrder[i]);
                } else {
                    SpecFilterItem filterItem = new SpecFilterItem();
                    filterItem.setItemValue(itemName[i]);
                    filterItem.setSortOrder(itemSortOrder[i]);
                    filterItem.setSpecFilter(specFilter);
                    specFilterItemDao.save(filterItem);
                }
            }
        }
        return specFilter;
    }

    @Override
    @Transactional(readOnly = true)
    public SpecFilter getSpecFilterById(Integer id) {
        return specFilterDao.getFilterDetailById(id);
    }

    @Override
    public void removeSpecFilterById(Integer id) {
        specFilterDao.delete(id);
    }

    @Override
    public Page<SpecFilter> pageSpecFilters(PageSpecification<SpecFilter> prQuery) {

        prQuery.setCustomSpecification(new CustomSpecification<SpecFilter>() {
            @Override
            public void addConditions(Root<SpecFilter> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(SpecFilter_.sortOrder)));
            }
        });
        Page<SpecFilter> pages = specFilterDao.findAll(prQuery, prQuery.getPageRequest());

        return pages;
    }

}
