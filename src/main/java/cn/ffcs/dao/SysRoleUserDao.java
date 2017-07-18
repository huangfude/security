package cn.ffcs.dao;

import com.google.common.base.Strings;
import cn.ffcs.domain.SysRoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 *
 * Created by MәӧωρaЯsε on 2017/5/5.
 */
public interface SysRoleUserDao extends JpaRepository<SysRoleUser,Integer>{

    void deleteByUserId(Integer userId);

    @Query(value = "DELETE FROM sys_role_user WHERE sys_user_id = ? AND sys_role_id " +
                   "IN ( SELECT sys_role_id FROM (SELECT sys_role_id FROM sys_role_user " +
                   "WHERE sys_user_id = ? ) AS temtable )  ",nativeQuery = true)
    @Transactional
    @Modifying
    void deleteByUserIdAndRoleIds(int userId, int roleIds);

    @Query(value = "select group_concat(sys_role_id order by sys_role_id asc separator ',') as sys_role_id " +
                   "from sys_role_user where sys_user_id =  ?",nativeQuery = true)
    String findByUserId(int userId);

    @Query(value = "select sys_role_id from sys_role_user where sys_user_id = ?",nativeQuery = true)
    List<Long> findRoleIdByUserId(int userID);

    @Query(value = "select sys_role_id from sys_role_user where sys_user_id = ?",nativeQuery = true)
    List<Integer> findOneByRoleAndUser(int currentUserId);
}
