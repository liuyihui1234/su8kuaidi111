package org.kuaidi.bean.domain;

import java.io.Serializable;
import java.util.List;

public class EforcesTreeMenus2 implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1351676896577097120L;

    private Integer id;

    private String title;


    private List<EforcesTreeMenus2> children;

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

    public List<EforcesTreeMenus2> getChildren() {
        return children;
    }

    public void setChildren(List<EforcesTreeMenus2> children) {
        this.children = children;
    }
}