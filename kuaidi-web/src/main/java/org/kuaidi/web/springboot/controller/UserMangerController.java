package org.kuaidi.web.springboot.controller;


import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.web.springboot.dubboservice.UserMangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/web/usermanger/")
public class UserMangerController {

    @Autowired
    UserMangerService userMangerService;


    @RequestMapping("getallgroup")
    @CrossOrigin
    public PageVo getAllGroup(QueryPageVo page) {
        PageVo rst = null;
        rst = userMangerService.getAllGroup(page);
        return rst;
    }

    @RequestMapping("getgroupbyid")
    @CrossOrigin
    public ResultVo getGroupByid(Integer id) {
        ResultVo rst = null;
        rst = userMangerService.getGroupById(id);
        return rst;
    }

    @GetMapping("user")
    @CrossOrigin
    public PageVo getAllUser(QueryPageVo page) {
        PageVo rst = null;
        System.out.println(page);

        rst = userMangerService.getAllUser(page);
        return rst;
    }

    @RequestMapping("getUserById")
    @CrossOrigin
    public ResultVo getUserById(Integer id) {
        ResultVo rst = null;
        rst = userMangerService.getUserById(id);
        return rst;
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @PutMapping("user")
    @CrossOrigin
    public ResultVo updateUserById(EforcesUser user) {
        ResultVo rst = null;
        System.out.println(user);
        rst = userMangerService.updateUserById(user);
        return rst;
    }

    /**
     * 新增用户
     * @return
     */
    @PostMapping("user")
    @CrossOrigin
    public ResultVo saveUser(EforcesUser user) {
        ResultVo rst = null;
        rst = userMangerService.addSyetemUser(user);
        return rst;
    }

    /**
     * 删除用户
     * @return
     */
    @DeleteMapping("user")
    @CrossOrigin
    public ResultVo deleteUser(@RequestBody List<Integer> array) {
        ResultVo rst = null;
       rst = userMangerService.deleteUserByID(array);
        return rst;
    }




}
