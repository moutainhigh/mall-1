package com.yunxin.cb.monitor.service;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.meta.PadState;
import com.yunxin.cb.monitor.entity.Concent;
import com.yunxin.cb.monitor.entity.Device;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gonglei on 16/1/5.
 */
public interface IConcentService {
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long getConcentCount();

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long getDeviceCount();

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Concent getConcentByUid(String ccuid);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<Concent> getAllConcents();

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Page<Concent> pageConcents(PageSpecification<Concent> query);

    Concent addConcent(Concent concent);

    Concent updateConcentCustomer(String concentCode, Customer customer);

    void concentAudit(int concentId, PadState padState, String remark);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<Concent> getConcentsByCustomerId(int customerId);

    Concent updateConcentDevices(String concentCode, List<Device> devices);

    void updateOnline(String concentCode, boolean online);

    List<Device> getDevicesByConcentId(int concentId);

    Concent getConcentById(int concentId);

    Concent updateConcent(Concent concent);

    void removeConcentById(int concentId);
}
