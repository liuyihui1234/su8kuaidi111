package org.kuaidi.bean.domain;

import java.io.Serializable;

public class EforcesDepartment implements Serializable {
    private static final long serialVersionUID = -3533403720111378220L;
    private Integer id;

    private String depaname;

    private Integer jiazhang;

    private String leaderidlist;

    private Integer incid;

    private String depanumber;

    private String depahelp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepaname() {
        return depaname;
    }

    public void setDepaname(String depaname) {
        this.depaname = depaname == null ? null : depaname.trim();
    }

    public Integer getJiazhang() {
        return jiazhang;
    }

    public void setJiazhang(Integer jiazhang) {
        this.jiazhang = jiazhang;
    }

    public String getLeaderidlist() {
        return leaderidlist;
    }

    public void setLeaderidlist(String leaderidlist) {
        this.leaderidlist = leaderidlist == null ? null : leaderidlist.trim();
    }

    public Integer getIncid() {
        return incid;
    }

    public void setIncid(Integer incid) {
        this.incid = incid;
    }

    public String getDepanumber() {
        return depanumber;
    }

    public void setDepanumber(String depanumber) {
        this.depanumber = depanumber == null ? null : depanumber.trim();
    }

    public String getDepahelp() {
        return depahelp;
    }

    public void setDepahelp(String depahelp) {
        this.depahelp = depahelp == null ? null : depahelp.trim();
    }
}