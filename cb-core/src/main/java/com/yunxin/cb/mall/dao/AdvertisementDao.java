package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Advertisement;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by chenxing on 2016/1/15.
 */
public interface AdvertisementDao extends JpaRepository<Advertisement, Integer>, JpaSpecificationExecutor<Advertisement>, BaseDao<Advertisement> {

    @Modifying
    @Query("update Advertisement a set a.enabled =?1 where a.advertId=?2")
    void enableByAdvertisementId(boolean enabled, int advertId);

}
