package com.yunxin.cb.system.dao;


import com.yunxin.cb.system.entity.Message;
import com.yunxin.cb.system.meta.PushStatus;
import com.yunxin.core.orm.BaseDaoImpl;
import org.apache.commons.collections.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yangzhen
 */
public class MessageDaoImpl extends BaseDaoImpl<Message> implements MessagePlusDao {

    public static volatile SingularAttribute<Message, PushStatus> pushStatusSingularAttribute;

    private void persistPushStatus(List<PushStatus> messageStatusList) {
        if (CollectionUtils.isNotEmpty(messageStatusList)) {
            for (PushStatus messageStatus : messageStatusList) {
                Message message = new Message();
                message.setPushStatus(messageStatus);
                entityManager.persist(message);
            }
        }
    }

    private Message findOneProfile(PushStatus messageStatus) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> criteriaQuery = criteriaBuilder.createQuery(Message.class);
        Root<Message> root = criteriaQuery.from(Message.class);
        Predicate predicate = criteriaBuilder.equal(root.get(pushStatusSingularAttribute), messageStatus);
        criteriaQuery.where(predicate);
        List<Message> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        return CollectionUtils.isNotEmpty(resultList) ? resultList.get(0) : null;
    }

    private List<Message> findAllMessage() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> criteriaQuery = criteriaBuilder.createQuery(Message.class);
        Root<Message> root = criteriaQuery.from(Message.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Message> generateAllMessage() {
        List<Message> messageList = this.findAllMessage();
        PushStatus[] messageStatusArr = PushStatus.values();
        if (CollectionUtils.isEmpty(messageList)) {
            persistPushStatus(Arrays.asList(messageStatusArr));
            return this.findAllMessage();
        } else {
            List<PushStatus> existProfiles = messageList.stream().map(Message::getPushStatus).collect(Collectors.toList());
            List<PushStatus> newProfileNames = Arrays.stream(messageStatusArr).filter(profileName -> !existProfiles.contains(messageStatusArr)).collect(Collectors.toList());
            persistPushStatus(newProfileNames);
            return this.findAllMessage();
        }
    }

}