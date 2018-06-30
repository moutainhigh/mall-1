/**
 *
 */
package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.StoreDao;
import com.yunxin.cb.mall.entity.Store;
import com.yunxin.cb.mall.entity.Store_;
import com.yunxin.cb.mall.service.IStoreService;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
 * @author z001075
 */
@Service
@Transactional
public class StoreService implements IStoreService {

    @Resource
    private StoreDao storeDao;

    @Override
    public Store addStore(Store store) throws EntityExistException {
        if (!storeDao.isOrUnique(store, Store_.storeCode, Store_.storeName)) {
            throw new EntityExistException("仓库编码或仓库名已存在");
        }
        return storeDao.save(store);
    }

    @Override
    public Store updateStore(Store store) throws EntityExistException {
        if (!storeDao.isOrUnique(store, Store_.storeCode, Store_.storeName)) {
            throw new EntityExistException("仓库编码或仓库名已存在");
        }
        Store storeOld = storeDao.findOne(store
                .getStoreId());
        AttributeReplication.copying(store, storeOld, Store_.storeName, Store_.storeCode, Store_.province, Store_.city, Store_.district, Store_.address, Store_.post, Store_.remark, Store_.enabled);
        return storeOld;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Store getStoreById(int storeId) {
        return storeDao.findOne(storeId);
    }

    @Override
    public void removeStoreById(int storeId) {
        storeDao.delete(storeId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Store> pageStores(final PageSpecification<Store> query) {
        query.setCustomSpecification(new CustomSpecification<Store>() {
            @Override
            public void addConditions(Root<Store> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                query.orderBy(builder.asc(root.get(Store_.storeName)));
            }
        });
        Page<Store> stores = storeDao.findAll(query, query.getPageRequest());
        return stores;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Store> getAllStores() {
        return storeDao.findAll(new Sort(Direction.ASC, "storeName"));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Store getStoreCode(String storeCode) {
        Store store = storeDao.findTopByStoreCode(storeCode);
        return store;
    }


    // @Override
    // public Store getStoreByProductId(int productId) {
    // Store store =
    // storeDao.getStoreByProductId(productId);
    // return store;
    // }

}
