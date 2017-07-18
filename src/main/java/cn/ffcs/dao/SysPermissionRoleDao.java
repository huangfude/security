package cn.ffcs.dao;

import cn.ffcs.domain.SysPermissionRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 *
 * Created by MәӧωρaЯsε on 2017/5/5.
 */
public interface SysPermissionRoleDao extends JpaRepository<SysPermissionRole,Long>{


    @Query(value = "update sys_permission_role set permission_id = ? where role_id = ?",nativeQuery = true)
    void saveRolePermission(String ids,int roleId);

    @Query(value = "select permission_id from sys_permission_role where role_id = ?",nativeQuery = true)
    String getRolePermission(int roleId);

}
