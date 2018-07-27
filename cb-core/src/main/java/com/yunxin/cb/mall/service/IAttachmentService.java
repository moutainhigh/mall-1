/**
 *
 */
package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Attachment;
import com.yunxin.cb.mall.entity.meta.AttachmentState;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author dengchenggang
 */
public interface IAttachmentService {

    public List<Attachment> findAttachmentByObjectTypeAndObjectId(ObjectType objectType, int objectId);


    public Attachment addAttachmentPictures(ObjectType objectType,int objectId,String filePath);


    public void deleteAttachmentPictures(ObjectType objectType, int objectId);

    public void updateStateByObjectTypeAndObjectId(ObjectType objectType, int objectId, AttachmentState attachmentState);

    public Page<Attachment> pageAttachment(final PageSpecification<Attachment> queryRequest);
}
