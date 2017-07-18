package cn.ffcs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * Created by MeowParse on 2017/5/2.
 */
@Entity
@Table(name = "sys_permission")
public class SysPermission {

    @Id
    @Column(name = "id")
    private java.lang.Integer id;
    //权限名称
    @Column(name = "name")
    private java.lang.String name;

    //权限描述
    @Column(name = "description")
    private java.lang.String description;

    //授权链接
    @Column(name = "url")
    private java.lang.String url;

    //父节点id
    @Column(name = "pid")
    private java.lang.Integer pid;

    //请求方法
    @Column(name = "method")
    private java.lang.String method;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public void setDescritpion(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}
