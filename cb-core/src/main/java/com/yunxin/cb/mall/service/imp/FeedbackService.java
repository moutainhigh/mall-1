/**
 *
 */
package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.FeedbackDao;
import com.yunxin.cb.mall.entity.Feedback;
import com.yunxin.cb.mall.entity.Feedback_;
import com.yunxin.cb.mall.service.IFeedbackService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public Feedback addFeedback(Feedback feedback)  {
        return feedbackDao.save(feedback);
    }

    /**
     * 获取feeback的分页信息
     * @param queryRequest
     * @return
     */
    public Page<Feedback> pageFeedback(final PageSpecification<Feedback> queryRequest){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        List<PageSpecification.FilterDescriptor> list=queryRequest.getFilter().getFilters();
        for (PageSpecification.FilterDescriptor filterDescriptor:list
                ) {
            if("createTime".equals(filterDescriptor.getField())){
                Date createTime= null;
                SimpleDateFormat simpleDateFormats=new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date dates=simpleDateFormats.parse(String.valueOf(filterDescriptor.getValue()));
                    String createTimes=simpleDateFormat.format(dates);
                    filterDescriptor.setValue(createTimes);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        queryRequest.setCustomSpecification(new CustomSpecification<Feedback>() {
            @Override
            public void buildFetch(Root<Feedback> root) {
                root.fetch(Feedback_.customer, JoinType.LEFT);
            }
            @Override
            public void addConditions(Root<Feedback> root, CriteriaQuery<?> query,
                                      CriteriaBuilder builder, List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(Feedback_.createTime)));
            }
        });
        Page<Feedback> page = feedbackDao.findAll(queryRequest, queryRequest.getPageRequest());
        return page;
    }


    /**
     * 根据id获取feedback详情信息
     * @param id
     * @return
     */
    public Feedback getFeedbackByid(int id){
        Feedback feedback=feedbackDao.findFeedbackByid(id);
        return feedback;
    }


}
