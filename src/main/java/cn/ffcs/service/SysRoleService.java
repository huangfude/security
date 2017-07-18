package cn.ffcs.service;


import cn.ffcs.model.PermissionModel;

import java.util.List;

/**
 * Created by MәӧωρaЯsε on 2017/6/21.
 *
 */
public interface SysRoleService {

    /**
     *
     * @param roleId  角色ID
     * @return list
     */
    List<PermissionModel> getRolePermission(int roleId);

}
