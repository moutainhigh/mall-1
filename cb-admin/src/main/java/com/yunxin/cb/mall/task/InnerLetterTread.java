package com.yunxin.cb.mall.task;

import com.yunxin.core.util.LogicUtils;
import com.yunxin.cb.mall.entity.SystemLetter;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.mall.service.ILetterService;
import com.yunxin.cb.mall.service.imp.LetterService;


/**
 * Created by q001381 on 2014/7/22.
 */
public class InnerLetterTread implements Runnable {

	private SystemLetter systemLetter;

	private LetterService innerLetterService;

	private ILetterService innerLetterPublishRecordService;

	private ICustomerService customerService;

	public InnerLetterTread(SystemLetter systemLetter) {
		super();
		this.systemLetter = systemLetter;
		getCustomerService();
		getInnerLetterPublishRecordService();
		getInnerLetterService();
	}

	@Override
	public void run() {

		if(LogicUtils.isNotNull(systemLetter)){

//			InnerLetterPublishRecord lr = new InnerLetterPublishRecord();
//			//选择全部客户
//			if (letter.getPublishType() == LetterType.ALL_CUSTOMER) {
//				List<Customer> customers = customerService.getAllCustomers();
//				lr.setLetter(letter);
//				for (Customer cus : customers) {
//					lr.setCustomer(cus);
////					try {
////						Thread.currentThread().sleep(1*60*1000);
////					} catch (InterruptedException e) {
////						e.printStackTrace();
////					}
//					innerLetterPublishRecordService.save(lr);
//				}
//
//			}else{
//				//选择指定客户
//				String[] ids = null;
//				int id = 0;
////				if (LogicUtils.isNotNullAndEmpty(letter.getCustomerIds())) {
////					ids = letter.getCustomerIds().split(",");
////					for (int i = 0; i < ids.length; i++) {
////						id = Integer.valueOf(ids[i]);
////						Customer member = customerService.getCustomerById(id);
////						lr.setLetter(letter);
////						lr.setCustomer(member);
////						innerLetterPublishRecordService.save(lr);
////					}
////				}
//			}
		}
	}

	public SystemLetter getSystemLetter() {
		return systemLetter;
	}

	public void setSystemLetter(SystemLetter systemLetter) {
		this.systemLetter = systemLetter;
	}

	public LetterService getInnerLetterService() {
		if(LogicUtils.isNull(innerLetterService)){
//			innerLetterService=(LetterService) SpringContextUtil.getBean("innerLetterService");
		}
		return innerLetterService;
	}

	public void setInnerLetterService(LetterService innerLetterService) {
		this.innerLetterService = innerLetterService;
	}

	public ILetterService getInnerLetterPublishRecordService() {
		if(LogicUtils.isNull(innerLetterPublishRecordService)){
//			innerLetterPublishRecordService = (ILetterService) SpringContextUtil.getBean("innerLetterPublishRecordService");
		}
		return innerLetterPublishRecordService;
	}

	public void setInnerLetterPublishRecordService(ILetterService innerLetterPublishRecordService) {
		this.innerLetterPublishRecordService = innerLetterPublishRecordService;
	}

	public ICustomerService getCustomerService() {
		if(LogicUtils.isNull(customerService)){
//			customerService = (ICustomerService) SpringContextUtil.getBean("customerService");
		}
		return customerService;
	}

	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}
}
