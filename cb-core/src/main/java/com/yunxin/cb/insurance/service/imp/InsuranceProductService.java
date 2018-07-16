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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        List<PageSpecification.FilterDescriptor> list=query.getFilter().getFilters();
        for (PageSpecification.FilterDescriptor filterDescriptor:list
                ) {
            if("createTime".equals(filterDescriptor.getField())){

                Date createTime= null;
                SimpleDateFormat simpleDateFormats=new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date dates=simpleDateFormats.parse(String.valueOf(filterDescriptor.getValue()));
                    String createTimes=simpleDateFormat.format(dates);
                    filterDescriptor.setValue(createTimes);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
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
     *
     * @param insuranceProduct
     * @return
     */
    @Override
    @Transactional
    public InsuranceProduct updateInsuranceProduct(InsuranceProduct insuranceProduct){
        InsuranceProduct oldProduct = insuranceProductDao.findOne(insuranceProduct.getProdId());
        oldProduct.setDescription(insuranceProduct.getDescription());
        oldProduct.setProdName(insuranceProduct.getProdName());
        oldProduct.setProtectionYear(insuranceProduct.getProtectionYear());
        oldProduct.setInsurePeriod(insuranceProduct.getInsurePeriod());
        oldProduct.setTags(insuranceProduct.getTags());
        oldProduct.setInstruction(insuranceProduct.getInstruction());
        oldProduct.setProdImg(insuranceProduct.getProdImg());
        oldProduct.setDescriptionImg(insuranceProduct.getDescriptionImg());
        return oldProduct;
    }

    /**
     *
     * @param prodId
     * @return
     */
    @Override
    public InsuranceProduct getInsuranceProductById(int prodId){
        return insuranceProductDao.getInsuranceProductById(prodId);
    }

    /**
     *
     * @param prodId
     */
    @Override
    public void removeById(int prodId){
        insuranceProductDao.delete(prodId);
    }

    /**
     *
     * @param matterId
     * @param matterId
     * @return
     */
    @Override
    @Transactional
    public InsuranceProduct removeInsuranceProductMatter(int prodId,int matterId){
        InsuranceProduct oldProduct = insuranceProductDao.getInsuranceProductById(prodId);
        Set<InsuranceInformedMatter> insuranceInformedMatters=oldProduct.getInsuranceInformedMatters();
        for (InsuranceInformedMatter insuranceInformedMatter: insuranceInformedMatters ) {
            if(insuranceInformedMatter.getMatterId()==matterId){
                insuranceInformedMatters.remove(insuranceInformedMatter);
            }
        }
        return oldProduct;
    }

    /**
     *
     * @param prodId
     * @param matterId
     * @return
     */
    @Override
    @Transactional
    public InsuranceProduct addInsuranceProductMatter(int prodId,int matterId){
        InsuranceProduct oldProduct = insuranceProductDao.getInsuranceProductById(prodId);
        InsuranceInformedMatter insuranceInformedMatter=insuranceInformedMatterDao.findOne(matterId);
        Set<InsuranceInformedMatter> insuranceInformedMatters=oldProduct.getInsuranceInformedMatters();
        insuranceInformedMatters.add(insuranceInformedMatter);
        return oldProduct;
    }
}
