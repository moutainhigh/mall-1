package com.yunxin.cb.insurance.service.imp;

import com.yunxin.cb.insurance.dao.InsuranceInformedMatterDao;
import com.yunxin.cb.insurance.dao.InsuranceOrderCodeDao;
import com.yunxin.cb.insurance.entity.InsuranceInformedMatter;
import com.yunxin.cb.insurance.entity.InsuranceOrderCode;
import com.yunxin.cb.insurance.service.IInsuranceInformedMatterService;
import com.yunxin.cb.insurance.service.IInsuranceOrderCodeService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class InsuranceInformedMatterService implements IInsuranceInformedMatterService {

    @Resource
    private InsuranceInformedMatterDao insuranceInformedMatterDao;

    /**
     * 分页
     * @param query
     * @return
     */
    public Page<InsuranceInformedMatter> pageInsuranceInformedMatter(PageSpecification<InsuranceInformedMatter> query){
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
        query.setCustomSpecification(new CustomSpecification<InsuranceInformedMatter>() {
            @Override
            public void buildFetch(Root<InsuranceInformedMatter> root) {

            }
            @Override
            public void addConditions(Root<InsuranceInformedMatter> root, CriteriaQuery<?> query,
                                      CriteriaBuilder builder, List<Predicate> predicates) {
            }
        });
        Page<InsuranceInformedMatter> page = insuranceInformedMatterDao.findAll(query, query.getPageRequest());
        return page;
    }

    /**
     *
     * @param metterId
     * @return
     */
    public InsuranceInformedMatter getInsuranceInformedMatter(int metterId){
        return insuranceInformedMatterDao.findOne(metterId);
    }

    /**
     *
     * @param insuranceInformedMatter
     * @return
     */
    public InsuranceInformedMatter addInsuranceInformedMatter(InsuranceInformedMatter insuranceInformedMatter){
        insuranceInformedMatterDao.save(insuranceInformedMatter);
        return insuranceInformedMatter;
    }

    /**
     *
     * @param metterId
     */
    public void removeByid(int metterId){
        insuranceInformedMatterDao.delete(metterId);
    }

    /**
     *
     * @param insuranceInformedMatter
     * @return
     */
    public InsuranceInformedMatter update(InsuranceInformedMatter insuranceInformedMatter){
        return insuranceInformedMatter;
    }
}
