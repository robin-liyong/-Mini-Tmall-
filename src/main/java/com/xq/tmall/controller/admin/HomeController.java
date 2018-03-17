package com.xq.tmall.controller.admin;

import com.xq.tmall.entity.Admin;
import com.xq.tmall.service.AdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 后台管理-主页
 */
@Controller
public class HomeController {
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    @Resource(name = "adminService")
    private AdminService adminService;

    //转到后台管理-主页
    @RequestMapping("admin")
    public String goToPage(HttpSession session,Map<String, Object> map,@RequestParam(value = "goHomeByAjax",required = false)boolean goHomeByAjax){
        logger.info("验证管理权限");
        Object o=isLogin(session);
        if (o==null){
            return "redirect:admin/login";
        }
        logger.info("获取管理员信息");
        Admin admin = adminService.get(null,Integer.parseInt(o.toString()));
        map.put("admin",admin);

        if(goHomeByAjax){
            logger.info("转到后台管理-主页-ajax方式");
            return "admin/include/homeManagePage";
        }
        logger.info("转到后台管理-主页");
        return "admin/homePage";
    }

    //验证权限
    private Object isLogin(HttpSession session){
        Object o = session.getAttribute("adminId");
        if(o==null){
            logger.info("无管理权限，返回管理员登陆页");
        } else {
            logger.info("权限验证成功，管理员ID：{}",o);
        }
        return o;
    }
}
