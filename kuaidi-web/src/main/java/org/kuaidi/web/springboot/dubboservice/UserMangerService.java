package org.kuaidi.web.springboot.dubboservice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.kuaidi.bean.domain.EforcesIncment;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.domain.EforcesUsersgroup;
import org.kuaidi.bean.domain.EforcesUsersgrouprele;
import org.kuaidi.bean.vo.*;
import org.kuaidi.iservice.IEforcesUserGroupService;
import org.kuaidi.iservice.IEforcesUserGrouproleService;
import org.kuaidi.iservice.UserService;
import org.kuaidi.utils.Md5Util;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMangerService {
    @Reference(version = "1.0.0")
    IEforcesUserGroupService iEforcesUserGroupService;
    @Reference(version = "1.0.0")
    private UserService userService;
    @Reference(version = "1.0.0")
    private IEforcesUserGrouproleService iEforcesUserGrouproleService;


    public PageVo getAllGroup(QueryPageVo page) {
        try {
            PageInfo<EforcesUsersgroup> info = iEforcesUserGroupService.selectAllIGroup(page.getPage(), page.getLimit());
            return ResultUtil.exec(info.getPageNum(),info.getSize(),info.getTotal(), info.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10 ,0, null);
        }
    }

    public ResultVo getGroupById(Integer id) {
        try {
            EforcesUsersgroup eforcesUsersgroups = iEforcesUserGroupService.selectByPrimaryKey(id);
            return ResultUtil.exec(true, "查询成功", eforcesUsersgroups);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "查询失败！", null);
        }
    }

    public PageVo getAllUser(QueryPageVo page,EforcesUser record) {
        try {
            PageInfo<EforcesUser> eforcesUsers = userService.selectAllUser(page.getPage(),page.getLimit(),record);
            return ResultUtil.exec(eforcesUsers.getPageNum(), eforcesUsers.getSize(),eforcesUsers.getTotal(), eforcesUsers.getList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(1, 10 ,0, null);
        }
    }


    public ResultVo addSyetemUser(EforcesUser user) {
        try {

            if(StringUtils.isEmpty(user.getPassword())){
                user.setPassword(Md5Util.encode("123456"));
            }else {
                user.setPassword(Md5Util.encode(user.getPassword()));
            }
            int i = userService.addUser(user);
            if(i>0){
                return ResultUtil.exec(true, "添加成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "添加失败！", null);
        }
        return ResultUtil.exec(false, "添加失败！", null);
    }


    public ResultVo deleteUserByID(List<Integer> list) {
        try {
            int i = userService.deleteByid(list);
            if(i>0){
                return ResultUtil.exec(true, "删除成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "删除失败！", null);
        }
        return ResultUtil.exec(false, "删除失败！", null);
    }

    public ResultVo getUserById(Integer id) {
        try {
            EforcesUser eforcesUsers = userService.selectUserById(id);
            eforcesUsers.setPassword("");
            return ResultUtil.exec(true, "查询成功", eforcesUsers);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "查询失败！", null);
        }
    }

    public ResultVo updateUserById(EforcesUser user) {
        try {
            List<EforcesUsersgrouprele> list = new ArrayList<>();
            if (StringUtils.isNotEmpty(user.getGroupid())) {
                String[] split = user.getGroupid().split(",");
                for (int i = 0; i < split.length; i++) {
                    EforcesUsersgrouprele role = new EforcesUsersgrouprele();
                    role.setUsername(user.getNumber());
                    role.setGroupid(Integer.parseInt(split[i]));
                    role.setUserid(user.getId());
                    list.add(role);
                }
            }
            int j = iEforcesUserGrouproleService.deleteByUserId(user.getId());
            int k = iEforcesUserGrouproleService.insertForeach(list);
            Integer inte = userService.updateUserInfo(user);
            String str = "";
            if (j > 0 && k > 0 && inte > 0) {
                str = "修改成功";
            }
            return ResultUtil.exec(true, str, null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "修改失败！", null);
        }
    }


    public ResultVo addUser(EforcesUser user) {
        try {
            Integer integer = userService.insertUserInfo(user);
            return ResultUtil.exec(true, "添加成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.exec(false, "查询失败！", null);
        }
    }




}
