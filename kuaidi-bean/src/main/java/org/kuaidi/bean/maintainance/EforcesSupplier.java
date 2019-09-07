package org.kuaidi.bean.maintainance;

import java.io.Serializable;
//供应商列表
public class EforcesSupplier implements Serializable{

    private static final long serialVersionUID = 6513077106939021712L;
    private Integer id;

    private String name;

    private String number;

    private String tel;

    private String fax;

    private String weburl;

    private String email;

    private String nation;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl == null ? null : weburl.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    @Override
    public String toString() {
        return "EforcesSupplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", tel='" + tel + '\'' +
                ", fax='" + fax + '\'' +
                ", weburl='" + weburl + '\'' +
                ", email='" + email + '\'' +
                ", nation='" + nation + '\'' +
                '}';
    }
}