/**
 *
 */
package com.yunxin.cb.rb.dao;


import com.yunxin.cb.rb.entity.FundsPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 
 * @author lxc
 */
public interface FundsPoolDao extends JpaRepository<FundsPool, Integer>, JpaSpecificationExecutor<FundsPool> {

}
