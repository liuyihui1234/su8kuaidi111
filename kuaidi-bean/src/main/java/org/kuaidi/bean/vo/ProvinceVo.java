package org.kuaidi.bean.vo;

public class ProvinceVo {
    String provincecode;
    String citycode;
    String areacode;

    public String getProvincecode() {
        return provincecode;
    }

    public void setProvincecode(String provincecode) {
        this.provincecode = provincecode;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    @Override
    public String toString() {
        return "ProvinceVo{" +
                "provincecode='" + provincecode + '\'' +
                ", citycode='" + citycode + '\'' +
                ", areacode='" + areacode + '\'' +
                '}';
    }
}
