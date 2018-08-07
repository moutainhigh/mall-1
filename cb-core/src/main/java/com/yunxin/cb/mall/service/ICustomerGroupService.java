package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.CustomerGroup;
import com.yunxin.cb.mall.vo.CustomerGroupVo;

public interface ICustomerGroupService {
    /**
     * 创建群组
     * @return
     */
    public CustomerGroup createGroup(CustomerGroupVo customerGroupVo,int customerId);
}
