package cn.ffcs.authorization.manager;

import cn.ffcs.authorization.model.PermissionModel;
import cn.ffcs.domain.SysPermission;

import java.util.List;

/**
 *
 * Created by MәӧωρaЯsε on 2017/5/3.
 */
public interface PermissionManager {

    /**
     * 创建一个token关联上用户所拥有的权限
     * @param token 指定用户的id
     * @return 生成的权限
     */
    PermissionModel createPermission(String token, String sysPermissions);

    /**
     * 检查权限是否有效
     * @param  token url method
     * @return 是否有效
     */
    boolean checkPermission(String token,String url,String method);

    /**
     * 从字符串中解析token
     * @param authentication 加密后的字符串
     * @return
     */
    PermissionModel getPermission(String authentication);

    /**
     * 清除permission
     * @param token 登录用户的token
     */
    void deletePermission(String token);

}
