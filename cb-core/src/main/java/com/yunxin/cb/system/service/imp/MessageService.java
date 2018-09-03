package com.yunxin.cb.system.service.imp;

import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.service.imp.AttachmentService;
import com.yunxin.cb.system.dao.MessageDao;
import com.yunxin.cb.system.entity.Message;
import com.yunxin.cb.system.entity.Message_;
import com.yunxin.cb.system.service.IMessageService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.apache.commons.lang.StringUtils;
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

    @Resource
    private AttachmentService attachmentService;

    /**
     * 功能描述: 消息列表分页查询
     *
     * @param: [query]
     * @return: org.springframework.data.domain.Page<com.yunxin.cb.system.entity.Message>
     * @auther: yangzhen
     * @date: 2018/8/13 19:57
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
                query.orderBy(builder.desc(root.get(Message_.createTime)));
            }
        });
        Page<Message> page = messageDao.findAll(query, query.getPageRequest());
        return page;
    }

    /**
     * 功能描述: 消息新增/修改
     *
     * @param: [message]
     * @return: com.yunxin.cb.system.entity.Message
     * @auther: yangzhen
     * @date: 2018/8/13 19:58
     */
    @Override
    @Transactional
    public Message addMessage(Message message) {
        if(null == message.getPushTime()){
            String imgUrl = message.getDigestPic();
            message.setDigestPic(!StringUtils.isEmpty(imgUrl)?imgUrl.split(",")[0]:null);
            message = messageDao.save(message);

            //保存图片路径
            attachmentService.deleteAttachmentPictures(ObjectType.MESSAGEDIGEST,message.getMessageId());
            if(!StringUtils.isEmpty(imgUrl)){
                attachmentService.addAttachmentPictures(ObjectType.MESSAGEDIGEST,message.getMessageId(),imgUrl);
            }
        }else{
            //推送消息时，仅更新推送状态
            message = messageDao.save(message);
        }
        return message;
    }

    /**
     * 功能描述: 获取消息详情
     *
     * @param: [messageId]消息ID
     * @return: com.yunxin.cb.system.entity.Message
     * @auther: yangzhen
     * @date: 2018/8/13 19:58
     */
    @Override
    public Message getMessage(int messageId) {
        return messageDao.findOne(messageId);
    }

    /**
     * 功能描述: 通过消息ID删除消息
     *
     * @param: messageId 消息ID
     * @return:
     * @auther: yangzhen
     * @date: 2018/8/15 17:24
     */
    @Override
    @Transactional
    public void removeMessageById(int messageId) {
        attachmentService.deleteAttachmentPictures(ObjectType.MESSAGEDIGEST,messageId);
        messageDao.delete(messageId);
    }
}
