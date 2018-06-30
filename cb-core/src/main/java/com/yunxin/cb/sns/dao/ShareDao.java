package com.yunxin.cb.sns.dao;

import com.yunxin.cb.sns.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by gonglei on 16/3/15.
 */
public interface ShareDao extends JpaRepository<Share, Integer>, JpaSpecificationExecutor<Share> {
}
