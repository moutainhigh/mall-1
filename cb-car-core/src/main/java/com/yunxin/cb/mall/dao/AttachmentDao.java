package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.Attachment;
import org.apache.ibatis.annotations.Mapper;

/**
 * Attachment 数据库处理类
 */
@Mapper
public interface AttachmentDao extends BaseDao<Attachment,Integer> {

    public int deleteAttachmentPictures(String objectType, int objectId);

}
