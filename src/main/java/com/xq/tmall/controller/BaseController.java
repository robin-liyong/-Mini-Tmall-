package com.xq.tmall.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

/**
 * 基控制器
 */
public class BaseController {
    //log4j2
    protected Logger logger = LogManager.getLogger(BaseController.class);

    //获取管理员信息
    protected Object checkAdmin(HttpSession session){
        Object o = session.getAttribute("adminId");
        if(o==null){
            logger.info("无管理权限，返回管理员登陆页");
            return null;
        }
        logger.info("当前登录管理员ID：{}",o);
        return o;
    }

    //检查用户是否登录
    protected Object checkUser(HttpSession session){
        Object o = session.getAttribute("userId");
        if(o==null){
            logger.info("用户未登录");
            return null;
        }
        logger.info("用户已登录，用户ID：{}", o);
        return o;
    }
}
