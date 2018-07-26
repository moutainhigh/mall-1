/**
 *
 */
package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Attachment;
import com.yunxin.cb.mall.entity.meta.FileType;
import com.yunxin.cb.mall.entity.meta.ObjectType;

import java.util.List;

/**
 * @author dengchenggang
 */
public interface IAttachmentService {

    public List<Attachment> findAttachmentByObjectTypeAndObjectId(ObjectType objectType, int objectId);


    public Attachment addAttachmentPictures(ObjectType objectType,int objectId,String filePath);


    public void deleteAttachmentPictures(ObjectType objectType, int objectId);
}
