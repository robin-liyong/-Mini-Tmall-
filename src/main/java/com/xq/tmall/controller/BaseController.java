package com.xq.tmall.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

/**
 * 基控制器
 */
@Controller
public class BaseController {
    //log4j2
    protected Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    //检查管理员权限
    protected Object checkAdmin(HttpSession session){
        Object o = session.getAttribute("adminId");
        if(o==null){
            logger.info("无管理权限，返回管理员登陆页");
            return null;
        }
        logger.info("权限验证成功，管理员ID：{}",o);
        return o;
    }

    //检查用户是否登录
    protected Object checkUser(HttpSession session){
        Object o = session.getAttribute("userId");
        if(o==null){
            logger.info("未登录");
            return null;
        }
        logger.info("登录成功");
        return o;
    }

}
