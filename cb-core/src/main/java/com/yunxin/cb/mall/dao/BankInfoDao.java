/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.BankInfo;
import com.yunxin.cb.mall.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
* @Description:    银行卡Dao
* @Author:         likang
* @CreateDate:     2018/7/31 10:45
*/
public interface BankInfoDao extends JpaRepository<BankInfo, Integer>, JpaSpecificationExecutor<BankInfo> {

}
