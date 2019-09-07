package org.kuaidi.bean.vo;

import java.util.List;

/**
 * @Author : zz-gjw
 * @Date : 2019/3/5 14:58
 * @Description:
 */
public class PageVo<T> {
    private long count;
    private int page;
    private int size;
    private int totalpage;
    private int code;
    private String msg ;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    
    @Override
	public String toString() {
		return "PageVo [count=" + count + ", page=" + page + ", size=" + size + ", totalpage=" + totalpage + ", code="
				+ code + ", data=" + data + "]";
	}

	public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    private List<T> data;
}
