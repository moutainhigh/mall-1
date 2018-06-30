package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.console.dao.UserDao;
import com.yunxin.cb.console.entity.Role;
import com.yunxin.cb.console.entity.User;
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
import java.util.ArrayList;
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
                query.orderBy(builder.asc(root.get(Seller_.sellerName)));
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

        if (!sellerDao.isOrUnique(seller, Seller_.sellerCode, Seller_.sellerName)) {
            throw new EntityExistException("商家编码或商家名称已存在");
        }

        seller.setAudit(true);
        seller.setEnabled(true);
        seller.setCreateTime(new Date());
        Seller sellerDb = sellerDao.save(seller);

        // 自动分配user账号
        Role role = new Role();
        role.setRoleCode(sellerDb.getSellerCode() + "_ADMIN");
        role.setRoleName(sellerDb.getSellerName() + "_管理员");
        role.setRemark(sellerDb.getSellerName() + "_管理员");
        role.setSeller(sellerDb);
        List<String> rescCodes = new ArrayList<String>();
        rescCodes.add("3");
        rescCodes.add("33");

        rescCodes.add("5");
        rescCodes.add("51");
        rescCodes.add("52");
        rescCodes.add("53");
        role.setRescCodes((String[]) rescCodes.toArray());

        role = securityService.addRole(role);

        User user = new User();
        user.setUserName(seller.getSellerCode());
        user.setPassword("123456");
        user.setCreateTime(new Date());
        user.setLastTime(new Date());
        user.setEmail("123456");
        user.setMobile("123456");
        user.setSeller(sellerDb);

        user.getRoles().add(role);
        userDao.save(user);
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

        if (!sellerDao.isOrUnique(seller, Seller_.sellerCode, Seller_.sellerName)) {
            throw new EntityExistException("商家编码或商家名称已存在");
        }
        Seller sellerDB = sellerDao.findOne(seller.getSellerId());
        AttributeReplication.copying(seller, sellerDB, Seller_.sellerName, Seller_.sellerCode, Seller_.sellerAddress, Seller_.sellerType, Seller_.linkman,
                Seller_.mobile, Seller_.telephone, Seller_.email, Seller_.qq, Seller_.wechat, Seller_.channelType, Seller_.channelAccount,
                Seller_.busName, Seller_.buslicenseNo, Seller_.accountName, Seller_.publicAccount, Seller_.bankAccount,
                Seller_.bankAccountAddress, Seller_.idCardNum, Seller_.remark);
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
}
