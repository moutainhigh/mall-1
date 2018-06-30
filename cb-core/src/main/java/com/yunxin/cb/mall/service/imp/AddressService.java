package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.DeliveryAddressDao;
import com.yunxin.cb.mall.dao.FreightDao;
import com.yunxin.cb.mall.entity.Customer_;
import com.yunxin.cb.mall.entity.DeliveryAddress;
import com.yunxin.cb.mall.entity.DeliveryAddress_;
import com.yunxin.cb.mall.entity.Freight;
import com.yunxin.cb.mall.query.DeliveryAddressQuery;
import com.yunxin.cb.mall.service.IAddressService;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * @author x001393
 */

@Service
@Transactional
public class AddressService implements IAddressService {

    @Resource
    private DeliveryAddressDao deliveryAddressDao;


    @Resource
    private FreightDao freightDao;

    @Override
    public DeliveryAddress addDeliveryAddress(DeliveryAddress deliveryAddress) {
        if (deliveryAddress.isDefaultAddress()) {
            deliveryAddressDao.setNonDefaultAddressByCustomerId(deliveryAddress.getCustomer().getCustomerId());
        }

        return deliveryAddressDao.save(deliveryAddress);
    }

    private void getEnvoronmentAndFreight(DeliveryAddress deliveryAddress) {
        Freight yf = freightDao.findByAreaCode(deliveryAddress.getDistrict());
        Freight yc = freightDao.findByAreaCode(deliveryAddress.getCity());
        Freight yp = freightDao.findByAreaCode(deliveryAddress.getProvince());
//        if(LogicUtils.isNotNull(yf)&&yf.getPrice()>0) {
//            deliveryAddress.setFrightPrice(yf.getPrice());
//        }else if(LogicUtils.isNotNull(yc)&&yc.getPrice()>0) {
//            deliveryAddress.setFrightPrice(yc.getPrice());
//        }else if(LogicUtils.isNotNull(yp)&&yp.getPrice()>0){
//            deliveryAddress.setFrightPrice(yp.getPrice());
//        }
    }

    @Override
    public DeliveryAddress updateDeliveryAddress(DeliveryAddress deliveryAddress) {
        if (deliveryAddress.isDefaultAddress()) {
            deliveryAddressDao.setNonDefaultAddressByCustomerId(deliveryAddress.getCustomer().getCustomerId());
        }
        DeliveryAddress oldDeliveryAddress = deliveryAddressDao.findOne(deliveryAddress.getAddressId());
        AttributeReplication.copying(deliveryAddress, oldDeliveryAddress, DeliveryAddress_.addressType, DeliveryAddress_.consigneeName, DeliveryAddress_.province,
                DeliveryAddress_.city, DeliveryAddress_.district, DeliveryAddress_.consigneeAddress, DeliveryAddress_.postCode, DeliveryAddress_.consigneeTelephone,
                DeliveryAddress_.consigneeMobile, DeliveryAddress_.defaultAddress, DeliveryAddress_.remark);

        return oldDeliveryAddress;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public DeliveryAddress getDeliveryAddressById(int deliveryAddressId) {
        DeliveryAddress deliveryAddress = deliveryAddressDao.findOne(deliveryAddressId);
        getEnvoronmentAndFreight(deliveryAddress);
        return deliveryAddress;
    }

    @Override
    public void removeDeliveryAddressById(int deliveryAddressId) {
        deliveryAddressDao.delete(deliveryAddressId);

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<DeliveryAddress> pageDeliveryAddresses(
            final DeliveryAddressQuery deliveryAddressQuery) {
        deliveryAddressQuery
                .setCustomSpecification(new CustomSpecification<DeliveryAddress>() {

                    public void buildFetch(Root<DeliveryAddress> root) {
                        root.fetch(DeliveryAddress_.customer, JoinType.INNER);
                    }

                    @Override
                    public void addConditions(Root<DeliveryAddress> root,
                                              CriteriaQuery<?> query, CriteriaBuilder builder,
                                              List<Predicate> predicates) {
                        int customerId = deliveryAddressQuery.getCustomerId();
                        if (customerId > 0) {
                            Path<Integer> customerIdPath = root.get(
                                    DeliveryAddress_.customer).get(
                                    Customer_.customerId);
                            predicates.add(builder.equal(customerIdPath,
                                    customerId));
                        }
                    }
                });
        Page<DeliveryAddress> pages = deliveryAddressDao.findAll(
                deliveryAddressQuery, deliveryAddressQuery.getPageRequest());
        return pages;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public DeliveryAddress queryIsDefaultAddressByCustomerId(int customerId) {
        return deliveryAddressDao.findTopByCustomer_CustomerIdAndDefaultAddress(customerId, true);
    }

    @Override
    public void setDefaultAddrByCustomerIdAndAddrId(int customerId, int deliveryAddressId) {
        DeliveryAddress address = queryIsDefaultAddressByCustomerId(customerId);
        if (null == address) {
            deliveryAddressDao.setDefaultAddressByAddrId(deliveryAddressId);
        } else {
            deliveryAddressDao.setNonDefaultAddressByCustomerId(customerId);
            deliveryAddressDao.setDefaultAddressByAddrId(deliveryAddressId);
        }
    }


    @Override
    @Transactional(readOnly = true)
    public List<DeliveryAddress> getAllAddressesByCustomerId(int customerId) {
        return deliveryAddressDao.findByCustomer_CustomerIdOrderByDefaultAddressDesc(customerId);
    }
}
