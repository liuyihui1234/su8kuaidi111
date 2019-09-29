package org.kuaidi.bean.vo;


import java.util.List;

import org.kuaidi.bean.Config;

/**
 * @Author : zz-gjw
 * @Date : 2019/3/5 15:01
 * @Description:
 */
public class ResultUtil {
    public static ResultVo exec(boolean istrue, String msg, Object data) {
        ResultVo resultVo = new ResultVo();
        if (istrue) {
            resultVo.setCode(Config.OK);
        } else {
            resultVo.setCode(Config.ERROR);
        }
        resultVo.setMsg(msg);
        resultVo.setData(data);
        return resultVo;
    }

    public static <T> PageVo<T> exec(int page, int size, long count, List<T> data) {
        PageVo pageVo = new PageVo();
      //  if (count > 0) {
            pageVo.setCode(1);
      //  } else {
      //      pageVo.setCode(1);
       // }
        pageVo.setPage(page);
        pageVo.setSize(size);
        pageVo.setCount(count);
        if (count > 0&&size>0) {
            pageVo.setTotalpage((int) (count % size == 0 ? count / size : count / size + 1));
        } else {
            pageVo.setTotalpage(0);
        }
        pageVo.setData(data);
        pageVo.setMsg("");
        return pageVo;
    }

    public static <T> PageVo<T> exec(int page, int size, long count, String msg, List<T> data) {
        PageVo pageVo = new PageVo();
        if (count > 0) {
            pageVo.setCode(1);
        } else {
            pageVo.setCode(0);
        }
        pageVo.setPage(page);
        pageVo.setSize(size);
        pageVo.setCount(count);
        pageVo.setMsg(msg);
        pageVo.setTotalpage((int)(count%size==0?count/size:count/size+1));
        if (count > 0) {
            pageVo.setTotalpage((int) (count % size == 0 ? count / size : count / size + 1));
        } else {
            pageVo.setTotalpage(0);
        }
        pageVo.setData(data);
        return pageVo;
    }
}
