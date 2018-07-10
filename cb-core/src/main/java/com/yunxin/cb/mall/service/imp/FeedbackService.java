/**
 *
 */
package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.BrandDao;
import com.yunxin.cb.mall.dao.FeedbackDao;
import com.yunxin.cb.mall.entity.Brand;
import com.yunxin.cb.mall.entity.Brand_;
import com.yunxin.cb.mall.entity.Feedback;
import com.yunxin.cb.mall.service.IBrandService;
import com.yunxin.cb.mall.service.IFeedbackService;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 * @author gonglei
 */
@Service
@Transactional
public class FeedbackService implements IFeedbackService {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackService.class);

    @Resource
    private FeedbackDao feedbackDao;

    public Feedback addFeedback(Feedback Feedback)  {
        Feedback.setCreateTime(new Date());
        return feedbackDao.save(Feedback);
    }



}
