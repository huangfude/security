package cn.ffcs.model;


import javax.persistence.Column;

/**
 *
 * Created by MәӧωρaЯsε on 2017/6/17.
 */
public class RoleModel {

    @Column(name = "sys_role_id")
    private java.lang.Integer sysRoleId;
    @Column(name = "name")
    private java.lang.String roleName;

    private boolean checked = false;

    public RoleModel(int sysRoleId,String roleName){
        this.sysRoleId = sysRoleId;
        this.roleName = roleName;
    }

    public int getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(int sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean equals(RoleModel roleModel){
        return this.sysRoleId == roleModel.getSysRoleId() && this.roleName.equals(roleModel.getRoleName());
    }

}
