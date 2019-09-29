package org.kuaidi.bean.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class EforcesPrice  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6660875370808542297L;

	private Integer id;

    private String fromprovinceid;

    private String toprovinceid;

    private BigDecimal firstweight;

    private BigDecimal firstprice;

    private BigDecimal continueweight;

    private BigDecimal continueprice;

    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromprovinceid() {
        return fromprovinceid;
    }

    public void setFromprovinceid(String fromprovinceid) {
        this.fromprovinceid = fromprovinceid == null ? null : fromprovinceid.trim();
    }

    public String getToprovinceid() {
        return toprovinceid;
    }

    public void setToprovinceid(String toprovinceid) {
        this.toprovinceid = toprovinceid == null ? null : toprovinceid.trim();
    }

    public BigDecimal getFirstweight() {
        return firstweight;
    }

    public void setFirstweight(BigDecimal firstweight) {
        this.firstweight = firstweight;
    }

    public BigDecimal getFirstprice() {
        return firstprice;
    }

    public void setFirstprice(BigDecimal firstprice) {
        this.firstprice = firstprice;
    }

    public BigDecimal getContinueweight() {
        return continueweight;
    }

    public void setContinueweight(BigDecimal continueweight) {
        this.continueweight = continueweight;
    }

    public BigDecimal getContinueprice() {
        return continueprice;
    }

    public void setContinueprice(BigDecimal continueprice) {
        this.continueprice = continueprice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}