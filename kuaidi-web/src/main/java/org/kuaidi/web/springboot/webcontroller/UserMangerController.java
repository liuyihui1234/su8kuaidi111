package org.kuaidi.web.springboot.webcontroller;


import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.web.springboot.dubboservice.UserMangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/web/usermanger/")
public class UserMangerController {

    @Autowired
    UserMangerService userMangerService;

   

    @GetMapping("user")
    @CrossOrigin
    public PageVo getAllUser(QueryPageVo page,EforcesUser record) {
        PageVo rst = null;
        System.err.println(record);
        rst = userMangerService.getAllUser(page,record);
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
