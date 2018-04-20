package com.xq.tmall.controller.admin;

import com.xq.tmall.controller.BaseController;
import com.xq.tmall.entity.Admin;
import com.xq.tmall.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 后台管理-主页
 */
@Controller
public class AdminHomeController extends BaseController {
    @Resource(name = "adminService")
    private AdminService adminService;

    //转到后台管理-主页
    @RequestMapping("admin")
    public String goToPage(HttpSession session,Map<String, Object> map){
        logger.info("检查管理员权限");
        Object adminId = checkAdmin(session);
        if(adminId == null){
            return "redirect:/admin/login";
        }

        logger.info("获取管理员信息");
        Admin admin = adminService.get(null,Integer.parseInt(adminId.toString()));
        map.put("admin",admin);

        logger.info("转到后台管理-主页");
        return "admin/homePage";
    }

    //转到后台管理-主页-ajax
    @RequestMapping("admin/home")
    public String goToPageByAjax(HttpSession session,Map<String, Object> map){
        logger.info("检查管理员权限");
        Object adminId = checkAdmin(session);
        if(adminId == null){
            return null;
        }

        logger.info("转到后台管理-主页-ajax方式");
        return "admin/homeManagePage";
    }
}
