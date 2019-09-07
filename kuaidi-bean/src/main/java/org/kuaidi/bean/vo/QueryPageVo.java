package org.kuaidi.bean.vo;

import java.io.Serializable;

public class QueryPageVo implements Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = 2330154022660722715L;
	private Integer page = 1;
    private Integer limit = 10;
    private Integer id;
    private String info1; //扩展字段1
    private String info2; //扩展字段2
    private	String recipientname;
    private String destinationname;
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getInfo1() {
        return info1;
    }


    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }



	public String getRecipientname() {
		return recipientname;
	}

	public void setRecipientname(String recipientname) {
		this.recipientname = recipientname;
	}

	public String getDestinationname() {
		return destinationname;
	}

	public void setDestinationname(String destinationname) {
		this.destinationname = destinationname;
	}

	@Override
	public String toString() {
		return "QueryPageVo [page=" + page + ", limit=" + limit + ", id=" + id + ", recipientname=" + recipientname
				+ ", destinationname=" + destinationname + "]";
	}





}
