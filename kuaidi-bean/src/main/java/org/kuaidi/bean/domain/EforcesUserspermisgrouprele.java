package org.kuaidi.bean.domain;

import java.io.Serializable;

/**
 * eforces_userspermisgrouprele
 * @author 
 */
public class EforcesUserspermisgrouprele implements Serializable {
    private Integer id;

    private Integer permisid;

    private Integer groupid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPermisid() {
        return permisid;
    }

    public void setPermisid(Integer permisid) {
        this.permisid = permisid;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }
}