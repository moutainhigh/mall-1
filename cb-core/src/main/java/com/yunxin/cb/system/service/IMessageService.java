package com.yunxin.cb.system.service;

import com.yunxin.cb.system.entity.Message;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

public interface IMessageService {

    public Page<Message> pageMessage(PageSpecification<Message> query);

    public Message addMessage(Message message);

    public Message getMessage(int fileId);

}
