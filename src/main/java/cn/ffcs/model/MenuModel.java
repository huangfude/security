package cn.ffcs.model;

import java.util.List;
import java.util.Map;

/**
 * @author MәӧωρaЯsε
 * @date 2017/6/26.
 */
public class MenuModel {

    private Map<Integer,PermissionModel> permissionModelMap;

    public Map<Integer, PermissionModel> getPermissionModelMap() {
        return permissionModelMap;
    }

    public void setPermissionModelMap(Map<Integer, PermissionModel> permissionModelMap) {
        this.permissionModelMap = permissionModelMap;
    }

    public void addPermissionModel(int id,PermissionModel permissionModel){
        permissionModelMap.put(id,permissionModel);
    }

    public void removePermissionModel(int id){
        permissionModelMap.remove(id);
    }
}
