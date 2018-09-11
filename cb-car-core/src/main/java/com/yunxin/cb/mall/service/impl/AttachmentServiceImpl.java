package com.yunxin.cb.mall.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.yunxin.cb.mall.entity.meta.AttachmentState;
import com.yunxin.cb.mall.entity.meta.FileType;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.yunxin.cb.mall.common.PageFinder;
import com.yunxin.cb.mall.common.Query;
import com.yunxin.cb.mall.dao.AttachmentDao;
import com.yunxin.cb.mall.entity.Attachment;
import com.yunxin.cb.mall.service.AttachmentService;
import com.yunxin.cb.util.DateUtils;

/**
 * Attachment 服务实现类
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {

	private static final Log log = LogFactory.getLog(AttachmentServiceImpl.class);
	
	@Resource
	private AttachmentDao attachmentDao;
	

	/**
	 * 根据查询条件，查询并返回Attachment的列表
	 * @param q 查询条件
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Attachment> getAttachmentList(Query q) {
		List<Attachment> list = null;
		try {
			//直接调用Dao方法进行查询
			list = attachmentDao.queryAll(q);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<Attachment>(0) : list;
		return list; 
	}
	
	/**
	 * 根据查询条件，分页查询并返回Attachment的分页结果
	 * @param q 查询条件
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public PageFinder<Attachment> getAttachmentPagedList(Query q) {
		
		List<Attachment> list = null;
		long rowCount = 0L;
		
		try {
			//调用dao查询满足条件的分页数据
			list = attachmentDao.pageList(q);
			//调用dao统计满足条件的记录总数
			rowCount = attachmentDao.count(q);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<Attachment>(0) : list;
	
		//将分页数据和记录总数设置到分页结果对象中
		PageFinder<Attachment> page = new PageFinder<Attachment>(q.getPageNo(), q.getPageSize(),rowCount,list);
		
		return page;
	}	
	
	/**
	 * 根据主键，查询并返回一个Attachment对象
	 * @param id 主键id
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public Attachment getAttachment(Integer id) {
		Attachment obj = null;
		if (id == null || id <= 0) { //传入的主键无效时直接返回null
			log.info("param error : " + " id = " + id);
			return obj;
		}
		try {
			//调用dao，根据主键查询
			obj = attachmentDao.get(id); 
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return obj;
	}

	/**
	 * 根据主键数组，查询并返回一组Attachment对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Attachment> getAttachmentByIds(Integer[] ids) {
		List<Attachment> list = null;
		if (ids == null || ids.length == 0) {
			log.info("param error : " + " ids is null or empty array.");
		} else {
			try {
				//调用dao，根据主键数组查询
				list = attachmentDao.getByIds(ids);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<Attachment>(0) : list;

		return list;
	}	
	

	/**
	 * 删除图片
	 * @author      likang
	 * @param objectType
	* @param objectId
	 * @return      void
	 * @exception
	 * @date        2018/9/11 11:06
	 */
	@Override
	public void deleteAttachmentPictures(String objectType, int objectId){
		attachmentDao.deleteAttachmentPictures(objectType,objectId);
	}

	/**
	 * 新增一条Attachment记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @return
	 */
	@Transactional
	public int addAttachment(ObjectType objectType, int objectId, String filePath) {
		int count = 0;
		Attachment attachment=new Attachment();
		if (attachment != null) { //传入参数无效时直接返回失败结果
			try {
				attachment.setObjectId(objectId);
				//path格式：七牛url，fileName,保存的inputid（用于前端删除）
				attachment.setFilePath(filePath.split(",")[0]);
				attachment.setFileName(filePath.split(",")[1]);
				attachment.setInputId(filePath.split(",")[2]);
				attachment.setFileType(FileType.PICTURES.getName());
				attachment.setState(AttachmentState.RUNNING.getName());
				attachment.setObjectType(objectType.toString());
				attachment.setStaffId(0);
				//如果传入参数的主键为null，则调用生成主键的方法获取新的主键
				if (attachment.getAttachId() == null) {
					attachment.setAttachId(this.generatePK());
				}
				//设置创建时间和更新时间为当前时间
				Date now = DateUtils.getTimeNow();
				attachment.setCreateTime(now);
				//填充默认值
				this.fillDefaultValues(attachment);
				//调用Dao执行插入操作
				attachmentDao.add(attachment);
				if (attachment.getAttachId() != null) {
					count = 1;
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}	
		}
		
		return count;
	}			
	
	/**
	 * 根据主键，更新一条Attachment记录
	 * @param attachment 更新的Attachment数据，且其主键不能为空
	 * @return
	 */
	@Transactional
	public int updateAttachment(Attachment attachment) {
		int count = 0;
				
		if (attachment != null && attachment.getAttachId() != null) { //传入参数无效时直接返回失败结果
			try {

				
				//调用Dao执行更新操作，并判断更新语句执行结果
				count = attachmentDao.update(attachment);			
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		}
		
		return count;
	}	
	
	/**
	 * 生成主键，如果表主键自增，则本方法可直接返回null；如非自增主键，则本方法必须返回一个大于0的值。
	 * @return
	 */
	public Integer generatePK() {
		return null;
	}
	
	/**
	 * 为Attachment对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(Attachment obj) {
		if (obj != null) {
		}
	}

}
