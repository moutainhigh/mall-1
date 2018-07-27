package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Attachment;
import com.yunxin.cb.mall.entity.meta.AttachmentState;
import com.yunxin.cb.mall.entity.meta.FileType;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttachmentDao extends  JpaRepository<Attachment, Integer>, JpaSpecificationExecutor<Attachment> {


    @Query("select  a from Attachment a where a.objectType=?1 and a.objectId=?2")
    List<Attachment> findAttachmentByObjectTypeAndObjectId(ObjectType objectType, int objectId);

    @Modifying
    @Query("delete from Attachment a where a.objectType =?1 and a.objectId=?2 and a.fileType=?3")
    void deleteByObjectTypeAndObjectId(ObjectType objectType, int objectIds,FileType fileType);

    @Modifying
    @Query("update  Attachment a set a.state=?3  where a.objectType =?1 and a.objectId=?2")
    void updateStateByObjectTypeAndObjectId(ObjectType objectType, int objectId, AttachmentState attachmentState);

}
