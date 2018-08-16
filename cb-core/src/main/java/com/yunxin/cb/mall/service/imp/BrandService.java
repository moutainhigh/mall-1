/**
 *
 */
package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.BrandDao;
import com.yunxin.cb.mall.entity.Brand;
import com.yunxin.cb.mall.entity.Brand_;
import com.yunxin.cb.mall.service.IBrandService;
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
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 * @author gonglei
 */
@Service
@Transactional
public class BrandService implements IBrandService {

    private static final Logger logger = LoggerFactory.getLogger(BrandService.class);

    @Resource
    private BrandDao brandDao;

    @Override
    public Brand addBrand(Brand brand) throws EntityExistException {
        if (!brandDao.isOrUnique(brand, Brand_.brandNo, Brand_.brandName)) {
            throw new EntityExistException("品牌编号或品牌中文名称已存在");
        }
//        brand.setEnabled(true);
        brand.setCreateTime(new Date());
        return brandDao.save(brand);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Brand> getAllBrands() {
        return brandDao.findByEnabledOrderByBrandNameAsc(true);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Brand> getDisplayHotBrands() {
        return brandDao.findByEnabledAndDisplayAndHotOrderByBrandNameAsc(true, true, true);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Brand> pageBrands(final PageSpecification<Brand> queryRequest) {
        queryRequest.setCustomSpecification(new CustomSpecification<Brand>() {

            @Override
            public void buildFetch(Root<Brand> root) {
                root.fetch(Brand_.category, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<Brand> root, CriteriaQuery<?> query,
                                      CriteriaBuilder builder, List<Predicate> predicates) {
//                predicates.add(builder.equal(root.get(Brand_.enabled), true));
                query.orderBy(builder.desc(root.get(Brand_.createTime)));
            }

        });
        Page<Brand> users = brandDao.findAll(queryRequest, queryRequest.getPageRequest());
        return users;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Brand getBrandById(int brandId) {
        return brandDao.findByBrandId(brandId);
    }

    @Override
    public Brand updateBrand(Brand brand) throws EntityExistException {
        if (!brandDao.isOrUnique(brand, Brand_.brandNo, Brand_.brandName)) {
            throw new EntityExistException("品牌编号或品牌中文名称已存在");
        }
        Brand oldBrand = brandDao.findOne(brand.getBrandId());
        AttributeReplication.copying(brand, oldBrand, Brand_.brandNo, Brand_.brandName, Brand_.brandEnName, Brand_.brandTitle, Brand_.brandKey
                , Brand_.website, Brand_.display, Brand_.picPath, Brand_.description, Brand_.seoKey, Brand_.seoTitle, Brand_.seoDescription,
                Brand_.hot, Brand_.remark, Brand_.enabled, Brand_.category,Brand_.sort);
        return oldBrand;
    }

    @Override
    public void removeBrandById(int brandId) {
        brandDao.delete(brandId);
    }

    @Override
    public void enableBrandById(int brandId, boolean enabled) {
        brandDao.enableBrandById(enabled, brandId);
    }

    @Override
    public String checkBrandNoAndBrandName(Brand brand) {
        if (!brandDao.isOrUnique(brand, Brand_.brandNo, Brand_.brandName)) {
            return "failure";
        }
        return "success";
    }

}
