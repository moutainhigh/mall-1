package com.yunxin.cb.sns.service.imp;

import com.yunxin.cb.mall.dao.CustomerDao;
import com.yunxin.cb.sns.dao.CommentDao;
import com.yunxin.cb.sns.dao.CustomerFriendDao;
import com.yunxin.cb.sns.dao.ShareDao;
import com.yunxin.cb.sns.service.IShareService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by gonglei on 16/3/15.
 */
@Service
@Transactional
public class ShareService implements IShareService {

    @Resource
    private CommentDao commentDao;

    @Resource
    private CustomerFriendDao customerFriendDao;

    @Resource
    private CustomerDao customerDao;

    @Resource
    private ShareDao shareDao;
}
