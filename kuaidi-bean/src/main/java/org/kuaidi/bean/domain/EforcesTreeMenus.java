package org.kuaidi.bean.domain;

import java.io.Serializable;
import java.util.List;

public class EforcesTreeMenus implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1351676896577097120L;

    private Integer id;

    private String title;

    private String actionname;

    private List<EforcesTreeMenus> children;

    public String getActionname() {
        return actionname;
    }

    public void setActionname(String actionname) {
        this.actionname = actionname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<EforcesTreeMenus> getChildren() {
        return children;
    }

    public void setChildren(List<EforcesTreeMenus> children) {
        this.children = children;
    }
}