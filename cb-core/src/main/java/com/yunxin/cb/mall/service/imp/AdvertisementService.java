package com.yunxin.cb.mall.service.imp;

import com.google.common.collect.Lists;
import com.yunxin.cb.mall.dao.AdvertisementDao;
import com.yunxin.cb.mall.entity.Advertisement;
import com.yunxin.cb.mall.entity.Advertisement_;
import com.yunxin.cb.mall.entity.meta.AdvertisementPlace;
import com.yunxin.cb.mall.service.IAdvertisementService;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * Created by chenxing on 2016/1/15.
 */
@Service
@Transactional
public class AdvertisementService implements IAdvertisementService {


    @Resource
    private AdvertisementDao advertisementDao;

    @Override
    public Advertisement addAdvertisement(Advertisement advertisement) throws EntityExistException {
        if (!advertisementDao.isUnique(advertisement, Advertisement_.advertTitle)) {
            throw new EntityExistException("广告标题已存在");
        }
        advertisement.setCreateTime(new Date());
        // 临时
        advertisement.setTemplatePath("");


        String[] clientTypesTemporary = advertisement.getClientTypesTemporary();
        StringBuffer cTypeBuffer = new StringBuffer();
        for (int i = 0; i < clientTypesTemporary.length; i++) {
            if (0 != i) {
                cTypeBuffer.append(",");
            }
            cTypeBuffer.append(clientTypesTemporary[i]);
        }
        advertisement.setClientTypes(cTypeBuffer.toString());
        return advertisementDao.save(advertisement);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Advertisement> pageAdvertisements(final PageSpecification<Advertisement> query) {
        query.setCustomSpecification(new CustomSpecification<Advertisement>() {
            @Override
            public void addConditions(Root<Advertisement> root, CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(Advertisement_.createTime)));
            }
        });
        return advertisementDao.findAll(query, query.getPageRequest());
    }


    @Override
    public Advertisement updateAdvertisement(Advertisement advertisement) throws EntityExistException {
        if (!advertisementDao.isUnique(advertisement, Advertisement_.advertTitle)) {
            throw new EntityExistException("广告标题已存在");
        }
        Advertisement oldAdvertisementDB = advertisementDao.findOne(advertisement.getAdvertId());
        AttributeReplication.copying(advertisement, oldAdvertisementDB, Advertisement_.advertTitle, Advertisement_.advertCode, Advertisement_.advertisementType,
                Advertisement_.advertisementPlace, Advertisement_.videoPath, Advertisement_.advertisementURLType, Advertisement_.advertURL, Advertisement_.picPath,
                Advertisement_.content, Advertisement_.remark,Advertisement_.enabled);

        String[] clientTypesTemporary = advertisement.getClientTypesTemporary();
        StringBuffer cTypeBuffer = new StringBuffer();
        for (int i = 0; i < clientTypesTemporary.length; i++) {
            if (0 != i) {
                cTypeBuffer.append(",");
            }
            cTypeBuffer.append(clientTypesTemporary[i]);
        }
        oldAdvertisementDB.setClientTypes(cTypeBuffer.toString());
        return oldAdvertisementDB;
    }

    @Override
    @Transactional(readOnly = true)
    public Advertisement getAdvertisementById(int advertisementId) {
        return advertisementDao.findOne(advertisementId);
    }

    @Override
    public void enableByAdvertisementId(boolean enabled, int advertId) {
        advertisementDao.enableByAdvertisementId(enabled, advertId);
    }

    @Override
    public void removeAdvertisementById(int advertId) {
        advertisementDao.delete(advertId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Advertisement> getAdvertByCodeAndPlace(String advertCode, AdvertisementPlace advertisementPlace, String clientType) {
        return advertisementDao.findAll(
                new Specification<Advertisement>() {
                    @Override
                    public Predicate toPredicate(Root<Advertisement> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                        List<Predicate> predicates = Lists.newArrayList();
                        if (StringUtils.isNotBlank(advertCode)) {
                            predicates.add(criteriaBuilder.equal(root.get(Advertisement_.advertCode), advertCode));
                        }
                        if (null != advertisementPlace) {
                            predicates.add(criteriaBuilder.equal(root.get(Advertisement_.advertisementPlace), advertisementPlace));
                        }
                        if (StringUtils.isNotBlank(clientType)) {
                            predicates.add(criteriaBuilder.like(root.get(Advertisement_.clientTypes), "%" + clientType + "%"));
                        }
                        predicates.add(criteriaBuilder.equal(root.get(Advertisement_.enabled), true));
                        // 将所有条件用 and 联合起来
                        if (predicates.size() > 0) {
                            return criteriaBuilder.and(predicates
                                    .toArray(new Predicate[predicates.size()]));
                        }
                        return criteriaBuilder.conjunction();
                    }
                }
        );
    }


}
