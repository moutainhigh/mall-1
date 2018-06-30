package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.CustomerDao;
import com.yunxin.cb.mall.dao.RankDao;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.Integral;
import com.yunxin.cb.mall.entity.Rank;
import com.yunxin.cb.mall.entity.RuleCondition;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.mall.service.IIntegralService;
import com.yunxin.cb.mall.service.IRankService;
import com.yunxin.cb.mall.service.IRuleConditionService;
import com.yunxin.cb.util.CalculateHelper;
import com.yunxin.core.util.LogicUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class RankService implements IRankService {

    @Resource
    private ICustomerService customerService;

    @Resource
    private IIntegralService integralService;

    @Resource
    private IRuleConditionService ruleConditionService;

    @Resource
    private RankDao rankDao;

    @Resource
    private CustomerDao customerDao;

    @Override
    public Rank addRank(Rank rank) {
        if (rank.isDefaultRank()) {
            Rank dbRank = rankDao.findByDefaultRank(true);
            dbRank.setDefaultRank(false);
        }
        return rankDao.save(rank);
    }

    @Override
    public Rank updateRank(Rank rank) {
        Rank dbRank = rankDao.findOne(rank.getRankId());
        dbRank.setRankName(rank.getRankName());
        dbRank.setIntegral(rank.getIntegral());
        dbRank.setRemark(rank.getRemark());
        setDefaulRankByRankId(rank.getRankId());
        return dbRank;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Rank getRankById(int rankId) {
        return rankDao.findOne(rankId);
    }

    @Override
    public void removeRankById(int rankId) {
        rankDao.delete(rankId);
    }

    @Override
    public void updateRankAndIntegral(int customerId, double calculateValue) {
        /**
         * 找到当前用户，更新用户积分，根据当前积分，更新用户等级
         * integralRatio 为数据库中规则表中的规则编码
         */
        Customer customer = customerDao.findOne(customerId);
        RuleCondition ruleCondition = ruleConditionService.getRuleConditionByCode("INTEGRAL_RATIO");
        double fresult = CalculateHelper.calculate(calculateValue, Double.valueOf(ruleCondition.getRuleValue()), ruleCondition.getOperator());
        int getIntegral = (int) Math.round(fresult); // 购物获得可用积分
        int curIntegral = customer.getIntegral(); // 获取原始可用积分
        int finalIntegral = getIntegral + curIntegral; // 最终可用积分
        customer.setIntegral(finalIntegral);

        int curTotalIntegral = customer.getTotalIntegral(); // 获取历史总积分
        int finalTotalIntegral = getIntegral + curTotalIntegral; // 最终历史总积分
        customer.setTotalIntegral(finalTotalIntegral);
        /**
         * 获取当前用户等级之上的所有等级集合 遍历，根据积分升级用户等级
         */
        List<Rank> ranks = rankDao.findAllUpperRanks(customer.getRank().getIntegral());
        for (Rank item : ranks) {
            if (finalTotalIntegral < item.getIntegral()) {
                break;
            } else {
                customer.setRank(item);
                customerService.updateCustomerRank(customer);
            }
        }
        //更新客户积分
        customerService.updateCustomerIntegral(customer);
        Integral integral = new Integral();
        integral.setCustomer(customer);
        integral.setScore(getIntegral);
        integral.setOrigin(ruleCondition.getRuleName());
        integral.setRuleCondition(ruleCondition);
        integralService.addIntegral(integral);
    }

    @Override
    public void setDefaulRankByRankId(int rankId) {
        Rank rank = rankDao.findByDefaultRank(true);
        if (LogicUtils.isNotNull(rank)) {
            // 先将已存在的默认等級设为非默认然后在设置某条记录为默认
            rank.setDefaultRank(false);
            rankDao.setDefaultRankByRankId(rankId, true);
        } else {
            rankDao.setDefaultRankByRankId(rankId, true);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Rank> getAllRanks() {
        return rankDao.findAll(new Sort(Direction.ASC, "integral"));
    }


}
