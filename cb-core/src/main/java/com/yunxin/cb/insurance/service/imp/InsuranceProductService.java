package com.yunxin.cb.insurance.service.imp;

import com.yunxin.cb.insurance.dao.InsuranceInformedMatterDao;
import com.yunxin.cb.insurance.dao.InsuranceProductDao;
import com.yunxin.cb.insurance.entity.InsuranceInformedMatter;
import com.yunxin.cb.insurance.entity.InsuranceProduct;
import com.yunxin.cb.insurance.service.IInsuranceProductService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class InsuranceProductService implements IInsuranceProductService {

    @Resource
    private InsuranceProductDao insuranceProductDao;

    @Resource
    private InsuranceInformedMatterDao insuranceInformedMatterDao;

    /**
     * 获取所有保险产品列表
     * @author      likang
     * @param
     * @return      java.util.List<com.yunxin.cb.insurance.entity.InsuranceProduct>
     * @exception
     * @date        2018/7/18 16:02
     */
    @Override
    public List<InsuranceProduct> getInsuranceProducts() {
        return insuranceProductDao.findInsuranceProductList();
    }

    /**
     * 添加保险产品
     * @author      likang
     * @param insuranceProduct
     * @return      com.yunxin.cb.insurance.entity.InsuranceProduct
     * @exception
     * @date        2018/7/18 16:03
     */
    @Override
    @Transactional
    public InsuranceProduct addInsuranceProduct(InsuranceProduct insuranceProduct) {
        insuranceProduct.setCreateTime(new Date());
        //保存事项id的数组
        int[] matterIds=insuranceProduct.getMatterIds();
        Set<InsuranceInformedMatter> insuranceInformedMatters=insuranceProduct.getInsuranceInformedMatters();
        //先清除所有事项
        insuranceInformedMatters.clear();
        //添加事项
        if (matterIds != null) {
            for (int i = 0; i < matterIds.length; i++) {
                InsuranceInformedMatter matter=insuranceInformedMatterDao.findOne(matterIds[i]);
                insuranceInformedMatters.add(matter);
            }
        }
        insuranceProduct.setInsuranceInformedMatters(insuranceInformedMatters);
        return insuranceProductDao.save(insuranceProduct);
    }

    /**
     * 获取保险产品的分页Page
     * @author      likang
     * @param query
     * @return      org.springframework.data.domain.Page<com.yunxin.cb.insurance.entity.InsuranceProduct>
     * @exception
     * @date        2018/7/18 16:04
     */
    @Override
    public Page<InsuranceProduct> pageInsuranceProduct(PageSpecification<InsuranceProduct> query){
        query.setCustomSpecification(new CustomSpecification<InsuranceProduct>() {
            @Override
            public void buildFetch(Root<InsuranceProduct> root) {

            }
            @Override
            public void addConditions(Root<InsuranceProduct> root, CriteriaQuery<?> query,
                                      CriteriaBuilder builder, List<Predicate> predicates) {
            }
        });
        Page<InsuranceProduct> page = insuranceProductDao.findAll(query, query.getPageRequest());
        return page;
    }

    /**
     * 更新保险产品
     * @author      likang
     * @param insuranceProduct
     * @return      com.yunxin.cb.insurance.entity.InsuranceProduct
     * @exception
     * @date        2018/7/18 16:05
     */
    @Override
    @Transactional
    public InsuranceProduct updateInsuranceProduct(InsuranceProduct insuranceProduct){
        InsuranceProduct oldProduct = insuranceProductDao.findOne(insuranceProduct.getProdId());
        int[] matterIds=insuranceProduct.getMatterIds();
        Set<InsuranceInformedMatter> insuranceInformedMatters=insuranceProduct.getInsuranceInformedMatters();
        insuranceInformedMatters.clear();
        if (matterIds != null) {
            for (int i = 0; i < matterIds.length; i++) {
                InsuranceInformedMatter matter=insuranceInformedMatterDao.findOne(matterIds[i]);
                insuranceInformedMatters.add(matter);
            }
        }
        oldProduct.setInsuranceInformedMatters(insuranceInformedMatters);
        oldProduct.setDescription(insuranceProduct.getDescription());
        oldProduct.setProdName(insuranceProduct.getProdName());
        oldProduct.setProtectionYear(insuranceProduct.getProtectionYear());
        oldProduct.setInsurePeriod(insuranceProduct.getInsurePeriod());
        oldProduct.setTags(insuranceProduct.getTags());
        oldProduct.setInstruction(insuranceProduct.getInstruction());
        oldProduct.setProdImg(insuranceProduct.getProdImg());
        oldProduct.setDescriptionImg(insuranceProduct.getDescriptionImg());
        oldProduct.setEnabled(insuranceProduct.getEnabled());
        return oldProduct;
    }

    /**
     * 根据id获取保险产品
     * @author      likang
     * @param prodId
     * @return      com.yunxin.cb.insurance.entity.InsuranceProduct
     * @exception
     * @date        2018/7/18 20:17
     */
    @Override
    public InsuranceProduct getInsuranceProductById(int prodId){
        return insuranceProductDao.getInsuranceProductById(prodId);
    }

    /**
     * 根据id删除产品
     * @author      likang
     * @param prodId
     * @return      void
     * @exception
     * @date        2018/7/18 20:18
     */
    @Override
    public void removeById(int prodId){
        insuranceProductDao.delete(prodId);
    }

}
