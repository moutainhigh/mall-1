package com.yunxin.cb.insurance.service.imp;

import com.yunxin.cb.insurance.dao.InsuranceInformedMatterDao;
import com.yunxin.cb.insurance.dao.InsuranceInformedMatterGroupDao;
import com.yunxin.cb.insurance.entity.InsuranceInformedMatter;
import com.yunxin.cb.insurance.entity.InsuranceInformedMatterGroup;
import com.yunxin.cb.insurance.entity.InsuranceInformedMatterGroup_;
import com.yunxin.cb.insurance.service.IInsuranceInformedMatterGroupService;
import com.yunxin.cb.insurance.service.IInsuranceInformedMatterService;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class InsuranceInformedMatterGroupService implements IInsuranceInformedMatterGroupService {

    @Resource
    private InsuranceInformedMatterGroupDao insuranceInformedMatterGroupDao;

    /**
     * 分页
     * @param query
     * @return
     */
    @Override
    public Page<InsuranceInformedMatterGroup> pageInsuranceInformedMatterGroup(PageSpecification<InsuranceInformedMatterGroup> query){
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
        query.setCustomSpecification(new CustomSpecification<InsuranceInformedMatterGroup>() {
            @Override
            public void buildFetch(Root<InsuranceInformedMatterGroup> root) {

            }
            @Override
            public void addConditions(Root<InsuranceInformedMatterGroup> root, CriteriaQuery<?> query,
                                      CriteriaBuilder builder, List<Predicate> predicates) {
                query.orderBy(builder.asc(root.get(InsuranceInformedMatterGroup_.serNo)));
            }
        });
        Page<InsuranceInformedMatterGroup> page = insuranceInformedMatterGroupDao.findAll(query, query.getPageRequest());
        return page;
    }

    /**
     *
     * @param groupId
     * @return
     */
    @Override
    public InsuranceInformedMatterGroup getInsuranceInformedMatterGroup(int groupId){
        return insuranceInformedMatterGroupDao.getInsuranceInformedMatterGroup(groupId);
    }

    /**
     *
     * @param insuranceInformedMatterGroup
     * @return
     */
    @Override
    public InsuranceInformedMatterGroup addInsuranceInformedMatterGroup(InsuranceInformedMatterGroup insuranceInformedMatterGroup){
        insuranceInformedMatterGroupDao.save(insuranceInformedMatterGroup);
        return insuranceInformedMatterGroup;
    }

    /**
     *
     * @param groupId
     */
    @Override
    public void removeById(int groupId){
        insuranceInformedMatterGroupDao.delete(groupId);
    }

    /**
     *
     * @param insuranceInformedMatterGroup
     * @return
     */
    @Override
    public InsuranceInformedMatterGroup update(InsuranceInformedMatterGroup insuranceInformedMatterGroup){
        InsuranceInformedMatterGroup oldGroup = insuranceInformedMatterGroupDao.findOne(insuranceInformedMatterGroup.getGroupId());
        oldGroup.setDescription(insuranceInformedMatterGroup.getDescription());
        oldGroup.setSerNo(insuranceInformedMatterGroup.getSerNo());
        return oldGroup;
    }

    /**
     *
     * @param enabled
     * @return
     */
    @Override
    public List<InsuranceInformedMatterGroup> findList(int enabled){
        return insuranceInformedMatterGroupDao.findList(enabled);
    }
}
