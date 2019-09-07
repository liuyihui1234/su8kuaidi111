package org.kuaidi.bean.domain;

import java.io.Serializable;
import java.util.List;

public class EforcesTreeMenus implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1351676896577097120L;

    private Integer id;

    private String name;

    private String auth;

    private String url;

    private String path;

    private List<EforcesTreeMenus> subMenus;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

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

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<EforcesTreeMenus> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<EforcesTreeMenus> subMenus) {
        this.subMenus = subMenus;
    }
}