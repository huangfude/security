package cn.ffcs.dao;

import cn.ffcs.domain.SysPermission;
import cn.ffcs.model.PermissionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

/**
 *
 * Created by MәӧωρaЯsε on 2017/5/5.
 */
public interface SysPermissionDao extends JpaRepository<SysPermission,Long>{

    @Query(value = "SELECT permission_id FROM sys_permission_role WHERE id = ?",nativeQuery = true)
    String findAllById(long id);

//    @Query(value = "SELECT * FROM sys_permission_role WHERE id in ( ? )",nativeQuery = true)
    List<SysPermission> findByIdIn(Collection ids);


}
