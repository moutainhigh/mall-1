/**
 *
 */
package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Supplier;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

import java.util.List;


/**
 * @author gonglei
 */
public interface ISupplierService {

    Supplier addSupplier(Supplier supplier) throws EntityExistException;

    Supplier updateSupplier(Supplier supplier) throws EntityExistException;

    Supplier getSupplierById(int supplierId);

    void removeSupplierById(int supplierId);

    void enableSupplierById(boolean enabled, int supplierId);

    Page<Supplier> pageSuppliers(final PageSpecification<Supplier> query);

    List<Supplier> getAllSuppliers();

}
