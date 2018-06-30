package com.yunxin.cb.monitor.service.imp;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.meta.PadState;
import com.yunxin.cb.monitor.dao.ConcentDao;
import com.yunxin.cb.monitor.dao.DeviceDao;
import com.yunxin.cb.monitor.entity.Concent;
import com.yunxin.cb.monitor.entity.Concent_;
import com.yunxin.cb.monitor.entity.Device;
import com.yunxin.cb.monitor.entity.Device_;
import com.yunxin.cb.monitor.service.IConcentService;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gonglei on 16/1/5.
 */
@Service
@Transactional
public class ConcentService implements IConcentService {

    @Resource
    private ConcentDao concentDao;

    @Resource
    private DeviceDao deviceDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getConcentCount() {
        return concentDao.count();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getDeviceCount() {
        return deviceDao.count();
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Concent getConcentByUid(String ccuid) {
        return concentDao.findByConcentCode(ccuid);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Concent> getAllConcents() {
        return concentDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Concent> pageConcents(final PageSpecification<Concent> query) {
        query.setCustomSpecification(new CustomSpecification<Concent>() {
            @Override
            public void buildFetch(Root<Concent> root) {
                root.fetch(Concent_.customer, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<Concent> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(Concent_.concentName)));
            }
        });
        return concentDao.findAll(query, query.getPageRequest());
    }

    @Override
    public Concent addConcent(Concent concent) {
        return concentDao.save(concent);
    }

    @Override
    public Concent updateConcentCustomer(String concentCode, Customer customer) {
        Concent concent = concentDao.findByConcentCode(concentCode);
        concent.setCustomer(customer);
        return concent;
    }


    /**
     * PAD审核
     **/
    @Override
    public void concentAudit(int concentId, PadState padState, String remark) {
        Concent concentDb = concentDao.findOne(concentId);
        concentDb.setPadState(padState);
        concentDb.setRemark(remark);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Concent> getConcentsByCustomerId(int customerId) {
        return concentDao.findByCustomerIdFetchDevices(customerId);
    }

    @Override
    public Concent updateConcentDevices(String concentCode, List<Device> devices) {
        Concent concent = concentDao.findByConcentCode(concentCode);
        //设置PAD下所有设备离线
        deviceDao.updateOnlineByConcent(false, concent);
        for (Device device : devices) {
            Device dbDevice = deviceDao.findByDeviceCode(device.getDeviceCode());
            if (dbDevice != null) {
                if (dbDevice.getConcent().getConcentId() != concent.getConcentId()) {
                    dbDevice.setConcent(concent);
                }
            } else {
                device.setCreateTime(new Date());
                device.setConcent(concent);
                deviceDao.save(device);
            }
        }
        return concent;
    }

    @Override
    public void updateOnline(String concentCode, boolean online) {
        Concent concent = concentDao.findByConcentCode(concentCode);
        concent.setOnline(online);
        if (!online) {
            deviceDao.updateOnlineByConcent(false, concent);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Device> getDevicesByConcentId(int concentId) {
        return deviceDao.findAll(new Specification<Device>() {
            @Override
            public Predicate toPredicate(Root<Device> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                predicates.add(criteriaBuilder.equal(root.get(Device_.concent).get(Concent_.concentId), concentId));
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Device_.createTime)));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Concent getConcentById(int concentId) {
        return concentDao.findOne(concentId);
    }

    @Override
    public Concent updateConcent(Concent concent) {
        Concent dbConcent = concentDao.findOne(concent.getConcentId());
        AttributeReplication.copying(concent, dbConcent, Concent_.concentName, Concent_.padState, Concent_.remark);
        return dbConcent;
    }

    public void removeConcentById(int concentId) {
        concentDao.delete(concentId);
    }
}
