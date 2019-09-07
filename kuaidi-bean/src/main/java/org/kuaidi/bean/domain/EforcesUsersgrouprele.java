package org.kuaidi.bean.domain;

import java.io.Serializable;

/**
 * eforces_usersgrouprele
 * @author 
 */
public class EforcesUsersgrouprele implements Serializable {
    private Integer id;

    private Integer userid;

    private String username;

    private Integer groupid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }


}