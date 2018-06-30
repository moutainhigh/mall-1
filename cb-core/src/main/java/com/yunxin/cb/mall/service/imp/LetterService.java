package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.SmsDao;
import com.yunxin.cb.mall.dao.SystemLetterDao;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.query.SmsQuery;
import com.yunxin.cb.mall.service.ILetterService;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.LogicUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class LetterService implements ILetterService {

    @Resource
    private SystemLetterDao systemLetterDao;

    @Resource
    private SmsDao smsDao;

    @Override
    public void readLetter(int taskId) {
        //systemLetterDao.readMessage(taskId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<SystemLetter> pageSystemLetters(final PageSpecification<SystemLetter> query) {

        query.setCustomSpecification(new CustomSpecification<SystemLetter>() {

            @Override
            public void addConditions(Root<SystemLetter> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(SystemLetter_.createTime)));
            }
        });
        Page<SystemLetter> pageSystemLetters = systemLetterDao.findAll(query, query.getPageRequest());
        return pageSystemLetters;

    }


    @Override
    public SystemLetter addSystemLetter(SystemLetter systemLetter) {
        systemLetter.setCreateTime(new Date());
        return systemLetterDao.save(systemLetter);
    }

    @Override
    public SystemLetter updateSystemLetter(SystemLetter systemLetter) {
        SystemLetter systemLetterDB = systemLetterDao.findOne(systemLetter.getLetterId());
        AttributeReplication.copying(systemLetter, systemLetterDB, SystemLetter_.title, SystemLetter_.publishType, SystemLetter_.content, SystemLetter_.recipient);
        return systemLetterDB;
    }

    @Override
    public void publishSystemLetter(int letterId, boolean published) {
        SystemLetter systemLetterDB = systemLetterDao.findOne(letterId);
        systemLetterDB.setPublishTime(new Date());
        systemLetterDB.setPublished(published);
    }


    @Override
    public void deleteSystemLetterById(int letterId) {
        systemLetterDao.delete(letterId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public SystemLetter getSystemLetterById(int letterId) {
        SystemLetter systemLetter = systemLetterDao.findOne(letterId);
        return systemLetter;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getCustomerLetterCount(Customer customer) {
        //return systemLetterDao.countByCustomerAndReaded(customer, false);
        return 0L;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<SystemLetter> pageCustomerLetters(PageSpecification<SystemLetter> query, Customer customer) {
        query.setCustomSpecification(new CustomSpecification<SystemLetter>() {
            @Override
            public void addConditions(Root<SystemLetter> root, CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                predicates.add(builder.equal(root.get(SystemLetter_.published), true));
                predicates.add(builder.or(builder.equal(root.get(SystemLetter_.recipient), ""), builder.like(root.get(SystemLetter_.recipient), "%" + customer.getAccountName() + "%")));
                //predicates.add(builder.like(root.get(SystemLetter_.recipient), "%" + customer.getAccountName() + "%"));
                query.orderBy(builder.desc(root.get(SystemLetter_.createTime)));
            }
        });
        return systemLetterDao.findAll(query, query.getPageRequest());
    }

    @Override
    public Page<SmsLog> pageSms(final SmsQuery smsQuery) {
        createSms(smsQuery);
        return smsDao.findAll(smsQuery, smsQuery.getPageRequest());
    }

    @Override
    public long countSms(SmsQuery smsQuery) {
        createSms(smsQuery);
        return smsDao.count(smsQuery);
    }

    private void createSms(final SmsQuery smsQuery) {
        smsQuery.setCustomSpecification(new CustomSpecification<SmsLog>() {
            @Override
            public void addConditions(Root<SmsLog> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                super.addConditions(root, query, builder, predicates);
                if (LogicUtils.isNotNullAndEmpty(smsQuery.getSmsCode())) {
                    String str = smsQuery.getSmsCode().replaceAll("\\s*", "");
                    predicates.add(builder.like(root.get(SmsLog_.smsCode), "%" + str + "%"));
                }
                if (LogicUtils.isNotNullAndEmpty(smsQuery.getSmsPhone())) {
                    String str = smsQuery.getSmsPhone().replaceAll("\\s*", "");
                    predicates.add(builder.like(root.get(SmsLog_.smsPhone), "%" + str + "%"));
                }

                query.orderBy(builder.desc(root.get(SmsLog_.smsId)));
            }
        });
    }

    @Override
    public SmsLog add(SmsLog smsLog) {
        smsLog.setSmsTime(new Date());
        return smsDao.save(smsLog);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean checkSmsCodeForAdd(String smsCode) {
        SmsLog smsLog = smsDao.findBySmsCode(smsCode);
        if (LogicUtils.isNotNull(smsLog)) {
            return false;
        }
        return true;
    }


    @Override
    public List<SmsLog> addList(List<SmsLog> smsLogs) {
        return smsDao.save(smsLogs);
    }


}
