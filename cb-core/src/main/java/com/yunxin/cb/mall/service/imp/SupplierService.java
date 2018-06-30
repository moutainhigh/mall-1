/**
 *
 */
package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.SupplierDao;
import com.yunxin.cb.mall.entity.Supplier;
import com.yunxin.cb.mall.entity.Supplier_;
import com.yunxin.cb.mall.service.ISupplierService;
import com.yunxin.core.exception.EntityExistException;
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
public class SupplierService implements ISupplierService {

    private static final Logger logger = LoggerFactory.getLogger(SupplierService.class);

    @Resource
    private SupplierDao supplierDao;

    @Override
    public Supplier addSupplier(Supplier supplier) throws EntityExistException {
        if (!supplierDao.isUnique(supplier, Supplier_.supplierCode, Supplier_.supplierName)) {
            throw new EntityExistException("供应商编码或供应商名称已存在");
        }
        supplier.setCreateTime(new Date());
        return supplierDao.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) throws EntityExistException {
        if (!supplierDao.isUnique(supplier, Supplier_.supplierCode, Supplier_.supplierName)) {
            throw new EntityExistException("供应商编码或供应商名称已存在");
        }
        Supplier dbSupplier = supplierDao.findOne(supplier.getSupplierId());
        dbSupplier.setSupplierName(supplier.getSupplierName());
        dbSupplier.setSupplierCode(supplier.getSupplierCode());
        dbSupplier.setLocation(supplier.getLocation());
        dbSupplier.setRemark(supplier.getRemark());
        return dbSupplier;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Supplier getSupplierById(int supplierId) {
        return supplierDao.findOne(supplierId);
    }

    @Override
    public void removeSupplierById(int supplierId) {
        supplierDao.delete(supplierId);
    }


    @Override
    public void enableSupplierById(boolean enabled, int supplierId) {
        supplierDao.enabledSupplierById(enabled, supplierId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Supplier> pageSuppliers(final PageSpecification<Supplier> suQuery) {
        suQuery.setCustomSpecification(new CustomSpecification<Supplier>() {

            public void buildFetch(Root<Supplier> root) {
            }

            @Override
            public void addConditions(Root<Supplier> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                Path<Boolean> delPath = root.get(Supplier_.enabled);
                predicates.add(builder.equal(delPath, false));
                query.orderBy(builder.desc(root.get(Supplier_.createTime)));
            }

        });
        Page<Supplier> pages = supplierDao.findAll(suQuery,
                suQuery.getPageRequest());
        return pages;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Supplier> getAllSuppliers() {
        return supplierDao.findByEnabledOrderBySupplierNameAsc(true);
    }

}
