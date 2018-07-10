/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.CouponState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author dengchenggang
 */
public interface FeedbackDao extends JpaRepository<Feedback, Integer>, JpaSpecificationExecutor<Feedback> {


    @Query("select f from Feedback f left join fetch f.customer where f.id=?1")
    Feedback findFeedbackByid(int id);

}
