package com.xq.tmall.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.xq.tmall.entity.Admin;
import com.xq.tmall.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 管理员登录页
 */
@Controller
public class LoginController {
    @Resource(name = "adminService")
    private AdminService adminService;

    //转到登录页
    @RequestMapping("admin/login")
    public String goToPage(){
        return "admin/loginPage";
    }

    //异步登陆验证
    @ResponseBody
    @RequestMapping(value = "admin/login/{username}",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String checkLogin(HttpSession session, @PathVariable String username, @RequestParam String password) {
        //根据用户名和密码获取管理员信息
        Admin admin = adminService.login(username,password);
        JSONObject object = new JSONObject();
        if(admin == null){
            object.put("success",false);
        } else {
            //在Session中存储id
            session.setAttribute("adminId",admin.getAdmin_id());
            object.put("success",true);
        }
        return object.toJSONString();
    }

    //异步获取头像
    @ResponseBody
    @RequestMapping(value = "admin/login/{username}",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getAdminProfilePicture(@PathVariable("username") String username){
        //根据用户名获取管理员信息
        Admin admin = adminService.get(username,null);
        JSONObject object = new JSONObject();
        if(admin == null){
            object.put("success",false);
        } else {
            object.put("success",true);
            object.put("srcString",admin.getAdmin_profile_picture_src());
        }
        return object.toJSONString();
    }
}
