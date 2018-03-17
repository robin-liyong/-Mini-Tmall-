package com.xq.tmall.controller.admin;

import com.xq.tmall.entity.Admin;
import com.xq.tmall.service.AdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 后台管理-账户页
 */
@Controller
public class AccountController {
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    @Resource(name = "adminService")
    private AdminService adminService;

    //转到后台管理-账户页
    @RequestMapping("admin/account")
    public String goToPage(HttpSession session, Map<String, Object> map){
        logger.info("验证管理权限");
        Object o=isLogin(session);
        if (o==null){
            return null;
        }

        logger.info("获取登录的管理员账户信息");
        Admin admin = adminService.get(null,Integer.parseInt(o.toString()));
        map.put("admin",admin);

        logger.info("转到后台管理-账户页");
        return "admin/include/accountManagePage";
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
