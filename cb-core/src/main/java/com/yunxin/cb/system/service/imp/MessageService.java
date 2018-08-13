package com.yunxin.cb.system.service.imp;

import com.yunxin.cb.system.dao.MessageDao;
import com.yunxin.cb.system.entity.Message;
import com.yunxin.cb.system.meta.PushStatus;
import com.yunxin.cb.system.service.IMessageService;
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
import java.util.List;

@Service
public class MessageService implements IMessageService {

    @Resource
    private MessageDao messageDao;

    /**
     * 系统配置分页信息
     *
     * @param query
     * @return org.springframework.data.domain.Page<com.yunxin.cb.system.entity.Message>
     * @throws
     * @author likang
     * @date 2018/7/19 9:50
     */
    @Override
    public Page<Message> pageMessage(PageSpecification<Message> query) {
        query.setCustomSpecification(new CustomSpecification<Message>() {
            @Override
            public void buildFetch(Root<Message> root) {
            }

            @Override
            public void addConditions(Root<Message> root, CriteriaQuery<?> query,
                                      CriteriaBuilder builder, List<Predicate> predicates) {
            }
        });
        Page<Message> page = messageDao.findAll(query, query.getPageRequest());
        return page;
    }

    /**
     * 添加系统配置
     *
     * @return com.yunxin.cb.system.entity.Message
     * @throws
     * @author likang
     * @date 2018/7/19 10:14
     */
    @Override
    @Transactional
    public Message addMessage(Message message) {
        return messageDao.save(message);
    }

    /**
     * 获取Message详情
     *
     * @param messageId 消息ID
     * @return com.yunxin.cb.system.entity.Message
     * @throws
     * @author likang
     * @date 2018/7/20 16:42
     */
    @Override
    public Message getMessage(int messageId) {
        return messageDao.findOne(messageId);
    }

    @Override
    public Message getMessageByPushStatus(PushStatus pushStatus) {
        return messageDao.getMessageByPushStatus(pushStatus);
    }

    @Override
    public void addMessageByMessageIsExit(){
        for (PushStatus e : PushStatus.values()) {
            Message message = messageDao.getMessageByPushStatus(e);
            if(message==null){
                message=new Message();
                message.setPushStatus(e);
                messageDao.save(message);
            }
        }
    }
}
