/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * @author z001075
 */
public interface CustomerDao extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer>, CustomerPlusDao, BaseDao<Customer> {

    @Query("select c from Customer c left join fetch c.rank where (c.accountName=?1 or c.mobile=?1) and c.enabled=?2")
    public Customer findByAccountNameAndEnabled(String accountName, boolean enabled);

    @Query("select c from Customer c where c.accountName=?1 and c.mobile=?2 and c.enabled=?3")
    public Customer findByAccountNameAndMobileAndEnabled(String accountName, String mobile, boolean enabled);

    @Query("select c from Customer c where c.mobile=?1 and c.enabled=?2")
    public Customer findByMobileAndEnabled(String mobile, boolean enabled);
    @Query("select c from Customer c left join fetch c.recommendCustomer where c.customerId=?1")
    public Customer findRecommendCustomer(int customerId);

    @Query("select c from Customer c where c.mobile=:invitationCode or c.invitationCode=:invitationCode")
    public Customer findByMobileOrInvitationCode(@Param("invitationCode") String invitationCode);
    @Query("select c from Customer c where c.levelCode=?1")
    public Customer findByLevelCode(String levelCode);

    @Query("select c from Customer c where c.email=?1 and c.enabled=?2")
    public Customer findByEmailAndEnabled(String email, boolean enabled);

    public Customer findByQqOpenId(String qqOpenId);

    Customer findTopByAccountName(String accountName);

    @Query("select c from Customer c where c.accountName=?1 and c.customerId<>?2")
    public Customer findByAccountNameAndCustomerIdNot(String accountName, int customerId);

    @Query("select c from Customer c left join fetch c.rank where c.customerId=?1")
    public Customer findByCustomerId(int customerId);

    public Customer findByEmail(String email);

    @Query("update Customer c set c.password=?2 where c.email=?1")
    @Modifying
    public void updatePwdById(String email, String pwds);

    @Query("update Customer c set c.enabled=?1 where c.email=?2")
    @Modifying
    public void enableCustomerById(boolean enabled, int customerId);

    @Query("update Customer c set c.integral=?1 where c.customerId=?2")
    @Modifying
    public void updateIntegralById(int integral, int customerId);

    @Query("select c from Customer c left join fetch c.rank where c.accountName=?1 ")
    public Customer findByAccountName(String accountName);
    @Query("select c from Customer c where c.accountName=?1")
    public Customer getAccountName(String accountName);

    @Query("select count(c.customerId) from Customer c left join  c.rank r where r.rankName=?1 ")
    public Long findCustomerNumberByRankName(String s);

    public List<Customer> findByRank_RankId(int rankId);


    @Query("select c.customerId from Customer c where c.rank.rankId=?1")
    List<Integer> findCustomerIdsByRandIds(List<Integer> Ids);

    long countByCreateTimeBetween(Date startDate, Date endDate);


    @Query("select c from Customer c left join fetch c.recommendCustomer where c.levelCode like ?1")
    public List<Customer> findCustomerByLikeLevelCode(String levelCode);

    long countByQqOpenId(String qqOpenId);

    List<Customer> findByRecommendCustomer_CustomerIdAndPraise(int customerId, boolean paraise);
}

interface CustomerPlusDao {

}
