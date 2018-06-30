package com.yunxin.cb.sns.dao;

import com.yunxin.cb.sns.entity.CustomerFriend;
import com.yunxin.cb.sns.entity.CustomerFriendId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by gonglei on 16/3/15.
 */
public interface CustomerFriendDao extends JpaRepository<CustomerFriend, CustomerFriendId>, JpaSpecificationExecutor<CustomerFriend> {
}
