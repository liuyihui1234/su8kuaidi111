package org.kuaidi.bean.maintainance;

import java.io.Serializable;
//运输方式
public class EforcesTransportmode implements Serializable {
    private static final long serialVersionUID = 8621501389438936188L;
    private Integer id;

    private String name;

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

    @Override
    public String toString() {
        return "EforcesTransportmode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}