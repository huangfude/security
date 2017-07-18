package cn.ffcs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by MәӧωρaЯsε on 2017/5/2.
 */
@Entity
@Table(name = "sys_role")
public class SysRole {
    @Id
    @Column(name = "id")
    private java.lang.Integer id;
    @Column(name = "name")
    private java.lang.String name;

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

}
