package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Advertisement;
import com.yunxin.cb.mall.entity.meta.AdvertisementPlace;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by chenxing on 2016/1/15.
 */
@Service
@Transactional
public interface IAdvertisementService {

    Advertisement addAdvertisement(Advertisement arAdvertisement) throws EntityExistException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Page<Advertisement> pageAdvertisements(PageSpecification<Advertisement> query);

    Advertisement updateAdvertisement(Advertisement arAdvertisement) throws EntityExistException;

    Advertisement getAdvertisementById(int advertisementId);

    /**
     * 逻辑删除
     *
     * @param enabled
     * @param advertId
     */
    void enableByAdvertisementId(boolean enabled, int advertId);

    void removeAdvertisementById(int advertId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<Advertisement> getAdvertByCodeAndPlace(String advertCode, AdvertisementPlace advertisementPlace, String clientType);
}
