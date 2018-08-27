package com.yunxin.cb.console.service.imp;

import com.yunxin.cb.console.dao.PermissionDao;
import com.yunxin.cb.console.dao.RoleDao;
import com.yunxin.cb.console.dao.UserDao;
import com.yunxin.cb.console.entity.*;
import com.yunxin.cb.console.service.ISecurityService;
import com.yunxin.cb.mall.entity.Feedback;
import com.yunxin.cb.mall.entity.Feedback_;
import com.yunxin.cb.mall.entity.Seller;
import com.yunxin.cb.mall.entity.Seller_;
import com.yunxin.cb.security.IPermission;
import com.yunxin.cb.security.PBKDF2PasswordEncoder;
import com.yunxin.cb.security.SecurityProvider;
import com.yunxin.cb.util.PasswordHash;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.LogicUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.*;
import java.util.stream.Collectors;

import static com.yunxin.cb.security.SecurityConstants.ADMINISTRATOR;
import static com.yunxin.cb.security.SecurityConstants.SUPER_ROLE;


/**
 * @author gonglei
 */
@Service
@Transactional
public class SecurityService extends SecurityProvider implements ISecurityService {

    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private PermissionDao permissionDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User getUserByName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User findByUserNameAndPassword(String userName, String password) {
        PBKDF2PasswordEncoder pbkdf2 = new PBKDF2PasswordEncoder();
        return userDao.findByUserNameAndPassword(userName,pbkdf2.encode(password));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }


    @Override
    public User addUser(User user) throws Exception {
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
        //user.setLastTime(date);
        user.setPassword(PasswordHash.createHash(user.getPassword()));
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
        if (StringUtils.isNotBlank(role.getRescCodes())) {
            String[] rescCodes = role.getRescCodes().split(",");
            // 保存可访问资源
            List<Permission> rolePrivileges = Arrays.stream(rescCodes)
                    .map(rescCode -> new Permission(newRole, rescCode))
                    .collect(Collectors.toList());
            permissionDao.save(rolePrivileges);
        }
        return newRole;
    }

    @Override
    public Role updateRole(Role role) throws EntityExistException {
        if (!roleDao.isUnique(role, Role_.roleName)) {
            throw new EntityExistException("角色名已存在");
        }
        Role dbRole = roleDao.findOne(role.getRoleId());
        dbRole.setRoleName(role.getRoleName());
        dbRole.setRoleCode(role.getRoleCode());
        dbRole.setRemark(role.getRemark());
        // 保存新的可访问资源
        permissionDao.deleteByRole_RoleId(dbRole.getRoleId());
        if (StringUtils.isNotBlank(role.getRescCodes())) {
            String[] rescCodes = role.getRescCodes().split(",");
            List<Permission> roleRescs = Arrays.stream(rescCodes)
                    .map(rescCode -> new Permission(dbRole, rescCode))
                    .collect(Collectors.toList());
            permissionDao.save(roleRescs);
        }
        return dbRole;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Role getRoleById(int roleId) {
        return roleDao.findOne(roleId);

    }

    @Override
    public void removeRoleById(int roleId) throws Exception {
        Role dbRole = roleDao.getOne(roleId);
        if (SUPER_ROLE.equals(dbRole.getRoleCode())) {
            // 系统超级管理员角色不能删除
            throw new Exception("系统超级管理员角色不能删除");
        }
        if (dbRole.getUsers().isEmpty()) {
            permissionDao.deleteByRole_RoleId(roleId);
            roleDao.delete(roleId);
        }else{
            throw new Exception("该角色下存在用户无法删除");
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<String> getPrivilegeCodesByRoleId(int roleId) {
        return permissionDao.findPrivilegeCodesByRoleId(roleId);
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
    public void initAdminAccount() throws Exception{
        Role superRole = roleDao.findFirstByRoleCode(SUPER_ROLE);
        if (superRole == null) {
            superRole = new Role();
            superRole.setRoleCode(SUPER_ROLE);
            superRole.setRoleName("超级管理员");
            superRole.setCreateTime(new Date());
            superRole = roleDao.save(superRole);
        }
        User admin = userDao.findTopByUserName(ADMINISTRATOR);
        if (admin == null) {
            admin = new User();
            admin.setUserName(ADMINISTRATOR);
            String pwd = PasswordHash.createHash("123456");
            admin.setPassword(pwd);
            admin.setCreateTime(new Date());
            admin = userDao.save(admin);
            admin.getRoles().add(superRole);
        }
    }

    /**
     * 批量更新密码加密
     */
    @Override
    public void batchPwdEncode() {
       List<User> users = userDao.findAll();
       users.forEach(user -> {
           try {
               if(!user.getPassword().contains("sha1:")){
                   String pwd = PasswordHash.createHash(user.getPassword());
                   user.setPassword(pwd);
               }
           } catch (PasswordHash.CannotPerformOperationException e) {
               e.printStackTrace();
           }

       });
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
    public List<Permission> getPermissionsByRole(Role role){
        return permissionDao.findByRole_RoleId(role.getRoleId());
    }

    @Override
    public void updateLoginTime(int userId, Date date) {
        userDao.updateLoginTime(userId, date);
    }

    @Override
    public User changePassword(int userId, String password) {
        User user = userDao.findOne(userId);
        PBKDF2PasswordEncoder pbkdf2 = new PBKDF2PasswordEncoder();
        user.setPassword(pbkdf2.encode(password));
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User findByUserNameAndPassword1(String userName, String password) {
        PBKDF2PasswordEncoder pbkdf2 = new PBKDF2PasswordEncoder();
        return userDao.findByUserNameAndPassword(userName, pbkdf2.encode(password));
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
    public void execute(String ...sql){
        userDao.execute(sql);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public UserDetails getUserDetailsByName(String userName) {
        User user = userDao.findByUserName(userName);
        return user;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<? extends IPermission> getAllPermissions() {
        //关联role
        return permissionDao.findDetailPermissions();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<String> getPrivilegeCodesByUserName(String userName) {
        return permissionDao.findPrivilegeCodesByUserName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findTopByUserName(userName);
        if (user != null) {
            List<GrantedAuthority> authorities = user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getRoleCode()))
                    .collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), user.isEnabled(), user.isEnabled(), user.isEnabled(), user.isEnabled(), authorities);
        } else {
            return null;
        }
    }

    public Page<Role> pageRole(final PageSpecification<Role> queryRequest){
        queryRequest.setCustomSpecification(new CustomSpecification<Role>() {
            @Override
            public void buildFetch(Root<Role> root) {
            }
            @Override
            public void addConditions(Root<Role> root, CriteriaQuery<?> query,
                                      CriteriaBuilder builder, List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(Role_.createTime)));
            }
        });
        Page<Role> page = roleDao.findAll(queryRequest, queryRequest.getPageRequest());
        return page;
    }
}
