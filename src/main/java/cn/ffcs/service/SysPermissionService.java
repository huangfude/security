package cn.ffcs.service;

import cn.ffcs.domain.SysPermission;
import cn.ffcs.model.PermissionModel;

import java.util.List;
import java.util.Map;

/**
 * Created by MәӧωρaЯsε on 2017/5/5.
 *
 */
public interface SysPermissionService {

    /**
     * 获得某个用户的权限
     * @param id 用户ID
     * @return list
     */
    List<SysPermission> getPermissionByUserid(long id);

    /**
     *
     * @param id
     * @return list
     */
    List<Integer> findAllById(long id);

    /**
     *
     * @param permissionModels
     * @return map
     */

    Map<Integer,PermissionModel> pListToMap(List<PermissionModel> permissionModels);

}
