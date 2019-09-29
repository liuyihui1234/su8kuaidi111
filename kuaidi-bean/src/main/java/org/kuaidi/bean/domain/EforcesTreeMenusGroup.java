package org.kuaidi.bean.domain;

import java.io.Serializable;
import java.util.List;

public class EforcesTreeMenusGroup implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1351676896577097120L;

    private Integer id;

    private String title;

    private boolean superadmin;


    private List<EforcesTreeMenusGroup> children;


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

    public List<EforcesTreeMenusGroup> getChildren() {
        return children;
    }

    public void setChildren(List<EforcesTreeMenusGroup> children) {
        this.children = children;
    }

    public boolean isSuperadmin() {
        return superadmin;
    }

    public void setSuperadmin(boolean superadmin) {
        this.superadmin = superadmin;
    }
}