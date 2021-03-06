package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.im.RongCloudService;
import com.yunxin.cb.insurance.dao.InsuranceOrderDao;
import com.yunxin.cb.insurance.dao.InsuranceOrderLogDao;
import com.yunxin.cb.insurance.entity.InsuranceOrder;
import com.yunxin.cb.insurance.entity.InsuranceOrderLog;
import com.yunxin.cb.insurance.meta.GratitudeType;
import com.yunxin.cb.insurance.meta.InsuranceOrderState;
import com.yunxin.cb.mall.dao.*;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.CustomerType;
import com.yunxin.cb.mall.entity.meta.PolicyType;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.mall.service.IFinaciaWalletService;
import com.yunxin.cb.mall.vo.*;
import com.yunxin.cb.redis.RedisService;
import com.yunxin.cb.security.PBKDF2PasswordEncoder;
import com.yunxin.cb.sns.dao.CustomerFriendDao;
import com.yunxin.cb.sns.entity.CustomerFriend;
import com.yunxin.cb.sns.entity.CustomerFriendId;
import com.yunxin.cb.sns.meta.CustomerFriendRequestState;
import com.yunxin.cb.sns.meta.CustomerFriendState;
import com.yunxin.cb.sns.service.ICustomerFriendRequestService;
import com.yunxin.cb.system.entity.Profile;
import com.yunxin.cb.system.meta.ProfileName;
import com.yunxin.cb.system.service.IProfileService;
import com.yunxin.cb.util.DmSequenceFourUtils;
import com.yunxin.cb.util.DmSequenceSixUtils;
import com.yunxin.cb.util.PasswordHash;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.CommonUtils;
import com.yunxin.core.util.IdGenerate;
import com.yunxin.core.util.LogicUtils;
import io.rong.models.response.BlackListResult;
import io.rong.models.user.UserModel;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
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
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Executors;

@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerService implements ICustomerService {
    @Value("${application.default.avatarUrl}")
    private String avatarUrl;
    private static Logger logger = LoggerFactory.getLogger(CustomerService.class);
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

    @Resource
    private ICustomerFriendRequestService customerFriendRequestService;

    @Resource
    private IProfileService iProfileService;
    @Resource
    private InsuranceOrderDao insuranceOrderDao;
    @Resource
    private RedisService redisService;
    @Resource
    private InsuranceOrderLogDao insuranceOrderLogDao;

    @Resource
    private IFinaciaWalletService iFinaciaWalletService;

    @Resource
    private FavoriteDao favoriteDao;
    @Resource
    private HistoryRecordDao historyRecordDao;

    @Override
    public Fridge addFridge(Fridge fridge) {
        fridge.setCreateTime(new Date());
        return fridgeDao.save(fridge);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
        if(customer.getRecommendCustomer()==null){
            throw new EntityExistException("推荐人不存在");
        }
        Customer customerCode = generateCodeByRecommendCustomer(customer.getRecommendCustomer());
        if (customerCode != null) {
            customer.setLevelCode(customerCode.getLevelCode());
            customer.setCustomerLevel(customerCode.getCustomerLevel());
            customer.setInvitationCode(customerCode.getInvitationCode());
        }

        if (StringUtils.isBlank(customer.getPassword())) {
            // 初始密码
            customer.setPassword(CommonUtils.randomString(6, CommonUtils.RANDRULE.RAND_IGNORE));
        }
        customer.setCreateTime(new Date());
        customer.setRank(rankDao.getRankByDefaultRank());
        String pwd = PasswordHash.createHash(customer.getPassword());
        customer.setPassword(pwd);

        Customer dbCustomer = customerDao.save(customer);
        String token = rongCloudService.register(dbCustomer);
        dbCustomer.setRongCloudToken(token);
        //加入缓存
        Executors.newCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                redisService.setCustomerList(dbCustomer.getMobile(),dbCustomer);
            }
        });
        return dbCustomer;
    }

    @Override
    public Customer addAdminCustomer(Customer customer) throws Exception {
        if (!customerDao.isUnique(customer, Customer_.mobile)) {
            throw new EntityExistException("客户手机号已存在");
        }
        if (StringUtils.isBlank(customer.getPassword())) {
            // 初始密码
            customer.setPassword(CommonUtils.randomString(6, CommonUtils.RANDRULE.RAND_IGNORE));
        }
        customer.setAccountName(IdGenerate.generateRandomStr(10));
        customer.setCustomerType(CustomerType.PLATFORM_SELF);
        customer.setCreateTime(new Date());
        customer.setAvatarUrl(avatarUrl);
        customer.setEnabled(true);
        customer.setRank(rankDao.getRankByDefaultRank());
        customer.setCardPositiveImg(" ");
        customer.setCardNegativeImg(" ");
        customer.setBankCardImg(" ");
        customer.setNickName(customer.getMobile());//add by lxc 2018-08-06  默认手机号为昵称
        customer.setRecommendCustomer(new Customer() {
            {
                setCustomerId(1);
            }
        });
//        Customer customer1 = customerDao.getOne(1);//此方法会引起,org.hibernate.lazyinitializationexception错误,解决方法,用另外一个根据customerId查询的方法
        Customer customer1 = customerDao.findRecommendCustomer(1);//add by lxc  2018-08-05
        if (customer1 != null) {
            Customer customerCode = generateCodeByRecommendCustomer(customer1);
            if (customerCode != null) {
                customer.setLevelCode(customerCode.getLevelCode());
                customer.setCustomerLevel(customerCode.getCustomerLevel());
                customer.setInvitationCode(customerCode.getInvitationCode());
            }else{
                throw new EntityExistException("注册失败");
            }
            String pwd = PasswordHash.createHash(customer.getPassword());
            customer.setPassword(pwd);
            Customer dbCustomer = customerDao.save(customer);
            String token = rongCloudService.register(dbCustomer);
            dbCustomer.setRongCloudToken(token);
            return dbCustomer;
        }else{
            throw new EntityExistException("注册失败");
        }

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
    @Transactional(rollbackFor = Exception.class)
    public Customer updatePassword(int customerId, String password) {
        Customer customer = customerDao.findOne(customerId);
        try {
            customer.setPassword(PasswordHash.createHash(password));
        } catch (PasswordHash.CannotPerformOperationException e) {
            e.printStackTrace();
        }
        return customer;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Customer updateMobile(int customerId, String mobile) {
        Customer customer = customerDao.findOne(customerId);
        customer.setMobile(mobile);
        return customer;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Customer updateAvatar(int customerId, String avatar) throws Exception {
        Customer customer = customerDao.findOne(customerId);
        customer.setAvatarUrl(avatar);
        rongCloudService.update(customer);
        //加入缓存
        Executors.newCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                redisService.setCustomerList(customer.getMobile(),customer);
            }
        });
        return customer;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Customer updateNickName(int customerId, String nickName) throws Exception {
        Customer customer = customerDao.findOne(customerId);
        if (StringUtils.isNotBlank(nickName)) {
            customer.setNickName(nickName);
            rongCloudService.update(customer);
            //加入缓存
            Executors.newCachedThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    redisService.setCustomerList(customer.getMobile(),customer);
                }
            });
        }
        return customer;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Customer updateSex(int customerId, boolean sex) {
        Customer customer = customerDao.findOne(customerId);
        customer.setSex(sex);
        return customer;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Customer updateAddress(int customerId, String province, String city, String district, String address) {
        Customer customer = customerDao.findOne(customerId);
        customer.setProvince(province);
        customer.setCity(city);
        customer.setDistrict(district);
        customer.setAddress(address);
        return customer;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Customer updateMobileChecked(int customerId, boolean mobileChecked) {
        Customer customer = customerDao.findOne(customerId);
        customer.setMobileChecked(mobileChecked);
        return customer;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Customer updateEmail(int customerId, String email) {
        Customer customer = customerDao.findOne(customerId);
        customer.setEmail(email);
        return customer;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Customer updateEmailChecked(int customerId, boolean mailChecked) {
        Customer customer = customerDao.findOne(customerId);
        customer.setEmailChecked(mailChecked);
        return customer;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
    public Customer updateCustomerMsg(int customerId, CustomerUpdateVo customerUpdateVo) {
        Customer customerDB = customerDao.findOne(customerId);
        if (StringUtils.isNotBlank(customerUpdateVo.getBankCardImg()))
            customerDB.setBankCardImg(customerUpdateVo.getBankCardImg());
        if (StringUtils.isNotBlank(customerUpdateVo.getCardNegativeImg()))
            customerDB.setCardNegativeImg(customerUpdateVo.getCardNegativeImg());
        if (StringUtils.isNotBlank(customerUpdateVo.getCardPositiveImg()))
            customerDB.setCardPositiveImg(customerUpdateVo.getCardPositiveImg());
        if (StringUtils.isNotBlank(customerUpdateVo.getRealName()))
            customerDB.setRealName(customerUpdateVo.getRealName());
        if (StringUtils.isNotBlank(customerUpdateVo.getCardType()))
            customerDB.setCardType(customerUpdateVo.getCardType());
        if (StringUtils.isNotBlank(customerUpdateVo.getCustomerCountry()))
            customerDB.setCustomerCountry(customerUpdateVo.getCustomerCountry());
        if (customerUpdateVo.getCustomerCardPeroid() != null)
            customerDB.setCustomerCardPeroid(customerUpdateVo.getCustomerCardPeroid());
        if (StringUtils.isNotBlank(customerUpdateVo.getOccupationalCategory()))
            customerDB.setOccupationalCategory(customerUpdateVo.getOccupationalCategory());
        if (StringUtils.isNotBlank(customerUpdateVo.getSex())) {
            if ("true".equals(customerUpdateVo.getSex()))
                customerDB.setSex(true);
            else if ("false".equals(customerUpdateVo.getSex()))
                customerDB.setSex(false);
        }

        return customerDB;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Customer updateCustomerRank(Customer customer) {
        Customer customerDB = customerDao.findOne(customer
                .getCustomerId());
        AttributeReplication.copying(customer, customerDB, Customer_.rank);
        return customerDB;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Customer updateCustomerIntegral(Customer customer) {
        Customer customerDB = customerDao.findOne(customer
                .getCustomerId());
        AttributeReplication.copying(customer, customerDB, Customer_.integral, Customer_.totalIntegral);
        return customerDB;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Customer getCustomerById(int customerId) {
        return customerDao.findRecommendCustomer(customerId);
    }

    @Override
    public void removeCustomerById(int customerId) {
        customerDao.delete(customerId);
    }

    @Override
    public void resetLevelCodeCode() {
       List<Customer> list=customerDao.findByLevelCodeIsNulls();
        resetRepeatLevelCode(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetInvitationCode() {
        //更新为空的邀请码
        List<Customer> customer=customerDao.findByInvitationCodeIsNulls();
        for (Customer list:customer){
            Customer dbCustomer=customerDao.findRecommendCustomer(list.getCustomerId());
            try {
                String invitationCode=checkInvitationCode(DmSequenceSixUtils.getNoRepeatId());
                customerDao.updateInvitationCode(invitationCode,dbCustomer.getCustomerId());
            } catch (Exception e) {
                logger.error("resetInvitationCode failed",e);
            }
        }

        //更新重复编码
        List<Customer> customerRepeats=customerDao.findInvitationCodeByRepeat();
        resetRepeatInvitationCode(customerRepeats);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Customer>  resetRepeatInvitationCode(List<Customer> customerRepeat) {

        if(null!=customerRepeat&&customerRepeat.size()>0){

            for (Customer customerRepeats:customerRepeat){

                try {
                    String invitationCode=checkInvitationCode(DmSequenceSixUtils.getNoRepeatId());
                    customerDao.updateInvitationCode(invitationCode,customerRepeats.getCustomerId());
                } catch (Exception e) {
                    logger.error("resetInvitationCodeRepeat failed",e);
                }

            }
//            List<Customer> customerRepeats=customerDao.findInvitationCodeByRepeat();
//            if(null!=customerRepeats&&customerRepeats.size()>0){
//                return resetRepeatInvitationCode(customerRepeats);
//            }

        }

        return customerRepeat;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Customer>  resetRepeatLevelCode(List<Customer> customerRepeat) {

        if(null!=customerRepeat&&customerRepeat.size()>0){

            for (Customer customer:customerRepeat){

                if(customer.getRecommendCustomer()!=null){

                    Customer customerRecommendCustomer=customerDao.findRecommendCustomer(customer.getRecommendCustomer().getCustomerId());

                    if(StringUtils.isNotEmpty(customerRecommendCustomer.getLevelCode())){

                        try {
                            Customer customers= generateCodeByRecommendCustomer(customerRecommendCustomer);
                            if(customers!=null){
                                customerDao.updateLevelCode(customers.getLevelCode(),customers.getCustomerLevel(),customer.getCustomerId());
                            }

                        } catch (Exception e) {
                            logger.error("findByLevelCodeIsNull failed",e);
                        }
                    }

                }

            }

//            List<Customer> list=customerDao.findByLevelCodeIsNulls();
//            if(null!=list&&list.size()>0){
//                return resetRepeatLevelCode(list);
//            }
        }
        return customerRepeat;
    }


    @Override
    public Customer generateCode(String invitationCode) {
        logger.info("generateCode----------" + invitationCode);
        final int initialLevel = 1;

        return new Customer() {
            {
                try {
                    String generateCode = checkLevelCode(DmSequenceFourUtils.getNoRepeatId());
                    String invitationCodes = checkInvitationCode(DmSequenceSixUtils.getNoRepeatId());
                    if (StringUtils.isNotBlank(invitationCode)) {
                        Customer recommendCustomer = getCustomerByInvitationCode(invitationCode);
                        if (recommendCustomer != null) {
                            int customerLevel = recommendCustomer.getCustomerLevel();
                            String recommendLevelCode = recommendCustomer.getLevelCode();
                            setCustomerLevel(customerLevel + initialLevel);
                            logger.info("invitationCode----------" + invitationCode);
                            setLevelCode(checkGenerateCode(recommendLevelCode, generateCode));
                        } else {
                            setCustomerLevel(initialLevel);
                            setLevelCode(generateCode);
                        }
                    } else {
                        setCustomerLevel(initialLevel);
                        setLevelCode(generateCode);
                    }
                    setInvitationCode(invitationCodes);
                    logger.info("invitationCodes----------" + invitationCodes);
                } catch (Exception e) {
                    logger.error("生成编码异常", e);

                }
            }
        };
    }

    @Override
    @Transactional(readOnly = true)
    public Customer generateCodeByRecommendCustomer(Customer recommendCustomer)  throws  Exception{
        logger.info("recommendCustomer.invitationCode----------" + recommendCustomer.getInvitationCode());

        final int initialLevel = 1;
        return new Customer(){
            {
                //等级编码
                String generateCode = checkLevelCode(DmSequenceFourUtils.getNoRepeatId());
                //邀请码
                String invitationCodes = checkInvitationCode(DmSequenceSixUtils.getNoRepeatId());
                //推荐人等级
                int customerLevel = recommendCustomer.getCustomerLevel();
                //推荐人等级编码
                String recommendLevelCode = recommendCustomer.getLevelCode();
                //设置等级
                setCustomerLevel(customerLevel + initialLevel);
                //设置等级编码
                setLevelCode(checkGenerateCode(recommendLevelCode, generateCode));
                //设置邀请码
                setInvitationCode(invitationCodes);
            }
        };
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findCustomerByLevelCode(String levelCode) {
        final int indexSize = 4;
        List<String> levelCodes = new ArrayList<>();
        int level = levelCode.length()/indexSize;
        for(int i=0;i<level-1;i++){
            levelCodes.add(levelCode.substring(0, (i+1)*indexSize));
        }
        return customerDao.findByLevelCodeIn(levelCodes);
    }

    /**
     * 校验邀请码
     *
     * @param invitationCode
     * @return
     */
    public String checkInvitationCode(String invitationCode) {
        Customer recommendCustomer = getCustomerByInvitationCode(invitationCode);
        if (recommendCustomer != null) {
            try {
                return checkInvitationCode(DmSequenceSixUtils.getNoRepeatId());
            } catch (Exception e) {
                logger.error("生成编码异常", e);
            }
        }
        return invitationCode;
    }

    /**
     * 校验等级编码
     *
     * @param levelCode
     * @return
     */
    public String checkGenerateCode(String levelCode, String generateCode) {
        Customer recommendCustomer = getByLevelCode(levelCode + generateCode);
        if (recommendCustomer != null) {
            try {
                return checkGenerateCode(levelCode,DmSequenceFourUtils.getNoRepeatId());
            } catch (Exception e) {
                logger.error("生成编码异常", e);
            }
        }
        return levelCode + generateCode;
    }

    public String checkLevelCode(String generateCode) {
        Customer recommendCustomer = getByLevelCode(generateCode);
        if (recommendCustomer != null) {
            try {
                return checkLevelCode(DmSequenceFourUtils.getNoRepeatId());
            } catch (Exception e) {
                logger.error("生成编码异常", e);
            }
        }
        return generateCode;
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
        PBKDF2PasswordEncoder pbkdf2 = new PBKDF2PasswordEncoder();
        Customer customer = customerDao.findByAccountNameAndEnabled(accountName, true);

        if (customer != null) {
            if (pbkdf2.matches(password, customer.getPassword())) {
                if (StringUtils.isNotEmpty(customer.getRealName()) && StringUtils.isNotEmpty(customer.getCustomerCountry())
                        && StringUtils.isNotEmpty(customer.getCardType()) && StringUtils.isNotEmpty(customer.getCustomerCardNo())
                        && StringUtils.isNotEmpty(customer.getOccupationalCategory()) && null != customer.getCustomerCardPeroid())
                    customer.setPerfect(true);
                else
                    customer.setPerfect(false);
            } else {
                return null;
            }
        }
        return customer;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Customer getCustomerByAccountNameAndMobile(String accountName, String mobile) {
        return customerDao.findByAccountNameAndMobileAndEnabled(accountName, mobile, true);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Customer getCustomerByMobile(String mobile) {
        Customer customer = customerDao.findByMobileAndEnabled(mobile, true);
        if (customer != null) {
            if (StringUtils.isNotEmpty(customer.getRealName()) && StringUtils.isNotEmpty(customer.getCustomerCountry())
                    && StringUtils.isNotEmpty(customer.getCardType()) && StringUtils.isNotEmpty(customer.getCustomerCardNo())
                    && StringUtils.isNotEmpty(customer.getOccupationalCategory()) && null != customer.getCustomerCardPeroid())
                customer.setPerfect(true);
            else
                customer.setPerfect(false);
        }
        return customer;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Customer getCustomerByInvitationCode(String invitationCode) {
        return customerDao.findByMobileOrInvitationCode(invitationCode);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Customer getByLevelCode(String levelCode) {
        return customerDao.findByLevelCode(levelCode);
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
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
    public void updatePwdById(String email, String pwds) {
        customerDao.updatePwdById(email, pwds);
    }

    @Override
    public Customer findByAccountName(String accountName) {
        return customerDao.findByAccountName(accountName);
    }

    @Override
    public Customer getAccountName(String accountName) {

        return customerDao.getAccountName(accountName);
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
        List<CustomerFriend> customerFriendss = customerFriendDao.findCustomerFriendByCustomerCustomerIdAndState(customerId, CustomerFriendState.NORMAL);
        return customerFriendss;
    }

    @Transactional
    public CustomerFriend addFriend(CustomerFriend customerFriend) {
        customerFriend = customerFriendDao.save(customerFriend);
        return customerFriend;
    }

    @Transactional
    public void delFriendById(int customerId, int friendId) throws Exception {
        CustomerFriendId customerFriendId1 = new CustomerFriendId();
        customerFriendId1.setCustomerId(customerId);
        customerFriendId1.setFriendId(friendId);
        customerFriendDao.delete(customerFriendId1);
        CustomerFriendId customerFriendId2 = new CustomerFriendId();
        customerFriendId2.setCustomerId(friendId);
        customerFriendId2.setFriendId(customerId);
        customerFriendDao.delete(customerFriendId2);
        Customer customer = customerDao.findByCustomerId(customerId);
        Customer friendCustomer = customerDao.findByCustomerId(friendId);
        rongCloudService.sendPrivateMessage(customer.getAccountName(), friendCustomer.getAccountName(), "DeleteFriend");
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isFriend(int customerId, int friendId) {
        return customerFriendDao.findOne(new CustomerFriendId(customerId, friendId)) != null;
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerFriend getFriend(int customerId, int friendId) {
        return customerFriendDao.findOne(new CustomerFriendId(customerId, friendId));
    }


    @Transactional
    public CustomerFriend updateFriendsProfile(CustomerFriend customerFriend) {
        CustomerFriend renew = customerFriendDao.findOne(customerFriend.getId());

        renew.setPhone(customerFriend.getPhone());
        renew.setAliasName(customerFriend.getAliasName());
        renew.setDescription(customerFriend.getDescription());
        renew.setTag(customerFriend.getTag());
        renew.setImage(customerFriend.getImage());

        return renew;
    }

    /**
     * 用户点赞
     *
     * @param customerId
     * @return
     */
    @Override
    public boolean customerPraise(int customerId) {
        Customer customer = customerDao.findOne(customerId);
        /**
         * 查保单
         */
        List<InsuranceOrder> list = insuranceOrderDao.findOrderPriceByCustomerId(customer.getCustomerId());
        if (list == null || list.size() == 0)
            return false;
        //给推荐人增加一个点赞次数
        Customer recommendCustomer = customer.getRecommendCustomer();
        recommendCustomer.setPraiseNum(recommendCustomer.getPraiseNum() + 1);
        //TODO 实现推荐人以及所有上级增加5%的授信额度
        if (!customer.isPraise()) {
            List<Customer> listCustomer = findCustomerByLevelCode(customer.getLevelCode());
            Profile Profile = iProfileService.getProfileByProfileName(ProfileName.GIVE_THE_THUMBS_UP);
            BigDecimal ration;
            try {
                ration = new BigDecimal(Profile.getFileValue());
            } catch (Exception e) {
                ration = new BigDecimal(0.05);
            }
            BigDecimal price=new BigDecimal(list.get(0).getPrice());
            BigDecimal rationPrice=price.multiply(ration).setScale(4,BigDecimal.ROUND_DOWN);
            if (listCustomer != null && listCustomer.size() > 0) {
                for (Customer listCustome : listCustomer){
//                    iCustomerWalletService.updateCustomerWallet(listCustome.getCustomerId(), ration, "推荐人以及所有上级增加5%的授信额度", BusinessType.GIVE_THE_THUMBS_UP, list.get(0).getPrice());
                    FinacialWallet finacialWallet=iFinaciaWalletService.getFinacialWalletByCustomerId(listCustome.getCustomerId());
                    if(finacialWallet==null)
                        finacialWallet.setCustomer(listCustome);
                    else{
                        BigDecimal creditAmount=rationPrice.add(finacialWallet.getCreditAmount());
                        finacialWallet.setCreditAmount(creditAmount);
                    }

                    iFinaciaWalletService.addFinaciaWallet(finacialWallet,1,rationPrice);

                }


            }

        }
        customer.setPraise(true);
        return true;
    }

    /**
     * 查询点赞用户
     *
     * @param customerId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Customer> getPraiseCustomers(int customerId) {
        return customerDao.findByRecommendCustomer_CustomerIdAndPraise(customerId, true);
    }

    @Override
    public List<CustomerMatchVo> matchAddressBook(CustomerMatchsVo[] customerMatchsVo) {
            return new ArrayList<CustomerMatchVo>(){
                {
                    if(null!=customerMatchsVo&&customerMatchsVo.length>0){

                        List<CustomerMatchsVo> listVo=Arrays.asList(customerMatchsVo);
                        Map<String,Object> map=(Map<String, Object>) redisService.getCustomerList();
                        if(null!=map){
                            for(CustomerMatchsVo customerMatchVos:listVo){
                                if(null!=map.get(customerMatchVos.getMobile())){
                                    Customer customer=(Customer)map.get(customerMatchVos.getMobile());
                                    CustomerMatchVo customerMatchVo=new CustomerMatchVo();
                                    BeanUtils.copyProperties(customer,customerMatchVo);
                                    customerMatchVo.setRealName(customerMatchVos.getRealName());
                                    add(customerMatchVo);
                                }
                        }
                    }



                }
            }
        };
    }
    public Map<String,Object> getCustomer(){
        redisService.deleteKey("customers");
        Map<String,Object> map=new HashMap<>();
        if(null!=redisService.getKey("customers"))
            map= (Map<String,Object>)redisService.getKey("customers");
        else{
            List<Customer> list= customerDao.findAll();
            if(null!=list&&list.size()>0){
                Map<String,Object> mp=new HashMap<String,Object>(){
                    {
                        for (Customer customer:list) {
                            put("customer_"+customer.getMobile(),customer);
                        }
                    }
                };
                map.put("customers",mp);
//                        redisService.setKey("customers",mp);
            }

        }
        return map;

    }

    /**
     * 添加黑名单
     *
     * @param friendId
     * @param customerId
     * @throws Exception
     */
    @Transactional
    public void addBlacklist(int friendId, int customerId) throws Exception {
        CustomerFriendId customerFriendId = new CustomerFriendId();
        customerFriendId.setCustomerId(customerId);
        customerFriendId.setFriendId(friendId);
        CustomerFriend customerFriend = customerFriendDao.getOne(customerFriendId);
        Customer customer = customerFriend.getCustomer();
        Customer friend = customerFriend.getFriend();
        customerFriend.setState(CustomerFriendState.BLACKLIST);
        rongCloudService.addBlacklist(customer.getAccountName(), friend.getAccountName());
    }

    /**
     * 移除黑名单
     *
     * @param friendId
     * @param customerId
     * @throws Exception
     */
    @Transactional
    public void removeBlacklist(int friendId, int customerId) throws Exception {
        CustomerFriendId customerFriendId = new CustomerFriendId();
        customerFriendId.setCustomerId(customerId);
        customerFriendId.setFriendId(friendId);
        CustomerFriend customerFriend = customerFriendDao.getOne(customerFriendId);
        Customer customer = customerFriend.getCustomer();
        Customer friend = customerFriend.getFriend();
        customerFriend.setState(CustomerFriendState.NORMAL);
        rongCloudService.removeBlacklist(customer.getAccountName(), friend.getAccountName());
    }

    @Transactional
    public List<CustomerFriend> getBlacklist(int customerId) throws Exception {
        Customer customer = customerDao.findByCustomerId(customerId);
        List<CustomerFriend> blacklist = customerFriendDao.findCustomerFriendByCustomerCustomerIdAndState(customerId, CustomerFriendState.BLACKLIST);
        BlackListResult result = rongCloudService.getBlacklist(customer.getAccountName());
        List<String> blacklistId = new ArrayList<>();
        List<String> blacklistIdRongcloud = new ArrayList<>();
        for (CustomerFriend cf : blacklist) {
            blacklistId.add(cf.getFriend().getAccountName());
        }
        for (UserModel um : result.getUsers()) {
            blacklistIdRongcloud.add(um.id);
        }

        //同步融云blackList
        List<String> tempList = new ArrayList<>();
        tempList.addAll(blacklistId);
        tempList.removeAll(blacklistIdRongcloud);
        for (String friend : tempList) {
            rongCloudService.addBlacklist(customer.getAccountName(), friend);
        }
        blacklistIdRongcloud.removeAll(blacklistId);
        for (String friend : blacklistIdRongcloud) {
            rongCloudService.removeBlacklist(customer.getAccountName(), friend);
        }
        return blacklist;
    }


    /**
     * 双向添加好友，并且修改申请添加记录
     *
     * @param myself
     * @param customer
     * @return void
     * @throws
     * @author likang
     * @date 2018/7/19 20:16
     */
    @Transactional
    public void addTwoWayFriend(Customer myself, Customer customer) throws Exception {
        //修改添加好友记录为已同意
        customerFriendRequestService.updateCustomerFriendRequestState(customer.getCustomerId(), myself.getCustomerId(), CustomerFriendRequestState.AGREE.getState());
        CustomerFriend customerFriend = new CustomerFriend();
        CustomerFriendId customerFriendId = new CustomerFriendId();
        customerFriendId.setCustomerId(myself.getCustomerId());
        customerFriendId.setFriendId(customer.getCustomerId());
        customerFriend.setId(customerFriendId);
        customerFriend.setCustomer(customer);
        customerFriend.setFriend(customer);
        customerFriend.setCreateTime(new Date());
        customerFriend.setState(CustomerFriendState.NORMAL);
        addFriend(customerFriend);
        //双向加好友
        customerFriend = new CustomerFriend();
        customerFriendId = new CustomerFriendId();
        customerFriendId.setCustomerId(customer.getCustomerId());
        customerFriendId.setFriendId(myself.getCustomerId());
        customerFriend.setId(customerFriendId);
        customerFriend.setCustomer(customer);
        customerFriend.setFriend(myself);
        customerFriend.setCreateTime(new Date());
        customerFriend.setState(CustomerFriendState.NORMAL);
        addFriend(customerFriend);
        rongCloudService.sendPrivateMessage(customer.getAccountName(), myself.getAccountName(), "AcceptResponse");
    }

    /**
     * 批量更新密码加密
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchPwdEncode() {
        List<Customer> customers = customerDao.findAll();
        if (customers != null) {
            customers.forEach(customer -> {
                if (!customer.getPassword().contains("sha1:")) {
                    try {
                        String pwd = PasswordHash.createHash(customer.getPassword());
                        customer.setPassword(pwd);
                    } catch (PasswordHash.CannotPerformOperationException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 查询推荐的下级人信息
     * @author      likang
     * @param customer
     * @return      java.util.List<com.yunxin.cb.mall.vo.CustomerTreeVo>
     * @exception
     * @date        2018/8/6 9:55
     */
    @Override
    @Transactional(readOnly = true)
    public List<Customer> findCustomerByLikeLevelCode(Customer customer) {
        String like = customer.getLevelCode()+"%";
        List<Customer> list = customerDao.findCustomerByLikeLevelCode(like);
        List<Customer> listVo=new ArrayList<>();
        if(list!=null){
            for (Customer p:list) {
                //清除自己
                if(p.getCustomerId()==customer.getCustomerId()){
                    continue;
                }
                //清除上级
                if(p.getRecommendCustomer()!=null&&  p.getRecommendCustomer().getCustomerId()==customer.getCustomerId()){
                    p.setRecommendCustomer(null);
                }
                listVo.add(p);
            }
        }
        return listVo;
    }

    @Override
    public CustomerGratitudeVo findCustomerGratitude(int customerId) {
        Customer customer= customerDao.findRecommendCustomer(customerId);

        return new CustomerGratitudeVo(){
            {
                    String levelCode = customer.getLevelCode()+"%";
                    //所有感恩人
                    setAllGratitude(customerDao.findAllCustomerByLikeLevelCode(customerId,levelCode,PolicyType.PAYMENT));
                    //已经感恩
                    setGratitudeMe(customerDao.getCustomerByRecommendCustomer(customerId,PolicyType.PAYMENT,true));
                    //未感恩
                    setNoGratitude(customerDao.getCustomerByRecommendCustomer(customerId,PolicyType.PAYMENT,false));
                    //未付款
                    setUnpaid(customerDao.getCustomerByRecommendCustomer(customerId,PolicyType.UNPAID,false));
                    //未购买
                    setNotPurchased(customerDao.getCustomerByRecommendCustomer(customerId,PolicyType.NOTPURCHASED,false));

                    String recommendName=customer.getRecommendCustomer().getNickName();
                        if(StringUtils.isEmpty(customer.getRecommendCustomer().getNickName()))
                            recommendName=customer.getRecommendCustomer().getMobile();
                    //推荐人
                    setRecommendName(recommendName);
            }
        };
    }

    @Override
    public List<CustomerGratitudeDataVo> findCustomerGratitudeData(int customerId,GratitudeType gratitudeType) {
        Customer customer= customerDao.findRecommendCustomer(customerId);
        return new ArrayList<CustomerGratitudeDataVo>(){
            {
                if(null!=customer){

                    if(GratitudeType.NOTPURCHASED.equals(gratitudeType)){
                        //未付款
                        List<Customer> listCustomer=customerDao.getCustomerByRecommendCustomers(customerId,PolicyType.NOTPURCHASED,false);

                        if(null!=listCustomer&&listCustomer.size()>0){
                            for (Customer customer:listCustomer){
                                add(new CustomerGratitudeDataVo(){
                                    {
                                        setGratitudeType(gratitudeType);
                                        setHeadPath(customer.getAvatarUrl());
                                        String userName=customer.getRealName();
                                        if(StringUtils.isEmpty(customer.getRealName())){
                                            userName=customer.getNickName();
                                            if(StringUtils.isEmpty(customer.getNickName()))
                                                userName=customer.getMobile();
                                        }
                                        setUserName(userName);
                                    }
                                });
                            }
                        }
                    }else{
                        List<InsuranceOrderLog> list=new ArrayList<>();
                        switch (gratitudeType){
                            //感恩我的
                            case GRATITUDEME:
                                list=insuranceOrderLogDao.findOrderLogByLevelCode(customerId,InsuranceOrderState.ON_PAID,true);
                                break;
                            //未感恩
                            case NOGRATITUDE:
                                list=insuranceOrderLogDao.findOrderLogByLevelCode(customerId,InsuranceOrderState.ON_PAID,false);
                                break;
                            //未付款
                            case UNPAID:
                                list=insuranceOrderLogDao.findInsuranceOrderLogByLevelCode(customerId,InsuranceOrderState.UN_PAID,PolicyType.UNPAID);
                                break;
                        }

                        if(null!=list&&list.size()>0){
                            for(InsuranceOrderLog insuranceOrderLog:list){
                                add(new CustomerGratitudeDataVo(){
                                    {
                                        setGratitudeType(gratitudeType);
                                        setHeadPath(insuranceOrderLog.getCustomer().getAvatarUrl());
                                        String userName=insuranceOrderLog.getCustomer().getRealName();
                                        if(StringUtils.isEmpty(insuranceOrderLog.getCustomer().getRealName())){
                                            userName=insuranceOrderLog.getCustomer().getNickName();
                                        if(StringUtils.isEmpty(insuranceOrderLog.getCustomer().getNickName()))
                                             userName=insuranceOrderLog.getCustomer().getMobile();
                                        }
                                        setUserName(userName);
                                        setProductName(insuranceOrderLog.getProdName()+(insuranceOrderLog.getPrice()>10000?(insuranceOrderLog.getPrice()/10000)+"万":insuranceOrderLog.getPrice()+"元"));
                                    }
                                });
                            }

                        }

                    }

                }
            }
        };

    }

    @Override
    public MyTotalVo getInterpersonal(int customerId) {
        Customer customer= customerDao.findRecommendCustomer(customerId);
        return new MyTotalVo(){
            {
                String levelCode = customer.getLevelCode()+"%";
                setInterpersonalTotal(customerDao.findAllCustomerByLikeLevelCode(customerId,levelCode,PolicyType.PAYMENT));
                setFavoriteTotal((int)favoriteDao.countByCustomer(customer));
                //足迹统计
                Calendar calendar=Calendar.getInstance();
                calendar.setTime(new Date());
                //结束时间
                Date endTime=calendar.getTime();
                calendar.add(Calendar.MONTH, -1);
                //开始时间
                Date startTime=calendar.getTime();
                List<HistoryRecord> list=historyRecordDao.countHistoryRecordByCustomer(customer.getCustomerId(),startTime,endTime);
                if(null!=list&&list.size()>0)
                    setRecordTotal(list.size());
                else
                    setRecordTotal(0);
            }
        };

    }

    @Override
    public  void enableCustomerById(int customerId,boolean enabled){
        customerDao.enableCustomerById(customerId,enabled);
    }

    @Override
    public void CancellationCustomerById(int customerId,boolean ynDelete,String time){
        customerDao.CancellationCustomerById(customerId,ynDelete,time);
    }
}
