package com.yunxin.cb.system.service;

import com.yunxin.cb.system.entity.Message;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

public interface IMessageService {

    Page<Message> pageMessage(PageSpecification<Message> query);

    Message addMessage(Message message);

    Message getMessage(int messageId);

    void removeMessageById(int messageId);

}
