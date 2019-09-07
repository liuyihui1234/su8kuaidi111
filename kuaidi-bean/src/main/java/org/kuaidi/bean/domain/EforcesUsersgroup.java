package org.kuaidi.bean.domain;

import java.io.Serializable;

/**
 * eforces_usersgroup
 * @author 
 */
public class EforcesUsersgroup implements Serializable {
    private Integer id;

    private String groupname;

    private String groupinfo;

    private Integer issuperadmin;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getGroupinfo() {
        return groupinfo;
    }

    public void setGroupinfo(String groupinfo) {
        this.groupinfo = groupinfo;
    }

    public Integer getIssuperadmin() {
        return issuperadmin;
    }

    public void setIssuperadmin(Integer issuperadmin) {
        this.issuperadmin = issuperadmin;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        EforcesUsersgroup other = (EforcesUsersgroup) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGroupname() == null ? other.getGroupname() == null : this.getGroupname().equals(other.getGroupname()))
            && (this.getGroupinfo() == null ? other.getGroupinfo() == null : this.getGroupinfo().equals(other.getGroupinfo()))
            && (this.getIssuperadmin() == null ? other.getIssuperadmin() == null : this.getIssuperadmin().equals(other.getIssuperadmin()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGroupname() == null) ? 0 : getGroupname().hashCode());
        result = prime * result + ((getGroupinfo() == null) ? 0 : getGroupinfo().hashCode());
        result = prime * result + ((getIssuperadmin() == null) ? 0 : getIssuperadmin().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", groupname=").append(groupname);
        sb.append(", groupinfo=").append(groupinfo);
        sb.append(", issuperadmin=").append(issuperadmin);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}