package cn.ffcs.dao;

import cn.ffcs.domain.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by MәӧωρaЯsε on 2017/6/21.
 */
public interface SysRoleDao extends JpaRepository<SysRole,Integer>{

    @Query(value = "update sys_role_permission set permission_id = ? where role_id = ?",nativeQuery = true)
    @Modifying
    @Transactional
    void updateRolePermission(String permissions,int roleId);

}
