/**
 *
 */
package com.yunxin.cb.rb.dao;


import com.yunxin.cb.rb.entity.FundsPoolLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description:    资金池明细Dao类
 * @author: lxc
 * @Return :
 * @DateTime: 2018/8/7 11:04
 */
public interface FundsPoolLogDao extends JpaRepository<FundsPoolLog, Integer>, JpaSpecificationExecutor<FundsPoolLog> {


    @Query("select f from FundsPoolLog f left join fetch f.fundsPool fp left join fetch f.catalog left join fetch f.product p left join fetch p.commodity where f.poolLogId=?1")
    public FundsPoolLog findFundsPoolLogDetail(int fundsPoolLogId);
}
