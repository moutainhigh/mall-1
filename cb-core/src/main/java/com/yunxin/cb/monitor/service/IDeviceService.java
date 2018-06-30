package com.yunxin.cb.monitor.service;

import com.yunxin.cb.monitor.entity.Device;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chenxing on 16/3/23.
 */
public interface IDeviceService {
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Page<Device> pageDevices(PageSpecification<Device> query);

    Device addDevice(Device device);

    Device getDeviceById(int deviceId);

    Device editDevice(Device device);
}
