package cn.ffcs.domain;

import javax.persistence.*;

/**
 * Created by MәӧωρaЯsε on 2017/5/5.
 */
@Entity
@Table(name = "sys_role_user")
public class SysRoleUser {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private java.lang.Integer id;
    //角色ID
    @Column(name = "sys_role_id")
    private java.lang.Integer roleId;

    //用户ID
    @Column(name = "sys_user_id")
    private java.lang.Integer userId;

    public SysRoleUser(int roleId,int userId ){
        this.roleId = roleId;
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}
