package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.GuaranteeDao;
import com.yunxin.cb.mall.dao.HomeFloorDao;
import com.yunxin.cb.mall.dao.OrderDao;
import com.yunxin.cb.mall.service.IGuaranteeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class GuaranteeService implements IGuaranteeService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Resource
    private GuaranteeDao guaranteeDao;

    @Resource
    private OrderDao orderDao;

    @Resource
    private HomeFloorDao homeFloorDao;


}
