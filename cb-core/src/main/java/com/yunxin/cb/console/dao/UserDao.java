package com.yunxin.cb.console.dao;

import com.yunxin.cb.console.entity.User;
import com.yunxin.core.orm.BaseDao;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * @author gonglei
 */
public interface UserDao extends UserPlusDao, JpaRepository<User, Integer>, JpaSpecificationExecutor<User>, BaseDao<User> {

    public List<User> findByEnabled(boolean enable);

    @Modifying
    @Query("update User user set user.enabled=?1 where user.userId=?2")
    public void enabledUserById(boolean enable, int userId);

    @Query("select u from User u left join fetch u.roles left join fetch u.seller where u.userName=?1")
    public User findByUserName(String userName);

    @Query("select u from User u where u.userName=?1 and u.userId<>?2")
    public User findByUserNameAndUserIdNot(String userName, int userId);


    @Query("select u from User u where u.userName=?1 and u.password=?2")
    public User findByUserNameAndPassword(String userName, String password);

    User findByEmail(String email);

    @Query("select u from User u left join fetch u.roles where u.userId=?1")
    public User getAllRolesByUserId(int userId);

    @Query("select u from User u left join fetch u.roles where u.userId=?1")
    public User getUserDetailById(int userId);

    @Query("select u from User u left join fetch u.roles r  where r.roleCode=?1")
    public List<User> findUsersByRole(String roleCode);

    @Query("select u from User u left join fetch u.roles where u.userName=:userName")
    User findTopByUserName(@Param("userName") String userName);

    @Modifying
    @Query("update User u set u.lastTime=:lastTime where u.userId=:userId")
    void updateLoginTime(@Param("userId") int userId, @Param("lastTime") Date date);

}

interface UserPlusDao {
    public List<User> findUsersByRoles(String roleCode, int start, int end);

    List<User> pageUsersByRole(PageSpecification<User> conQuery);

    void execute(String ...sql);
}
