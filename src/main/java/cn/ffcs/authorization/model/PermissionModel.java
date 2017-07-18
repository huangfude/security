package cn.ffcs.authorization.model;

import cn.ffcs.domain.SysPermission;

import java.util.List;

/**
 *
 * Created by MәӧωρaЯsε on 2017/5/2.
 */
public class PermissionModel {

    private String sysPermissions;

    private String token;

    public PermissionModel(String token, String sysPermissions) {
        this.sysPermissions = sysPermissions;
        this.token = token;
    }

    public String getSysPermissions() {
        return sysPermissions;
    }

    public void setSyspermissions(String sysPermissions) {
        this.sysPermissions = sysPermissions;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}