/**
 *
 */
package com.yunxin.cb.rb.dao;


import com.yunxin.cb.rb.entity.FundsPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Description:    资金池Dao类
 * @author: lxc
 * @Return :
 * @DateTime: 2018/8/7 11:04
 */
public interface FundsPoolDao extends JpaRepository<FundsPool, Integer>, JpaSpecificationExecutor<FundsPool> {

}
