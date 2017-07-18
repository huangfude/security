package cn.ffcs.service;

import cn.ffcs.model.RoleModel;

import java.util.List;

/**
 * Created by MәӧωρaЯsε on 2017/5/5.
 *
 */
public interface SysRoleUserService {

    /**
     * 删除角色关联
     * @param userId 用户ID
     */

    void deleteByUserid(int userId);

    /**
     * 更新角色用户关联
     * @return list
     */
    List<RoleModel> updateRoleUser(String roleIds,int userId,int currentUserId);

    /**
     * 获得用户拥有的角色
     * @param userId
     * @return list
     */
    List<RoleModel> getUserRoles(int userId,int cUserId);

}
