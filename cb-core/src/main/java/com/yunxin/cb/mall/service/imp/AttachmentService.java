/**
 *
 */
package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.AttachmentDao;
import com.yunxin.cb.mall.entity.Attachment;
import com.yunxin.cb.mall.entity.Attachment_;
import com.yunxin.cb.mall.entity.meta.AttachmentState;
import com.yunxin.cb.mall.entity.meta.FileType;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.service.IAttachmentService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    public Attachment addAttachmentPictures(ObjectType objectType,int objectId,String filePath){
        Attachment attachment=new Attachment();
        attachment.setObjectId(objectId);
        attachment.setCreateTime(new Date());
        //path格式：七牛url，fileName,保存的inputid（用于前端删除）
        attachment.setFilePath(filePath.split(",")[0]);
        attachment.setFileName(filePath.split(",")[1]);
        attachment.setInputId(filePath.split(",")[2]);
        attachment.setFileType(FileType.PICTURES);
        attachment.setState(AttachmentState.RUNNING);
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
    public void deleteAttachmentPictures(ObjectType objectType, int objectId){
        attachmentDao.deleteByObjectTypeAndObjectId(objectType,objectId,FileType.PICTURES);
    }

    /**
     * 根据objecttype和objectIds修改state
     * @author      likang
     * @param objectType
    * @param objectIds
    * @param fileType
    * @param attachmentState
     * @return      void
     * @exception
     * @date        2018/7/26 18:31
     */
    @Override
    public void updateStateByObjectTypeAndObjectId(ObjectType objectType, int objectId, AttachmentState attachmentState){
        attachmentDao.updateStateByObjectTypeAndObjectId(objectType,objectId,attachmentState);
    }

    /**
     * 分页信息
     * @author      likang
     * @param queryRequest
     * @return      org.springframework.data.domain.Page<com.yunxin.cb.mall.entity.Attachment>
     * @exception
     * @date        2018/7/26 18:36
     */
    @Override
    public Page<Attachment> pageAttachment(final PageSpecification<Attachment> queryRequest){
        queryRequest.setCustomSpecification(new CustomSpecification<Attachment>() {
            @Override
            public void buildFetch(Root<Attachment> root) {
            }
            @Override
            public void addConditions(Root<Attachment> root, CriteriaQuery<?> query,
                                      CriteriaBuilder builder, List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(Attachment_.createTime)));
            }
        });
        Page<Attachment> page = attachmentDao.findAll(queryRequest, queryRequest.getPageRequest());
        return page;
    }
}
