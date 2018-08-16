/**
 *
 */
package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.PriceSectionDao;
import com.yunxin.cb.mall.entity.PriceSection;
import com.yunxin.cb.mall.entity.PriceSection_;
import com.yunxin.cb.mall.service.IPriceService;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author sheh
 */
@Service
@Transactional
public class PriceService implements IPriceService {

    private static final Logger logger = LoggerFactory.getLogger(PriceService.class);

    @Resource
    private PriceSectionDao priceSectionDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<PriceSection> pagePriceSections(PageSpecification<PriceSection> query) {
        query.setCustomSpecification(new CustomSpecification<PriceSection>() {
            @Override
            public void addConditions(Root<PriceSection> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(PriceSection_.startPrice)));
            }

            @Override
            public void buildFetch(Root<PriceSection> root) {
                super.buildFetch(root);
            }
        });
        return priceSectionDao.findAll(query, query.getPageRequest());
    }

    @Override
    public PriceSection addPriceSection(PriceSection priceSection) throws EntityExistException {
        if (!priceSectionDao.isUnique(priceSection, PriceSection_.startPrice, PriceSection_.endPrice)) {
            throw new EntityExistException("价格段已存在");
        }
        return priceSectionDao.save(priceSection);
    }

    @Override
    public PriceSection updatePriceSection(PriceSection priceSection) throws EntityExistException {
        if (!priceSectionDao.isUnique(priceSection, PriceSection_.startPrice, PriceSection_.endPrice)) {
            throw new EntityExistException("价格段已存在");
        }
        PriceSection oldPrice = priceSectionDao.findOne(priceSection.getSectionId());
        AttributeReplication.copying(priceSection, oldPrice, PriceSection_.startPrice, PriceSection_.endPrice, PriceSection_.sectionNo, PriceSection_.enabled, PriceSection_.remark);
        return oldPrice;
    }

    @Override
    public void removePriceSectionById(int sectionId) {
        priceSectionDao.delete(sectionId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PriceSection getPriceSectionById(int sectionId) {
        return priceSectionDao.findOne(sectionId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<PriceSection> getAllPriceSections() {
        return priceSectionDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<PriceSection> getAllPriceSections(boolean enabled) {
        return priceSectionDao.findByEnabledOrderByStartPriceAsc(enabled);
    }

    @Override
    public List<PriceSection> findAllByEnabled() {
        return priceSectionDao.findAllByEnabled();
    }
}
