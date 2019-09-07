package org.kuaidi.bean.maintainance;

import java.io.Serializable;
import java.util.Date;

public class EforcesProductcategory implements Serializable {

    private static final long serialVersionUID = 1585142297139731433L;

    private Integer id;

    private String name;

    private Integer pid;

    private Date createtime;

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
        this.name = name == null ? null : name.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "EforcesProductcategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", createtime=" + createtime +
                '}';
    }
}