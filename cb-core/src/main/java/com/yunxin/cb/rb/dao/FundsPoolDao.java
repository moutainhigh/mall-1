/**
 *
 */
package com.yunxin.cb.rb.dao;


import com.yunxin.cb.rb.entity.FundsPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

/**
 * @Description:    资金池Dao类
 * @author: lxc
 * @Return :
 * @DateTime: 2018/8/7 11:04
 */
public interface FundsPoolDao extends JpaRepository<FundsPool, Integer>, JpaSpecificationExecutor<FundsPool> {

    /**
     * @Description:    根据一级分类id查找
     * @author: lxc
     * @param catalogId     一级分类id
     * @Return com.yunxin.cb.rb.entity.FundsPool:
     * @DateTime: 2018/8/7 16:04
     */
    FundsPool findByCatalog_CatalogId(Integer catalogId);


    /**
     * @Description:        方法不能单独使用,更新资金池时,同时需要插入一条资金池数据.  可调用方法名:com.yunxin.cb.rb.service.IFundsPoolService.updateFundsAndSaveFundsPoolLog
     * @author: lxc
     * @param amount            操作金额
     * @param version           版本号
     * @param poolId            资金池id
     * @Return int:
     * @DateTime: 2018/8/8 20:11
     */
    @Modifying
    @Query("update FundsPool o set o.funds = o.funds + (?1),o.version = o.version + 1 where o.version = ?2 and o.poolId = ?3")
    int updateFundsByIdAndAndVersion(BigDecimal amount,int version,int poolId);
}
