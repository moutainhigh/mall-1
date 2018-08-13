/**
 *
 */
package com.yunxin.cb.rb.service;

import com.yunxin.cb.rb.entity.FundsPool;
import com.yunxin.cb.rb.entity.FundsPoolLog;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:    资金池接口服务类
 * @author: lxc
 * @DateTime: 2018/8/7 10:58
 */

public interface IFundsPoolService {

    /**
     * @Description:        获得所有资金池
     * @author: lxc
     * @param
     * @Return java.util.List<com.yunxin.cb.rb.entity.FundsPool>:
     * @DateTime: 2018/8/8 10:13
     */
    List<FundsPool> getFundsPoolList();

    /**
     * @Description:    获取FundsPool的分页信息
     * @author: lxc
     * @param queryRequest
     * @Return org.springframework.data.domain.Page<com.yunxin.cb.rb.entity.FundsPool>:
     * @DateTime: 2018/8/7 17:52
     */
    Page<FundsPool> pageFundsPool(final PageSpecification<FundsPool> queryRequest);


    /**
     * @Description:        根据id查找资金池对象
     * @author: lxc
     * @param poolId        资金池id
     * @Return com.yunxin.cb.rb.entity.FundsPool:
     * @DateTime: 2018/8/7 20:25
     */
    FundsPool getFundsPoolByid(int poolId);


    /**
     * @Description:    获取FundsPoolLog的分页信息
     * @author: lxc
     * @param queryRequest
     * @Return org.springframework.data.domain.Page<com.yunxin.cb.rb.entity.FundsPoolLog>:
     * @DateTime: 2018/8/7 17:52
     */
    Page<FundsPoolLog> pageFundsPoolLog(final PageSpecification<FundsPoolLog> queryRequest);

    /**
     * @Description:        根据id查找资金池明细对象
     * @author: lxc
     * @param poolLogId        资金池操作日志id
     * @Return com.yunxin.cb.rb.entity.FundsPool:
     * @DateTime: 2018/8/7 20:25
     */
    FundsPoolLog getFundsPoolLogByid(int poolLogId);

    /**
     * @Description:        根据id查找资金池明细全对象(资金池对象,一级分类对象,货品对象)
     * @author: lxc
     * @param poolLogId        资金池操作日志id
     * @Return com.yunxin.cb.rb.entity.FundsPool:
     * @DateTime: 2018/8/7 20:25
     */
    FundsPoolLog getFullFundsPoolLogByid(int poolLogId);

    /**
     * @Description:            更新资金池funds金额时,同时需要向FundsPoolLog表插一条数据
     * @author: lxc
     * @param fundsPool         资金池对象
     * @param amount            操作金额
     * @param type              类型：1.累计，2.报账
     * @param fundsPoolLogs
     * @Return boolean:
     * @DateTime: 2018/8/8 19:54
     */
    boolean updateFundsAndSaveFundsPoolLog(FundsPool fundsPool,BigDecimal amount,int type,List<FundsPoolLog> fundsPoolLogs);

    /**
     * @Description:                根据报账id号查询报账明细并且更新资金池(后台审核报账通过使用，对资金池作减操作)
     * @author: lxc
     * @param transactionId         报账id
     * @Return boolean:
     * @DateTime: 2018/8/11 11:32
     */
    boolean updateAndCountReimbursementAmout(int transactionId );

    /**
     * @Description:               根据订单号查询订单明细并且更新资金池(后台审核订单通过使用，对资金池作加操作)
     * @author: lxc
     * @param transactionId        订单号
     * @Return boolean:
     * @DateTime: 2018/8/10 18:38
     */
    boolean updateAndCountOrderAmout(int transactionId );
}
