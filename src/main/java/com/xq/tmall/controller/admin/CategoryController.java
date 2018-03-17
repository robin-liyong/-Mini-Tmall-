package com.xq.tmall.controller.admin;

import com.xq.tmall.entity.Category;
import com.xq.tmall.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 后台管理-分类页
 */
@Controller
public class CategoryController {
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    @Resource(name = "categoryService")
    private CategoryService categoryService;

    //转到后台管理-分类页
    @RequestMapping("admin/category")
    public String goToPage(HttpSession session, Map<String, Object> map){
        logger.info("验证管理权限");
        Object o=isLogin(session);
        if (o==null){
            return null;
        }

        logger.info("获取前10条分类信息列表");
        List<Category> categoryList = categoryService.getList(null,null);
        map.put("categoryList",categoryList);

        logger.info("转到后台管理-分类页");
        return "admin/include/categoryManagePage";
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
