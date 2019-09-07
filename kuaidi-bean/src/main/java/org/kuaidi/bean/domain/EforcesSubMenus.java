package org.kuaidi.bean.domain;

import java.io.Serializable;
import java.util.List;

public class EforcesSubMenus implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = -1351676896577097120L;
    private Integer id;

    private String name;

    private String component;

    private String path;

    private String actionname;

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

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getActionname() {
        return actionname;
    }

    public void setActionname(String actionname) {
        this.actionname = actionname;
    }
}