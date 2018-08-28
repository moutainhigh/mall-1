package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.console.dao.UserDao;
import com.yunxin.cb.console.service.ISecurityService;
import com.yunxin.cb.mall.dao.SellerDao;
import com.yunxin.cb.mall.entity.Seller;
import com.yunxin.cb.mall.entity.Seller_;
import com.yunxin.cb.mall.entity.meta.SellerType;
import com.yunxin.cb.mall.service.ISellerService;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 *
 *
 */
@Service
@Transactional
public class SellerService implements ISellerService {

    @Resource
    private SellerDao sellerDao;

    @Resource
    private UserDao userDao;

    @Resource
    private ISecurityService securityService;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Seller> pageSellers(final PageSpecification<Seller> query) {
        query.setCustomSpecification(new CustomSpecification<Seller>() {
            @Override
            public void buildFetch(Root<Seller> root) {
                //root.fetch(Seller_.sysUser, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<Seller> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(Seller_.createTime)));
            }
        });
        Page<Seller> sellers = sellerDao.findAll(query, query.getPageRequest());
        return sellers;
    }

    @Override
    public Seller addSeller(Seller seller) throws EntityExistException {
        if (seller.getSellerType() == SellerType.SELF_OPERATION) {
            Seller oldSel = sellerDao.checkSellerBySellerTypeForAdd(SellerType.SELF_OPERATION);
            if (null != oldSel) {
                throw new EntityExistException("平台自营商家已存在");
            }
        }

        if (!sellerDao.isOrUnique(seller, Seller_.sellerCode, Seller_.sellerName,Seller_.email,Seller_.idCardNum)) {
            throw new EntityExistException("商家编码、名称、邮箱或身份证不能重复，请检查");
        }

        seller.setAudit(true);
        seller.setEnabled(true);
        seller.setCreateTime(new Date());
        Seller sellerDb = sellerDao.save(seller);

        // 自动分配user账号删除
//        Role role = new Role();
//        role.setRoleCode(sellerDb.getSellerCode() + "_ADMIN");
//        role.setRoleName(sellerDb.getSellerName() + "_管理员");
//        role.setRemark(sellerDb.getSellerName() + "_管理员");
//        role.setSeller(sellerDb);
//        String rescCodes = "3,33,5,51,52,53";
//
//        role.setRescCodes(rescCodes);
//
//        role = securityService.addRole(role);

//        User user = new User();
//        user.setUserName(seller.getSellerCode());
//        user.setPassword("123456");
//        user.setCreateTime(new Date());
//        user.setLastTime(new Date());
//        user.setEmail("123456");
//        user.setMobile("123456");
//        user.setSeller(sellerDb);
//
//        user.getRoles().add(role);
//        userDao.save(user);
        return sellerDb;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Seller getSellerById(int sellerId) {
        return sellerDao.findOne(sellerId);
    }

    @Override
    public Seller updateSeller(Seller seller) throws EntityExistException {
        if (seller.getSellerType() == SellerType.SELF_OPERATION) {
            Seller oldSel = sellerDao.checkSellerBySellerTypeForEdit(SellerType.SELF_OPERATION, seller.getSellerId());
            if (null != oldSel) {
                throw new EntityExistException("平台自营商家已存在");
            }
        }

        if (!sellerDao.isOrUnique(seller, Seller_.sellerCode, Seller_.sellerName,Seller_.email,Seller_.idCardNum)) {
            throw new EntityExistException("商家编码、名称、邮箱或身份证不能重复，请检查");
        }
        Seller sellerDB = sellerDao.findOne(seller.getSellerId());
        AttributeReplication.copying(seller, sellerDB, Seller_.sellerName, Seller_.sellerCode, Seller_.sellerAddress, Seller_.sellerType, Seller_.linkman,
                Seller_.mobile, Seller_.telephone, Seller_.email, Seller_.qq, Seller_.wechat, Seller_.channelType, Seller_.channelAccount,
                Seller_.busName, Seller_.buslicenseNo, Seller_.accountName, Seller_.publicAccount, Seller_.bankAccount,
                Seller_.bankAccountAddress, Seller_.idCardNum, Seller_.remark, Seller_.positionX, Seller_.positionY,
                Seller_.province, Seller_.city, Seller_.district,Seller_.provinceName, Seller_.cityName, Seller_.districtName);
        return sellerDB;
    }

    @Override
    public void enableSellerById(int sellerId, boolean enabled) {
        sellerDao.enableSellerById(enabled, sellerId);
    }

    @Override
    public void removeSellerById(int sellerId) {
        sellerDao.delete(sellerId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getSellerCount() {
        return sellerDao.count();
    }

    @Override
    public boolean queryIsExistsMgt(Integer sellerId) {
        Seller oldSel = sellerDao.checkSellerBySellerTypeForEdit(SellerType.SELF_OPERATION, sellerId);
        if (null != oldSel) {
            return true;
        }else{
            return false;
        }
    }
}
