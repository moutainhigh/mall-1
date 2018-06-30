package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.SmsLog;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by z001392 on 2014/8/13.
 */
public interface SmsDao extends SmsPlusDao, JpaRepository<SmsLog, Integer>, JpaSpecificationExecutor<SmsLog>, BaseDao<SmsLog> {

    SmsLog findBySmsCode(String smsCode);


}

interface SmsPlusDao {
}