package org.kuaidi.web.springboot.webcontroller;

import java.util.List;
import org.kuaidi.bean.domain.EforcesUsersgroup;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.QueryPageVo;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.web.springboot.dubboservice.UserMangerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/groupmanager/")
public class UserGroupManage {
	
	@Autowired
    UserMangerService userMangerService;

    @RequestMapping("group")
    @CrossOrigin
    public PageVo getAllGroup(QueryPageVo page) {
        PageVo rst = null;
        rst = userMangerService.getAllGroup(page);
        return rst;
    }
    
    /**
     * 修改用户组
     * @param user
     * @return
     */
    @PutMapping("group")
    @CrossOrigin
    public ResultVo updateUserById(EforcesUsersgroup group) {
        ResultVo rst = null;
        rst = userMangerService.updateUserGroup(group);
        return rst;
    }

    /**
     * 新增用户
     * @return
     */
    @PostMapping("group")
    @CrossOrigin
    public ResultVo saveUser(EforcesUsersgroup group) {
        ResultVo rst = null;
        rst = userMangerService.addUserGroup(group);
        return rst;
    }

    /**
             * 删除用户
     * @return
     */
    @DeleteMapping("group")
    @CrossOrigin
    public ResultVo deleteUser(@RequestBody List<Integer> array) {
        ResultVo rst = null;
        rst = userMangerService.deleteUserGroupByID(array);
        return rst;
    }
    
    @RequestMapping("getgroupbyid")
    @CrossOrigin
    public ResultVo getGroupByid(Integer id) {
        ResultVo rst = null;
        rst = userMangerService.getGroupById(id);
        return rst;
    }

}
