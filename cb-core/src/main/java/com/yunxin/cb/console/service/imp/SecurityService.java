package com.yunxin.cb.console.service.imp;

import com.yunxin.cb.console.dao.RoleDao;
import com.yunxin.cb.console.dao.RoleRescDao;
import com.yunxin.cb.console.dao.UserDao;
import com.yunxin.cb.console.entity.*;
import com.yunxin.cb.console.service.ISecurityService;
import com.yunxin.cb.mall.entity.Seller;
import com.yunxin.cb.mall.entity.Seller_;
import com.yunxin.cb.security.Privilege;
import com.yunxin.cb.security.SecurityHolder;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.LogicUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.criteria.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * @author gonglei
 */
@Service
@Transactional
public class SecurityService implements ISecurityService, SecurityHolder {

    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private RoleRescDao roleRescDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User getUserByName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User findByUserNameAndPassword(String userName, String password) {
        return userDao.findByUserNameAndPassword(userName, password);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }


    @Override
    public User addUser(User user) throws EntityExistException {
        if (!userDao.isUnique(user, User_.userName)) {
            throw new EntityExistException("用户名已存在");
        }
        int[] roleId = user.getRoldIds();
        if (roleId != null) {
            for (int i = 0; i < roleId.length; i++) {
//                Role role = new Role(roleId[i]);
                user.getRoles().add(roleDao.findOne(roleId[i]));
            }
        }
        Date date = new Date();
        user.setCreateTime(date);
        user.setLastTime(date);
        return userDao.save(user);
    }


    @Override
    public User updateUser(User user) throws EntityExistException {
        if (!userDao.isUnique(user, User_.userName)) {
            throw new EntityExistException("用户名已存在");
        }
        User dbUser = userDao.findOne(user.getUserId());
        AttributeReplication.copying(user, dbUser, User_.userName, User_.realName, User_.sex, User_.email, User_.position, User_.mobile, User_.enabled, User_.remark);
        dbUser.getRoles().clear();
        int[] roleId = user.getRoldIds();
        if (roleId != null) {
            for (int i = 0; i < roleId.length; i++) {
                dbUser.getRoles().add(roleDao.findOne(roleId[i]));
            }
        }
        return dbUser;
    }

    @Override
    public User updateUserLastTime(User user) {
        User dbUser = userDao.findOne(user.getUserId());
        dbUser.setLastTime(new Date());
        return dbUser;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User getUserById(int userId) {
        User user = userDao.getUserDetailById(userId);
        return user;
    }

    @Override
    public void enabledUserById(boolean enable, int userId) {
        userDao.enabledUserById(enable, userId);
    }

    @Override
    public void removeUserById(int userId) {
        userDao.delete(userId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<User> pageUsers(final PageSpecification<User> userQuery) {
        userQuery.setCustomSpecification(new CustomSpecification<User>() {

            @Override
            public void buildFetch(Root<User> root) {
                root.fetch(User_.roles, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(User_.createTime)));
            }
        });
        Page<User> pages = userDao.findAll(userQuery, userQuery.getPageRequest());
        return pages;
    }


    /**
     * 角色Service
     */
    @Override
    public Role addRole(Role role) throws EntityExistException {
        if (!roleDao.isOrUnique(role, Role_.roleCode, Role_.roleName)) {
            throw new EntityExistException("角色编码或名称已存在");
        }
        role.setCreateTime(new Date());
        Role newRole = roleDao.save(role);
        String[] rescCodes = role.getRescCodes();
        if (rescCodes != null && rescCodes.length > 0) {
            for (String rescCode : rescCodes) {
                RoleResc roleResc = new RoleResc(newRole, rescCode);
                roleRescDao.save(roleResc);
            }
        }
        return newRole;
    }

    @Override
    public Role updateRole(Role role) throws EntityExistException {
        if (!roleDao.isOrUnique(role, Role_.roleCode, Role_.roleName)) {
            throw new EntityExistException("角色编码或名称已存在");
        }
        Role dbrole = roleDao.findOne(role.getRoleId());
        dbrole.setRoleName(role.getRoleName());
        dbrole.setRoleCode(role.getRoleCode());
        dbrole.setRemark(role.getRemark());
        String[] rescCodes = role.getRescCodes();
        if (rescCodes != null && rescCodes.length > 0) {
            roleRescDao.removeRoleResc(role);
            for (String rescCode : rescCodes) {
                RoleResc roleResc = new RoleResc(dbrole, rescCode);
                roleRescDao.save(roleResc);
            }
        }else
            throw new EntityExistException("请至少选中一个权限进行操作");
        return dbrole;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Role getRoleById(int roleId) {
        return roleDao.findOne(roleId);

    }

    @Override
    public void removeRoleById(int roleId) {
        roleRescDao.removeRoleResc(new Role(){
            {
                setRoleId(roleId);
            }
        });
        roleDao.delete(roleId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<RoleResc> getRoleRescsByRole(Role role) {
        return roleRescDao.findByRole(role);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Role> getRolesBySeller(Seller seller) {
        return roleDao.findAll(new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                predicates.add(criteriaBuilder.equal(root.get(Role_.seller).get(Seller_.sellerCode), seller.getSellerCode()));
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(Role_.roleName)));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Role getRoleById1(int roleId) {
        return roleDao.findByRoleId1(roleId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<RoleResc> getAllRoleRescs() {
        return roleRescDao.findAllRoles();
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Role> getAllRoles() throws Exception {
        return roleDao.findAll(new Sort(Sort.Direction.ASC, "roleName"));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Set<Role> getRolesByUserId(int userId) {
        User user = userDao.getAllRolesByUserId(userId);
        return user.getRoles();
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Role> queryRolersByUser(int userId) throws Exception {
        return roleDao.queryRolersByUser(userId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<String> getRescCodesByUser(User user) {
        return roleRescDao.findRescCodesByUser(user);
    }


    @Override
    public User changePassword(int userId, String password)
            throws InvalidKeyException, NoSuchPaddingException,
            NoSuchAlgorithmException, BadPaddingException,
            IllegalBlockSizeException {
        User user = userDao.findOne(userId);
        user.setPassword(password);
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User findByUserNameAndPassword1(String userName, String password) {
        return userDao.findByUserNameAndPassword(userName, password);
    }

    /**
     * 根据角色编号查询用户
     *
     * @param roleCode
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<User> getUsersByRole(String roleCode) {
        return userDao.findUsersByRole(roleCode);
    }

    /**
     * 前台：根据角色编号查询用户
     */
    @Override
    public List<User> getUsersByRoles(String roleCode, int start, int end) {
        return userDao.findUsersByRoles(roleCode, start, end);
    }

    @Override
    public long countUsersByRoles(String roleCode) {
        List<User> users = userDao.findUsersByRole(roleCode);
        if (LogicUtils.isNotNullAndEmpty(users)) {
            return users.size();
        } else {
            return 0L;
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public UserDetails getUserDetailsByName(String userName) {
        User user = userDao.findByUserName(userName);
        return user;
    }

    @Override
    public List<Privilege> loadPrivilegesDefine() {
        List<RoleResc> roleRescs = roleRescDao.findAllRoles();
        List<Privilege> privileges = new ArrayList<>(roleRescs.size());
        for (RoleResc roleResc : roleRescs) {
            privileges.add(roleResc);
        }
        return privileges;
    }


}
