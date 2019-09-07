package org.kuaidi.bean.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class EforcesProduct implements Serializable {
    private static final long serialVersionUID = 4010243666507647383L;
    private Integer id;

    private String number;

    private String name;

    private String bigcate;

    private Integer big;

    private String smallcate;

    private Integer small;

    private String unit;

    private BigDecimal unitprice;

    private String specification;

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

    public String getBigcate() {
        return bigcate;
    }

    public void setBigcate(String bigcate) {
        this.bigcate = bigcate == null ? null : bigcate.trim();
    }

    public Integer getBig() {
        return big;
    }

    public void setBig(Integer big) {
        this.big = big;
    }

    public String getSmallcate() {
        return smallcate;
    }

    public void setSmallcate(String smallcate) {
        this.smallcate = smallcate == null ? null : smallcate.trim();
    }

    public Integer getSmall() {
        return small;
    }

    public void setSmall(Integer small) {
        this.small = small;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public BigDecimal getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }
}