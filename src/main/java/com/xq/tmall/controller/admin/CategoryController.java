package com.xq.tmall.controller.admin;

import com.xq.tmall.controller.BaseController;
import com.xq.tmall.entity.Category;
import com.xq.tmall.service.CategoryService;
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
public class CategoryController extends BaseController{
    @Resource(name = "categoryService")
    private CategoryService categoryService;

    //转到后台管理-分类页-ajax
    @RequestMapping("admin/category")
    public String goToPage(HttpSession session, Map<String, Object> map){
        logger.info("检查管理员权限");
        Object adminId = checkAdmin(session);
        if(adminId == null){
            return null;
        }

        logger.info("获取前10条分类信息列表");
        List<Category> categoryList = categoryService.getList(null,null);
        map.put("categoryList",categoryList);

        logger.info("转到后台管理-分类页-ajax方式");
        return "admin/include/categoryManagePage";
    }
}
