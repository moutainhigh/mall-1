/**
 *
 */
package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Feedback;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

/**
 * @author dengchenggang
 */
public interface IFeedbackService {

    public Feedback addFeedback(Feedback Feedback);

    public Page<Feedback> pageFeedback(final PageSpecification<Feedback> queryRequest);

    public Feedback getFeedbackByid(int id);
}
