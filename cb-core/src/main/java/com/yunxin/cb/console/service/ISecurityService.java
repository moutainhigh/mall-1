/**
 *
 */
package com.yunxin.cb.console.service;


import com.yunxin.cb.console.entity.Permission;
import com.yunxin.cb.console.entity.Role;

import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.mall.entity.Feedback;
import com.yunxin.cb.mall.entity.Seller;

import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author gonglei
 */
public interface ISecurityService extends UserDetailsService {

    /**
     * IUserService
     *
     * @param
     * @return
     */
    User getUserByName(String userName);

    void initAdminAccount() throws Exception;

    void batchPwdEncode();

    List<Role> getAllRoles() throws Exception;

    public Set<Role> getRolesByUserId(int userId);

    public User addUser(User user)  throws Exception;

    public User updateUser(User user) throws EntityExistException;


    public User updateUserLastTime(User user);

    public User getUserById(int userId);

    public void enabledUserById(boolean enable, int userId);

    void removeUserById(int userId);

    public Page<User> pageUsers(final PageSpecification<User> query);

    User getUserByEmail(String email);

    User findByUserNameAndPassword(String userName, String password);

    User findByUserNameAndPassword1(String userName, String password);


    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<Permission> getPermissionsByRole(Role role);

    void updateLoginTime(int userId, Date date);

    /**
     * 修改密码
     *
     * @param userId
     * @param password
     * @return
     */
    User changePassword(int userId, String password);


    /**
     * IRoleService
     * 角色
     */

    public Role addRole(Role role) throws EntityExistException;

    public Role updateRole(Role role) throws EntityExistException;

    public Role getRoleById(int roleId);

    public void removeRoleById(int roleId) throws Exception;



    List<String> getPrivilegeCodesByRoleId(int roleId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<Role> getRolesBySeller(Seller seller);


    public Role getRoleById1(int roleId);




    public List<Role> queryRolersByUser(int userId) throws Exception;


    /**
     * 根据角色编号查询用户
     *
     * @param roleCode
     * @return
     */
    public List<User> getUsersByRole(String roleCode);

    /**
     * 根据角色编号查询用户
     *
     * @param roleCode
     * @return
     */
    public List<User> getUsersByRoles(String roleCode, int start, int end);

    public long countUsersByRoles(String roleCode);


    void execute(String ...sql);

    UserDetails getUserDetailsByName(String userName);


    public Page<Role> pageRole(final PageSpecification<Role> queryRequest);

}
