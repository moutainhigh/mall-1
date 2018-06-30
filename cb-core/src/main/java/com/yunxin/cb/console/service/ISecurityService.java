/**
 *
 */
package com.yunxin.cb.console.service;

import com.yunxin.cb.console.entity.Role;
import com.yunxin.cb.console.entity.RoleResc;
import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.mall.entity.Seller;
import com.yunxin.cb.security.Privilege;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;

/**
 * @author gonglei
 */
public interface ISecurityService {

    /**
     * IUserService
     *
     * @param
     * @return
     */
    User getUserByName(String userName);

    List<Role> getAllRoles() throws Exception;

    public Set<Role> getRolesByUserId(int userId);

    public User addUser(User user) throws EntityExistException;

    public User updateUser(User user) throws EntityExistException;


    public User updateUserLastTime(User user);

    public User getUserById(int userId);

    public void enabledUserById(boolean enable, int userId);

    void removeUserById(int userId);

    public Page<User> pageUsers(final PageSpecification<User> query);

    User getUserByEmail(String email);

    User findByUserNameAndPassword(String userName, String password) throws Exception;

    User findByUserNameAndPassword1(String userName, String password);


    /**
     * 修改密码
     *
     * @param userId
     * @param password
     * @return
     */
    User changePassword(int userId, String password) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException;


    /**
     * IRoleService
     * 角色
     */

    public Role addRole(Role role) throws EntityExistException;

    public Role updateRole(Role role) throws EntityExistException;

    public Role getRoleById(int roleId);

    public void removeRoleById(int roleId);


    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<Role> getRolesBySeller(Seller seller);


    public Role getRoleById1(int roleId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<RoleResc> getAllRoleRescs();


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

    List<RoleResc> getRoleRescsByRole(Role role);

    List<String> getRescCodesByUser(User user);

    UserDetails getUserDetailsByName(String userName);

    public List<Privilege> loadPrivilegesDefine();
}
