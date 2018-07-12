package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.im.RongCloudService;
import com.yunxin.cb.mall.dao.CustomerDao;
import com.yunxin.cb.mall.dao.FridgeDao;
import com.yunxin.cb.mall.dao.RankDao;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.sns.dao.CustomerFriendDao;
import com.yunxin.cb.sns.entity.CustomerFriend;
import com.yunxin.cb.sns.entity.CustomerFriendId;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.CommonUtils;
import com.yunxin.core.util.LogicUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerService implements ICustomerService {

    @Resource
    private CustomerDao customerDao;

    @Resource
    private RankDao rankDao;

    @Resource
    private FridgeDao fridgeDao;

    @Resource
    private RongCloudService rongCloudService;

    @Resource
    private CustomerFriendDao customerFriendDao;

    @Override
    public Fridge addFridge(Fridge fridge) {
        fridge.setCreateTime(new Date());
        return fridgeDao.save(fridge);
    }

    @Override
    public Fridge updateFridge(Fridge fridge) {
        Fridge fridgeDB = fridgeDao.findOne(fridge.getFridgeId());
        AttributeReplication.copying(fridge, fridgeDB, Fridge_.articleName, Fridge_.articleType,
                Fridge_.shelfLife, Fridge_.unit, Fridge_.weight, Fridge_.remark);
        return fridgeDB;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Fridge getFridgeById(int fridgeId) {
        return fridgeDao.findOne(fridgeId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Fridge> pageFridges(final PageSpecification<Fridge> query) {
        query.setCustomSpecification(new CustomSpecification<Fridge>() {
            @Override
            public void addConditions(Root<Fridge> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(Fridge_.createTime)));
            }
        });
        Page<Fridge> pages = fridgeDao.findAll(query, query.getPageRequest());
        return pages;
    }

    @Override
    public void removeFridgeById(int fridgeId) {
        fridgeDao.delete(fridgeId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Fridge> getFridgesByCustomerId(int customerId) {
        return fridgeDao.findByCustomer_CustomerIdOrderByCreateTimeDesc(customerId);
    }

    @Override
    public Customer addCustomer(Customer customer) throws Exception {
        if (!customerDao.isUnique(customer, Customer_.accountName)) {
            throw new EntityExistException("客户账户名已存在");
        }
        if (StringUtils.isBlank(customer.getPassword())) {
            // 初始密码
            customer.setPassword(CommonUtils.randomString(6, CommonUtils.RANDRULE.RAND_IGNORE));
        }
        customer.setCreateTime(new Date());
        customer.setRank(rankDao.getRankByDefaultRank());
        Customer dbCustomer = customerDao.save(customer);
        String token = rongCloudService.register(dbCustomer);
        dbCustomer.setRongCloudToken(token);
        return dbCustomer;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Customer> pageCustomers(final PageSpecification<Customer> query) {
        query.setCustomSpecification(new CustomSpecification<Customer>() {
            @Override
            public void addConditions(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(Customer_.createTime)));
            }
        });
        Page<Customer> pages = customerDao.findAll(query, query.getPageRequest());
        return pages;
    }

    @Override
    public Customer updatePassword(int customerId, String password) {
        Customer customer = customerDao.findOne(customerId);
        customer.setPassword(password);
        return customer;
    }


    @Override
    public Customer updateMobile(int customerId, String mobile) {
        Customer customer = customerDao.findOne(customerId);
        customer.setMobile(mobile);
        return customer;
    }


    @Override
    public Customer updateAvatar(int customerId, String avatar) throws Exception{
        Customer customer = customerDao.findOne(customerId);
        customer.setAvatarUrl(avatar);
        rongCloudService.update(customer);
        return customer;
    }

    @Override
    public Customer updateNickName(int customerId, String nickName) throws Exception {
        Customer customer = customerDao.findOne(customerId);
        customer.setNickName(nickName);
        rongCloudService.update(customer);
        return customer;
    }

    @Override
    public Customer updateSex(int customerId, boolean sex) {
        Customer customer = customerDao.findOne(customerId);
        customer.setSex(sex);
        return customer;
    }

    @Override
    public Customer updateAddress(int customerId, String province,String city,String district,String address) {
        Customer customer = customerDao.findOne(customerId);
        customer.setProvince(province);
        customer.setCity(city);
        customer.setDistrict(district);
        customer.setAddress(address);
        return customer;
    }


    @Override
    public Customer updateMobileChecked(int customerId, boolean mobileChecked) {
        Customer customer = customerDao.findOne(customerId);
        customer.setMobileChecked(mobileChecked);
        return customer;
    }

    @Override
    public Customer updateEmail(int customerId, String email) {
        Customer customer = customerDao.findOne(customerId);
        customer.setEmail(email);
        return customer;
    }

    @Override
    public Customer updateEmailChecked(int customerId, boolean mailChecked) {
        Customer customer = customerDao.findOne(customerId);
        customer.setEmailChecked(mailChecked);
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) throws EntityExistException {
        if (!customerDao.isUnique(customer, Customer_.accountName)) {
            throw new EntityExistException("客户账户名已存在");
        }
        Customer customerDB = customerDao.findOne(customer
                .getCustomerId());
        AttributeReplication.copying(customer, customerDB, Customer_.realName, Customer_.sex);
        return customerDB;
    }

    @Override
    public Customer updateCustomerRank(Customer customer) {
        Customer customerDB = customerDao.findOne(customer
                .getCustomerId());
        AttributeReplication.copying(customer, customerDB, Customer_.rank);
        return customerDB;
    }

    @Override
    public Customer updateCustomerIntegral(Customer customer) {
        Customer customerDB = customerDao.findOne(customer
                .getCustomerId());
        AttributeReplication.copying(customer, customerDB, Customer_.integral, Customer_.totalIntegral);
        return customerDB;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Customer getCustomerById(int customerId) {
        return customerDao.findOne(customerId);
    }

    @Override
    public void removeCustomerById(int customerId) {
        customerDao.delete(customerId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        return customerDao.findAll(new Sort(Direction.ASC, "accountName"));
    }

    @Override
    public void resetCustomerPwd(int customerId) {
        Customer oldCustomer = customerDao.findOne(customerId);
        oldCustomer.setPassword("123456");
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findCustomerByName(String name) {
        return customerDao.findTopByAccountName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getCustomerByAccountNameAndPassword(String accountName, String password) {
        return customerDao.findByAccountNameAndPasswordAndEnabled(accountName, password, true);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Customer getCustomerByAccountNameAndMobile(String accountName, String mobile) {
        return customerDao.findByAccountNameAndMobileAndEnabled(accountName, mobile, true);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Customer getCustomerByMobile(String mobile) {
        return customerDao.findByMobileAndEnabled(mobile, true);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Customer getCustomerByEmail(String email) {
        return customerDao.findByEmailAndEnabled(email, true);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Customer getCustomerByQqOpenId(String qqOpenId) {
        return customerDao.findByQqOpenId(qqOpenId);
    }

    @Override
    public Customer updateCustomerForPre(Customer customer) {
        Customer oldCustomer = customerDao.findByCustomerId(customer
                .getCustomerId());
        oldCustomer.setRealName(customer.getRealName());
        oldCustomer.setAddress(customer.getAddress());
        oldCustomer.setProvince(customer.getProvince());
        oldCustomer.setCity(customer.getCity());
        oldCustomer.setDistrict(customer.getDistrict());
//        oldCustomer.setRegionCode(member.getRegionCode());
        oldCustomer.setMobile(customer.getMobile());
        oldCustomer.setEmail(customer.getEmail());
        return oldCustomer;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean findByEmail(String email) {
        if (LogicUtils.isNotNullAndEmpty(email)) {
            Customer customer = customerDao.findByEmail(email);
            if (LogicUtils.isNotNull(customer)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Customer getByEmail(String email) {
        if (LogicUtils.isNotNullAndEmpty(email)) {
            Customer customer = customerDao.findByEmail(email);
            return customer;
        } else {
            return null;
        }
    }


    @Override
    public void updatePwdById(String email, String pwds) {
        customerDao.updatePwdById(email, pwds);
    }

    @Override
    public Customer findByAccountName(String accountName) {
        return customerDao.findByAccountName(accountName);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Long> getCustomerNumberForRank() {
        List<Rank> ranks = rankDao.findAll(/*new Sort(Direction.ASC, "integral")*/);

        Map<String, Long> customerNum = new HashMap<String, Long>();
        if (ranks.size() > 4) {
            for (int i = 0; i < 4; i++) {
                String rname = ranks.get(i).getRankName();
                Long number = customerDao.findCustomerNumberByRankName(rname);
                customerNum.put(rname, number);
            }
            return customerNum;
        } else {
            for (Rank r : ranks) {
                String rname = r.getRankName();
                Long number = customerDao.findCustomerNumberByRankName(rname);
                customerNum.put(rname, number);
            }
            return customerNum;
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Customer> findByRankId(int rankId) {
        return customerDao.findByRank_RankId(rankId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Customer findByCustomerId(int customerId) {
        Customer customer = customerDao.findByCustomerId(customerId);
        return customer;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getCustomerCount() {
        return customerDao.count();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long countByCreateTimeBetween(Date startDate, Date endDate) {
        return customerDao.countByCreateTimeBetween(startDate, endDate);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long countByQqOpenId(String qqOpenId) {
        return customerDao.countByQqOpenId(qqOpenId);
    }

    public List<CustomerFriend> getFriendByCustomerId(int customerId) {
        List<CustomerFriend> customerFriendss = customerFriendDao.findCustomerFriendByCustomerCustomerId(customerId);
        return customerFriendss;
    }

    @Transactional
    public CustomerFriend addFriend(CustomerFriend customerFriend) {
        customerFriend = customerFriendDao.save(customerFriend);
        return customerFriend;
    }

    @Transactional
    public void delFriendById(CustomerFriendId customerFriendId) {
        customerFriendDao.delete(customerFriendId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isFriend(int customerId, int friendId) {
        return customerFriendDao.findOne(new CustomerFriendId(customerId, friendId)) != null;
    }

    @Transactional
    public CustomerFriend updateFriendsProfile(CustomerFriend customerFriend) {
        CustomerFriend renew= customerFriendDao.findOne(customerFriend.getId());

        renew.setPhone(customerFriend.getPhone());
        renew.setAliasName(customerFriend.getAliasName());
        renew.setDescription(customerFriend.getDescription());
        renew.setTag(customerFriend.getTag());
        renew.setImage(customerFriend.getImage());

        return renew;
    }

    /**
     * 用户点赞
     * @param customerId
     * @return
     */
    @Override
    @Transactional
    public Customer customerPraise(int customerId) {
        Customer customer = customerDao.findOne(customerId);
        customer.setPraise(true);
        Customer recommendCustomer = customer.getRecommendCustomer();
        recommendCustomer.setPraiseNum(recommendCustomer.getPraiseNum()+1);
        return customer;
    }

    /**
     * 查询点赞用户
     * @param customerId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Customer> getPraiseCustomers(int customerId) {
        return customerDao.findByRecommendCustomer_CustomerIdAndPraise(customerId, true);
    }
}
