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
     * 分页获取事项组信息
     * @author      likang
     * @param query
     * @return      org.springframework.data.domain.Page<com.yunxin.cb.insurance.entity.InsuranceInformedMatterGroup>
     * @exception
     * @date        2018/7/18 20:18
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
     * 根据id获取事项组
     * @author      likang
     * @param groupId
     * @return      com.yunxin.cb.insurance.entity.InsuranceInformedMatterGroup
     * @exception
     * @date        2018/7/18 20:19
     */
    @Override
    public InsuranceInformedMatterGroup getInsuranceInformedMatterGroup(int groupId){
        return insuranceInformedMatterGroupDao.getInsuranceInformedMatterGroup(groupId);
    }

    /**
     * 添加事项组
     * @author      likang
     * @param insuranceInformedMatterGroup
     * @return      com.yunxin.cb.insurance.entity.InsuranceInformedMatterGroup
     * @exception
     * @date        2018/7/18 20:19
     */
    @Override
    public InsuranceInformedMatterGroup addInsuranceInformedMatterGroup(InsuranceInformedMatterGroup insuranceInformedMatterGroup){
        insuranceInformedMatterGroupDao.save(insuranceInformedMatterGroup);
        return insuranceInformedMatterGroup;
    }

    /**
     * 根据id删除事项组
     * @author      likang
     * @param groupId
     * @return      void
     * @exception
     * @date        2018/7/18 20:20
     */
    @Override
    public void removeById(int groupId){
        insuranceInformedMatterGroupDao.delete(groupId);
    }

    /**
     * 更新事项组
     * @author      likang
     * @param insuranceInformedMatterGroup
     * @return      com.yunxin.cb.insurance.entity.InsuranceInformedMatterGroup
     * @exception
     * @date        2018/7/18 20:20
     */
    @Override
    public InsuranceInformedMatterGroup update(InsuranceInformedMatterGroup insuranceInformedMatterGroup){
        InsuranceInformedMatterGroup oldGroup = insuranceInformedMatterGroupDao.findOne(insuranceInformedMatterGroup.getGroupId());
        oldGroup.setDescription(insuranceInformedMatterGroup.getDescription());
        oldGroup.setSerNo(insuranceInformedMatterGroup.getSerNo());
        oldGroup.setEnabled(insuranceInformedMatterGroup.getEnabled());
        return oldGroup;
    }

    /**
     * 根据启用状态查询事项组
     * @author      likang
     * @param enabled
     * @return      java.util.List<com.yunxin.cb.insurance.entity.InsuranceInformedMatterGroup>
     * @exception
     * @date        2018/7/18 20:20
     */
    @Override
    public List<InsuranceInformedMatterGroup> findList(int enabled){
        return insuranceInformedMatterGroupDao.findList(enabled);
    }

    /**
     * 更新事项组使用状态
     * @author      likang
     * @param groupId
     * @return      void
     * @exception
     * @date        2018/7/20 10:15
     */
    @Override
    public void enableInformedMatterGroupById(int groupId,int enabled){
        insuranceInformedMatterGroupDao.enableInformedMatterGroupById(groupId,enabled);
    }
}
