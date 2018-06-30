/**
 *
 */
package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Brand;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author gonglei
 */
public interface IBrandService {

    Brand addBrand(Brand brand) throws EntityExistException;

    List<Brand> getAllBrands();

    Brand getBrandById(int brandId);

    Brand updateBrand(Brand brand) throws EntityExistException;

    void removeBrandById(int brandId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<Brand> getDisplayHotBrands();

    Page<Brand> pageBrands(final PageSpecification<Brand> queryRequest);

    void enableBrandById(int brandId, boolean enabled);
}
