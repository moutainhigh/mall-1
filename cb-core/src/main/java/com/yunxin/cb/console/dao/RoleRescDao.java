/**
 *
 */
package com.yunxin.cb.console.dao;

import com.yunxin.cb.console.entity.Role;
import com.yunxin.cb.console.entity.RoleResc;
import com.yunxin.cb.console.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author gonglei
 */
public interface RoleRescDao extends JpaRepository<RoleResc, Integer> {

    @Modifying
    @Query("delete from RoleResc rr where rr.role=?1")
    public void removeRoleResc(Role role);

    public List<RoleResc> findByRole(Role role);

    @Query("select rr from RoleResc rr left join fetch rr.role")
    public List<RoleResc> findAllRoles();

    @Query("select distinct rr.rescCode from RoleResc rr left join rr.role role left join role.users user where user=?1")
    List<String> findRescCodesByUser(User user);
}
