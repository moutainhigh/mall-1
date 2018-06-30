package com.yunxin.cb.monitor.service.imp;

import com.yunxin.cb.monitor.dao.DeviceDao;
import com.yunxin.cb.monitor.entity.Device;
import com.yunxin.cb.monitor.entity.Device_;
import com.yunxin.cb.monitor.service.IDeviceService;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 * Created by chenxing on 16/3/23.
 */
@Service
@Transactional
public class DeviceService implements IDeviceService {

    @Resource
    private DeviceDao deviceDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Device> pageDevices(final PageSpecification<Device> query) {
        query.setCustomSpecification(new CustomSpecification<Device>() {
            @Override
            public void buildFetch(Root<Device> root) {
                root.fetch(Device_.concent, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<Device> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(Device_.createTime)));
            }
        });
        return deviceDao.findAll(query, query.getPageRequest());
    }

    @Override
    public Device addDevice(Device device) {
        device.setCreateTime(new Date());
        return deviceDao.save(device);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Device getDeviceById(int deviceId) {
        return deviceDao.findOne(deviceId);
    }

    @Override
    public Device editDevice(Device device) {
        Device dbDevice = deviceDao.findOne(device.getDeviceId());
        AttributeReplication.copying(device, dbDevice, Device_.deviceState, Device_.version);
        return dbDevice;
    }
}
