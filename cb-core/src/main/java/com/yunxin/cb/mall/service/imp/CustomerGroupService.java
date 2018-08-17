package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.im.RongCloudService;
import com.yunxin.cb.mall.dao.CustomerDao;
import com.yunxin.cb.mall.dao.CustomerGroupDao;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.CustomerGroup;
import com.yunxin.cb.mall.service.ICustomerGroupService;
import com.yunxin.cb.mall.vo.CustomerGroupVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerGroupService implements ICustomerGroupService {

    @Resource
    private CustomerGroupDao customerGroupDao;
    @Resource
    private CustomerDao customerDao;
    @Resource
    private RongCloudService rongCloudService;
    @Override
    public CustomerGroup createGroup(CustomerGroupVo customerGroupVo,int customerId) {

        CustomerGroup customerGroup=new CustomerGroup();
        customerGroup.setCreateTime(new Date());
        Customer customer=new Customer();
        customer.setCustomerId(customerId);
        customerGroup.setCustomer(customer);
        customerGroup.setGroupName(customerGroupVo.getGroupName());
        CustomerGroup dbCustomerGroup=customerGroupDao.save(customerGroup);
        try {
          boolean flag=rongCloudService.groupCreate(String.valueOf(customerId),String.valueOf(dbCustomerGroup.getCustomerGroupId()),customerGroupVo.getGroupName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbCustomerGroup;
    }
}
