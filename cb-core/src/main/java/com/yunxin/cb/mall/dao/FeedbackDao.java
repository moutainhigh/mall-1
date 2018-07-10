/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.Favorite;
import com.yunxin.cb.mall.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author dengchenggang
 */
public interface FeedbackDao extends JpaRepository<Feedback, Integer>, JpaSpecificationExecutor<Feedback> {


}
