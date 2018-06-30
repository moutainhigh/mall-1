/**
 *
 */
package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.PriceSection;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPriceService {

    public Page<PriceSection> pagePriceSections(PageSpecification<PriceSection> query);

    public PriceSection addPriceSection(PriceSection priceSection) throws EntityExistException;

    public PriceSection updatePriceSection(PriceSection priceSection) throws EntityExistException;

    public void removePriceSectionById(int sectionId);

    public PriceSection getPriceSectionById(int sectionId);

    public List<PriceSection> getAllPriceSections();

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<PriceSection> getAllPriceSections(boolean enabled);
}
