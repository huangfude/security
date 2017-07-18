package cn.ffcs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户数据的domain类
 * @author ScienJus
 * @date 2015/7/31.
 */
@Entity
@Table(name = "sys_user")
public class SysUser {
    //用户名
    @Column(name = "username")
    private java.lang.String username;

    //密码
    @Column(name = "password")
    private java.lang.String password;

    //用户id
    @Id
    @Column(name = "id")
    private java.lang.Long id;

    //昵称
    @Column(name = "nickname")
    private java.lang.String nickname;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
