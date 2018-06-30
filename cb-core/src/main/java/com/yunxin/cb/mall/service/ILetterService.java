package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.SmsLog;
import com.yunxin.cb.mall.entity.SystemLetter;
import com.yunxin.cb.mall.query.SmsQuery;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ILetterService {

    Page<SystemLetter> pageSystemLetters(PageSpecification<SystemLetter> letterQueryuery);


    public SystemLetter addSystemLetter(SystemLetter systemLetter);

    public SystemLetter updateSystemLetter(SystemLetter systemLetter);

    void publishSystemLetter(int letterId, boolean published);

    public void deleteSystemLetterById(int letterId);

    SystemLetter getSystemLetterById(int letterId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long getCustomerLetterCount(Customer customer);

    public Page<SystemLetter> pageCustomerLetters(PageSpecification<SystemLetter> query, Customer customer);

    /**
     * 逻辑删除
     *
     * @param taskId
     */
    void readLetter(int taskId);

    public Page<SmsLog> pageSms(SmsQuery smsQuery);

    public long countSms(SmsQuery smsQuery);

    public SmsLog add(SmsLog smsLog);

    public boolean checkSmsCodeForAdd(String smsCode);

    public List<SmsLog> addList(List<SmsLog> smsLogs);
}
