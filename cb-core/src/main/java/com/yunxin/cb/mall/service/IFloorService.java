package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.HomeFloor;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IFloorService {

    public Page<HomeFloor> pageServiceRulesForClean(PageSpecification<HomeFloor> serviceRuleQuery);


    public HomeFloor getHomeFloorById(int serviceRuleId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    HomeFloor getHomeFloorFetchAllById(int floorId);

    public HomeFloor addHomeFloor(HomeFloor homeFloor)throws EntityExistException;

    public HomeFloor updateHomeFloor(HomeFloor homeFloor) throws EntityExistException;


    @Transactional(readOnly = true)
    List<HomeFloor> getEnabledHomeFloors();

    void removeHomeFloorById(int floorId);

    void enableHomeFloorById(int floorId, boolean enabled);
}
