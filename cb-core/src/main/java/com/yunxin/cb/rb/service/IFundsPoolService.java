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
     * @param amount            操作金额    (如果无商品金额，则转null)
     * @param version           版本号
     * @param poolId            资金池id
     * @param productId         货品id
     * @param transactionId     交易ID,累计为订单号，报帐为报帐ID
     * @param type              类型：1.累计，2.报账
     * @Return boolean:
     * @DateTime: 2018/8/8 19:54
     */
    boolean updateFundsAndSaveFundsPoolLog(BigDecimal amount, int version, int poolId,int productId,int transactionId,int type);
}
