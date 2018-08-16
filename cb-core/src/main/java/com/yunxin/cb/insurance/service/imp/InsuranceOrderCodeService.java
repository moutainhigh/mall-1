package com.yunxin.cb.insurance.service.imp;

import com.yunxin.cb.insurance.dao.InsuranceEmailDao;
import com.yunxin.cb.insurance.dao.InsuranceOrderCodeDao;
import com.yunxin.cb.insurance.entity.InsuranceEmail;
import com.yunxin.cb.insurance.entity.InsuranceOrderCode;
import com.yunxin.cb.insurance.service.IInsuranceOrderCodeService;
import com.yunxin.cb.mail.EmailSendService;
import com.yunxin.cb.system.entity.Profile;
import com.yunxin.cb.system.meta.ProfileName;
import com.yunxin.cb.system.service.IProfileService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class InsuranceOrderCodeService implements IInsuranceOrderCodeService {


    @Resource
    private InsuranceOrderCodeDao insuranceOrderCodeDao;

    @Resource
    private InsuranceEmailDao insuranceEmailDao;

    @Resource
    private EmailSendService emailSendService;

    @Resource
    private IProfileService iProfileService;
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

    @Override
    public int getInsuranceOrderCodeMaxSort() {
       return insuranceOrderCodeDao.getInsuranceOrderCodeMaxSort();
    }

    /**
     *
     * @param query
     * @return
     */
    @Override
    public Page<InsuranceOrderCode> pageInsuranceOrderCode(PageSpecification<InsuranceOrderCode> query){
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

    @Override
    @Transactional
    public void Reminding() {

       if(isSendEmail()){
           String receiveEmail="Wangt@999shuijingqiu.com";
           String text="保险合同号库存不足,请尽快导入保险合同号";
           Profile profile=iProfileService.getProfileByProfileName(ProfileName.INSURANCE_CODE_RECEIVE_EMAIL);
           if(profile!=null)
               receiveEmail=profile.getFileValue();

           Profile profiles=iProfileService.getProfileByProfileName(ProfileName.INSURANCE_CODE_RECEIVE_CONTEXT);
           if(profiles!=null)
               text=profiles.getFileValue();
           InsuranceEmail insuranceEmail=emailSendService.mailReminding(receiveEmail,"保险合同编号提醒",text);
           if(null!=insuranceEmail)
              insuranceEmailDao.save(insuranceEmail);
       }
    }

     boolean isSendEmail(){
        int maxSort=insuranceOrderCodeDao.getInsuranceOrderCodeMaxSort();
        int notUseed= insuranceOrderCodeDao.getInsuranceOrdernotUseedBySort(0,maxSort);
        int onUseed= insuranceOrderCodeDao.getInsuranceOrdernotUseedBySort(1,maxSort);
        if(notUseed<10)
            return true;
        if(onUseed>0){
            if(notUseed/onUseed<0.1)
                return true;
        }

        return false;
    }
}
