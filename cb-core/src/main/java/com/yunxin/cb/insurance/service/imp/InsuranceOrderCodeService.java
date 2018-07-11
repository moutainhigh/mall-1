package com.yunxin.cb.insurance.service.imp;

import com.yunxin.cb.insurance.dao.InsuranceOrderCodeDao;
import com.yunxin.cb.insurance.entity.InsuranceOrderCode;
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
public class InsuranceOrderCodeService implements IInsuranceOrderCodeService {

    @Resource
    private InsuranceOrderCodeDao insuranceOrderCodeDao;
    /**
     *
     * @param codeNo
     * @return
     */
    @Override
    public InsuranceOrderCode getInsuranceOrderCodeByCodeNo(String codeNo){
        return insuranceOrderCodeDao.findInsuranceOrderCodeByCodeNo(codeNo);
    }

    /**
     *
     * @param insuranceOrderCode
     */
    @Override
    public void addInsuranceOrderCode(InsuranceOrderCode insuranceOrderCode){
        insuranceOrderCodeDao.save(insuranceOrderCode);
    }

    /**
     *
     * @param query
     * @return
     */
    @Override
    public Page<InsuranceOrderCode> pageInsuranceOrderCode(PageSpecification<InsuranceOrderCode> query){
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
        query.setCustomSpecification(new CustomSpecification<InsuranceOrderCode>() {
            @Override
            public void buildFetch(Root<InsuranceOrderCode> root) {

            }
            @Override
            public void addConditions(Root<InsuranceOrderCode> root, CriteriaQuery<?> query,
                                      CriteriaBuilder builder, List<Predicate> predicates) {
            }
        });
        Page<InsuranceOrderCode> page = insuranceOrderCodeDao.findAll(query, query.getPageRequest());
        return page;
    }
}
