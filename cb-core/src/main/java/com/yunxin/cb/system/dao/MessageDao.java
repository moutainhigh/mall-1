package com.yunxin.cb.system.dao;


import com.yunxin.cb.system.entity.Message;
import com.yunxin.cb.system.meta.PushStatus;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author tanggangyi
 */
public interface MessageDao extends JpaRepository<Message, Integer>, JpaSpecificationExecutor<Message>, BaseDao<Message>, MessagePlusDao {

    @Query("select p from Message p where p.pushStatus = ?1")
    Message getMessageByPushStatus(PushStatus pushStatus);
}

interface MessagePlusDao {

    List<Message> generateAllMessage();

}