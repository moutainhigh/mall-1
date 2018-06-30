package com.yunxin.cb.monitor.dao;

import com.yunxin.cb.monitor.entity.Concent;
import com.yunxin.cb.monitor.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by gonglei on 16/1/5.
 */
public interface DeviceDao extends JpaRepository<Device, Integer>, JpaSpecificationExecutor<Device> {

    Device findByDeviceCode(String deviceCode);

    @Modifying
    @Query("update Device d set d.online=?1 where d.concent=?2")
    void updateOnlineByConcent(boolean online, Concent concent);
}
