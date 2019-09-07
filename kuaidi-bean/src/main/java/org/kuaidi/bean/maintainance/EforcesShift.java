package org.kuaidi.bean.maintainance;

import java.io.Serializable;
 //班次管理
public class EforcesShift implements Serializable {

    private static final long serialVersionUID = 2728379056105623159L;

    private Integer id;

    private String number;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "EforcesShift{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}