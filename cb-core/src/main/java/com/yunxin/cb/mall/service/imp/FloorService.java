package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.*;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.service.IFloorService;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
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

@Service
@Transactional
public class FloorService implements IFloorService {

    @Resource
    private HomeFloorDao homeFloorDao;

    @Resource
    private FloorCommodityDao floorCommodityDao;

    @Resource
    private FloorCategoryDao floorCategoryDao;

    @Resource
    private FloorBrandDao floorBrandDao;

    @Resource
    private FloorAdvertDao floorAdvertDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public HomeFloor getHomeFloorById(int floorId) {
        return homeFloorDao.findOne(floorId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public HomeFloor getHomeFloorFetchAllById(int floorId) {
        return homeFloorDao.fetchAllById(floorId);
    }

    @Override
    public HomeFloor addHomeFloor(HomeFloor homeFloor) {
        int[] categoryId = homeFloor.getCategoryId();
        int[] categoryOrder = homeFloor.getCategoryOrder();
        int[] commodityId = homeFloor.getCommodityId();
        int[] commodityOrder = homeFloor.getCommodityOrder();
        int[] brandId = homeFloor.getBrandId();
        int[] brandOrder = homeFloor.getBrandOrder();
        int[] advertId = homeFloor.getAdvertId();
        int[] advertOrder = homeFloor.getAdvertOrder();
        homeFloor = homeFloorDao.save(homeFloor);
        batchAddFloorCategoryAndCommodity(homeFloor, categoryId, categoryOrder, commodityId, commodityOrder,brandId,brandOrder,advertId,advertOrder);
        return homeFloor;
    }

    private void batchAddFloorCategoryAndCommodity(HomeFloor homeFloor, int[] categoryId, int[] categoryOrder, int[] commodityId, int[] commodityOrder,int[] brandId,int[] brandOrder,int[] advertId,int[] advertOrder) {
        if (categoryId != null) {
            for (int i = 0; i < categoryId.length; i++) {
                FloorCategoryId id = new FloorCategoryId(homeFloor.getFloorId(), categoryId[i]);
                FloorCategory floorCategory = new FloorCategory();
                floorCategory.setId(id);
                floorCategory.setCategory(new Category(categoryId[i]));
                floorCategory.setHomeFloor(homeFloor);
                floorCategory.setSortOrder(categoryOrder[i]);
                floorCategoryDao.save(floorCategory);
            }
            homeFloor.setCategoryAmount(categoryId.length);
        }
        if (commodityId != null) {
            for (int i = 0; i < commodityId.length; i++) {
                FloorCommodityId id = new FloorCommodityId(homeFloor.getFloorId(), commodityId[i]);
                FloorCommodity floorCommodity = new FloorCommodity();
                floorCommodity.setId(id);
                floorCommodity.setCommodity(new Commodity(commodityId[i]));
                floorCommodity.setHomeFloor(homeFloor);
                floorCommodity.setSortOrder(commodityOrder[i]);
                floorCommodityDao.save(floorCommodity);
            }
            homeFloor.setCommodityAmount(commodityId.length);
        }
        if(brandId != null){
            for (int i = 0; i < brandId.length; i++) {
                FloorBrandId id = new FloorBrandId(homeFloor.getFloorId(), brandId[i]);
                FloorBrand floorBrand = new FloorBrand();
                floorBrand.setId(id);
                floorBrand.setBrand(new Brand(brandId[i]));
                floorBrand.setHomeFloor(homeFloor);
                floorBrand.setSortOrder(brandOrder[i]);
                floorBrandDao.save(floorBrand);
            }
            homeFloor.setBrandAmount(brandId.length);
        }
        if(advertId != null){
            for(int i=0;i < advertId.length; i++){
                FloorAdvertId id = new FloorAdvertId(homeFloor.getFloorId(),advertId[i]);
                FloorAdvert floorAdvert = new FloorAdvert();
                floorAdvert.setId(id);
                floorAdvert.setAdvertisement(new Advertisement(advertId[i]));
                floorAdvert.setHomeFloor(homeFloor);
                floorAdvert.setSortOrder(advertOrder[i]);
                floorAdvertDao.save(floorAdvert);
            }
            homeFloor.setAdvertAmount(advertId.length);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HomeFloor> pageServiceRulesForClean(
            PageSpecification<HomeFloor> serviceRuleQuery) {
        serviceRuleQuery.setCustomSpecification(new CustomSpecification<HomeFloor>() {
            @Override
            public void addConditions(Root<HomeFloor> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                builder.desc(root.get(HomeFloor_.enabled));
                builder.asc(root.get(HomeFloor_.sortOrder));
            }
        });
        Page<HomeFloor> pages = homeFloorDao.findAll(serviceRuleQuery, serviceRuleQuery.getPageRequest());
        return pages;
    }

    @Override
    @Transactional(readOnly = true)
    public List<HomeFloor> getEnabledHomeFloors() {
        return homeFloorDao.findEnabledHomeFloors();
    }

    @Override
    public HomeFloor updateHomeFloor(HomeFloor homeFloor) throws EntityExistException {
        if (!homeFloorDao.isUnique(homeFloor, HomeFloor_.floorName)) {
            throw new EntityExistException("楼层名称已存在！");
        }
        HomeFloor dbhomeFloor = homeFloorDao.findOne(homeFloor.getFloorId());

        AttributeReplication.copying(homeFloor, dbhomeFloor, HomeFloor_.categoryAmount, HomeFloor_.commodityAmount,HomeFloor_.advertAmount,HomeFloor_.brandAmount, HomeFloor_.enabled, HomeFloor_.floorName,
                HomeFloor_.imagePath, HomeFloor_.iconPath, HomeFloor_.remark, HomeFloor_.sortOrder, HomeFloor_.floorLayout);
        int[] categoryId = homeFloor.getCategoryId();
        int[] categoryOrder = homeFloor.getCategoryOrder();
        int[] commodityId = homeFloor.getCommodityId();
        int[] commodityOrder = homeFloor.getCommodityOrder();
        int[] brandId = homeFloor.getBrandId();
        int[] brandOrder = homeFloor.getBrandOrder();
        int[] advertId = homeFloor.getAdvertId();
        int[] advertOrder = homeFloor.getAdvertOrder();
        floorCategoryDao.emptyByHomeFloor(dbhomeFloor);
        floorCommodityDao.emptyByHomeFloor(dbhomeFloor);
        floorBrandDao.emptyByHomeFloor(dbhomeFloor);
        floorAdvertDao.emptyByHomeFloor(dbhomeFloor);
        batchAddFloorCategoryAndCommodity(dbhomeFloor, categoryId, categoryOrder, commodityId, commodityOrder,brandId, brandOrder,advertId,advertOrder);
        return dbhomeFloor;
    }

    @Override
    public void removeHomeFloorById(int floorId) {
        homeFloorDao.delete(floorId);
    }

    @Override
    public void enableHomeFloorById(int floorId, boolean enabled) {
        homeFloorDao.enableHomeFloorById(enabled, floorId);
    }


}
