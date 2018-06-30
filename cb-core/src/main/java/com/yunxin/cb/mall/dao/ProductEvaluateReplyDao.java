/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.ProductEvaluateReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author j000101
 */
public interface ProductEvaluateReplyDao extends JpaRepository<ProductEvaluateReply, Integer>, JpaSpecificationExecutor<ProductEvaluateReply> {


}
