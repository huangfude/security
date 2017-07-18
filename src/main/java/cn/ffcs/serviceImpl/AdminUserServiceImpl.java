package cn.ffcs.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.ffcs.authorization.manager.PermissionManager;
import cn.ffcs.authorization.manager.TokenManager;
import cn.ffcs.authorization.model.TokenModel;
import cn.ffcs.domain.SysPermission;
import cn.ffcs.domain.SysUser;
import cn.ffcs.service.AdminUserService;
import cn.ffcs.service.SysPermissionService;
import cn.ffcs.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MәӧωρaЯsε on 2017/6/17.
 *
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private PermissionManager permissionManager;


    @Override
    public JSONObject loginToken(SysUser sysUser) {

        //删除旧token存储的内容
        String token = tokenManager.getUserToken(sysUser.getId().intValue());
        if(token != null){
            permissionManager.deletePermission(token);
        }

        TokenModel model = tokenManager.createToken(sysUser.getId().intValue());
        //获得用户权限
        List<SysPermission> sysPermissions = sysPermissionService.getPermissionByUserid(sysUser.getId());
        String sysPermissionsString =  JSON.toJSON(sysPermissions).toString();//转化为字符串
        permissionManager.createPermission(model.getToken(),sysPermissionsString);
        //拼装json
        JSONObject jsonModel = new JSONObject();
        jsonModel.put("permission",sysPermissions);
        jsonModel.put("token",model);

        return jsonModel;
    }
}
