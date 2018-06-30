/**
 *
 */
package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Store;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author z001075
 */
public interface IStoreService {

    Store addStore(Store store) throws EntityExistException;

    Store updateStore(Store store) throws EntityExistException;

    Store getStoreById(int storeId);

    void removeStoreById(int storeId);

    Page<Store> pageStores(final PageSpecification<Store> query);

    List<Store> getAllStores();

    Store getStoreCode(String storeCode);


//	public Store getStoreByProductId(int productId);

}
