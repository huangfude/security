package cn.ffcs.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by MәӧωρaЯsε on 2017/6/21.
 *
 */
public class PermissionModel {

    private java.lang.Integer id;
    //权限名称
    private java.lang.String name;

    //权限描述
    private java.lang.String description;

    //父节点id
    private java.lang.Integer pid;

    private java.lang.Boolean checked = false;

    private List<PermissionModel> permissionModels = new ArrayList<>();

    public PermissionModel(int id,String name,String description,int pid){
        this.id = id;
        this.name = name;
        this.description = description;
        this.pid = pid;
    }

    public boolean equals(PermissionModel permissionModel){

        return this.id == permissionModel.getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<PermissionModel> getPermissionModels() {
        return permissionModels;
    }

    public void setPermissionModels(List<PermissionModel> permissionModels) {
        this.permissionModels = permissionModels;
    }

}
