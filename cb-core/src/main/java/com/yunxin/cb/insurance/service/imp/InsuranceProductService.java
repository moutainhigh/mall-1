package com.yunxin.cb.insurance.service.imp;

import com.yunxin.cb.insurance.dao.InsuranceProductDao;
import com.yunxin.cb.insurance.entity.InsuranceProduct;
import com.yunxin.cb.insurance.service.IInsuranceProductService;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class InsuranceProductService implements IInsuranceProductService {

    @Resource
    private InsuranceProductDao insuranceProductDao;
    /**
     * 查询保险产品列表
     * @return
     */
    @Override
    public List<InsuranceProduct> getInsuranceProducts() {
        return insuranceProductDao.findAll();
    }

    /**
     * 添加保险产品
     * @param insuranceProduct
     * @return
     */
    @Override
    @Transactional
    public InsuranceProduct addInsuranceProduct(InsuranceProduct insuranceProduct) {
        insuranceProduct.setCreateTime(new Date());
        return insuranceProductDao.save(insuranceProduct);
    }

    /**
     *
     * @param query
     * @return
     */
    @Override
    public Page<InsuranceProduct> pageInsuranceProduct(PageSpecification<InsuranceProduct> query){
        return null;
    }

    /**
     *
     * @param insuranceProduct
     * @return
     */
    @Override
    public InsuranceProduct updateInsuranceProduct(InsuranceProduct insuranceProduct){
        return null;
    }
}
