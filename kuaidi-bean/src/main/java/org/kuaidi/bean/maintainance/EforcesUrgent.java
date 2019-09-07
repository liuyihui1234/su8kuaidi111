package org.kuaidi.bean.maintainance;

import java.io.Serializable;
//业务类型
public class EforcesUrgent implements Serializable {
    private static final long serialVersionUID = 282208810228224769L;
    private Integer id;

    private String name;

    private Integer price;

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "EforcesUrgent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}