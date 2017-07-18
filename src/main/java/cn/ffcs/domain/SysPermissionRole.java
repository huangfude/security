package cn.ffcs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by MәӧωρaЯsε on 2017/5/5.
 */
@Entity
@Table(name = "sys_permission_role")
public class SysPermissionRole {

        @Id
        @Column(name = "id")
        private java.lang.Integer id;
        //角色ID
        @Column(name = "role_id")
        private java.lang.Integer roleId;

        //拥有权限
        @Column(name = "permission_id")
        private java.lang.String permissionId;

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

        public String getPermissionId() {
            return permissionId;
        }

        public void setPermissionId(String permissionId) {
            this.permissionId = permissionId;
        }

}
