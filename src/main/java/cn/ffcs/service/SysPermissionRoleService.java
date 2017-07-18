package cn.ffcs.service;

import cn.ffcs.domain.SysPermission;

import java.util.List;

/**
 * Created by MәӧωρaЯsε on 2017/5/5.
 *
 */
public interface SysPermissionRoleService {

    /**
     * 更新角色权限
     * @param ids 权限id (1,2,3,4,5,6)
     * @param roleId 角色id
     */

    void updatePermissionByRoleid(String ids,int roleId);

    /**
     * 获得某个角色的权限
     * @param roleId 角色id
     * @return list
     */
    List<SysPermission> getRolePermission(int roleId);

}
