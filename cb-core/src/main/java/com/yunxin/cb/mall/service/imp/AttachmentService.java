/**
 *
 */
package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.AttachmentDao;
import com.yunxin.cb.mall.dao.FeedbackDao;
import com.yunxin.cb.mall.entity.Attachment;
import com.yunxin.cb.mall.entity.Feedback;
import com.yunxin.cb.mall.entity.Feedback_;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.service.IAttachmentService;
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
public class AttachmentService implements IAttachmentService {

    private static final Logger logger = LoggerFactory.getLogger(AttachmentService.class);

    @Resource
    private AttachmentDao attachmentDao;

    /**
     * 根据ObjectType与ObjectId 获取所有文件信息
     * @author      likang
     * @param objectType
    * @param objectId
     * @return      java.util.List<com.yunxin.cb.mall.entity.Attachment>
     * @exception
     * @date        2018/7/24 10:55
     */
    @Override
    public List<Attachment> findAttachmentByObjectTypeAndObjectId(ObjectType objectType, int objectId){
       return  attachmentDao.findAttachmentByObjectTypeAndObjectId(objectType,objectId);
    }

    /**
     * 添加Attachment
     * @author      likang
     * @return      com.yunxin.cb.mall.entity.Attachment
     * @exception
     * @date        2018/7/24 10:56
     */
    @Override
    public Attachment addAttachment(ObjectType objectType,int objectId,String filePath){
        Attachment attachment=new Attachment();
        attachment.setObjectId(objectId);
        attachment.setCreateTime(new Date());
        attachment.setFilePath(filePath.split(",")[0]);
        attachment.setFileName(filePath.split(",")[1]);
        attachment.setInputId(filePath.split(",")[2]);
        attachment.setObjectType(objectType);
        attachment.setStaffId(0);
        attachmentDao.save(attachment);
        return attachment;
    }

    /**
     * 删除Attachment
     * @author      likang
     * @param objectType
    * @param objectId
     * @return      void
     * @exception
     * @date        2018/7/24 16:34
     */
    @Override
    public void deleteAttachment(ObjectType objectType, int objectId){
        attachmentDao.deleteByObjectTypeAndObjectId(objectType,objectId);
    }

}
