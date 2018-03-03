package com.xq.tmall.controller.admin;

import com.xq.tmall.entity.Admin;
import com.xq.tmall.entity.Category;
import com.xq.tmall.entity.Product;
import com.xq.tmall.service.AdminService;
import com.xq.tmall.service.CategoryService;
import com.xq.tmall.service.ProductService;
import com.xq.tmall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 后台主页
 */
@Controller
public class HomeController {

    @Resource(name = "adminService")
    private AdminService adminService;
    @Resource(name = "categoryService")
    private CategoryService categoryService;
    @Resource(name = "productService")
    private ProductService productService;

    //转到主页
    @RequestMapping("admin")
    public String goToPage(HttpSession session,Map<String, Object> map){
        //获取id
        Object o = session.getAttribute("adminId");
        if(o==null){
            //重定向至登录页
            return "redirect:/admin/login";
        }
        //根据id获取管理员信息
        Admin admin = adminService.get(null,Integer.parseInt(o.toString()));
        map.put("admin",admin);

        return "/admin/homePage";
    }

    //异步请求首页管理页面
    @ResponseBody
    @RequestMapping("admin/home_manage")
    public ModelAndView goHomeManage(ModelAndView modelAndView){
        modelAndView.setViewName("/admin/include/homeManagePage");
        return modelAndView;
    }

    //异步请求产品管理页面
    @ResponseBody
    @RequestMapping("admin/product_manage")
    public ModelAndView goProductManage(ModelAndView modelAndView) {
        //获取产品分类列表
        List<Category> categoryList = categoryService.getList(null,null);
        //获取产品列表
        List<Product> productList = productService.getList(null,null,new PageUtil(1,10));
        //获取产品总数量
        Integer productCount = productService.getTotal(null);
        modelAndView.addObject("categoryList",categoryList);
        modelAndView.addObject("productList",productList);
        modelAndView.addObject("productCount",productCount);

        modelAndView.setViewName("/admin/include/productManagePage");
        return modelAndView;
    }
}
