package org.kuaidi.bean.maintainance;

import java.io.Serializable;

public class EforcesProblemcause implements Serializable {

    private static final long serialVersionUID = -465721870145538508L;

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
        return "EforcesProblemcause{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}