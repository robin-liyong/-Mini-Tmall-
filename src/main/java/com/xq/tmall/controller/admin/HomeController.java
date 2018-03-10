package com.xq.tmall.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xq.tmall.entity.Admin;
import com.xq.tmall.entity.Category;
import com.xq.tmall.entity.Product;
import com.xq.tmall.service.AdminService;
import com.xq.tmall.service.CategoryService;
import com.xq.tmall.service.ProductService;
import com.xq.tmall.util.PageUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * 后台主页
 */
@Controller
public class HomeController {
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    @Resource(name = "adminService")
    private AdminService adminService;
    @Resource(name = "categoryService")
    private CategoryService categoryService;
    @Resource(name = "productService")
    private ProductService productService;

    //转到后台主页
    @RequestMapping("admin")
    public String goToPage(HttpSession session,Map<String, Object> map){
        logger.info("验证管理权限");
        Object o = session.getAttribute("adminId");
        if(o==null){
            logger.info("无管理权限，返回管理员登陆页");
            return "redirect:/admin/login";
        } else {
            logger.info("权限验证成功，管理员ID：{}",o);
        }

        logger.info("获取管理员信息");
        Admin admin = adminService.get(null,Integer.parseInt(o.toString()));
        map.put("admin",admin);

        logger.info("转到后台主页");
        return "admin/homePage";
    }

    //转到首页管理页
    @RequestMapping("admin/home")
    public String goHomeManagePage(HttpSession session,Map<String, Object> map){
        logger.info("验证管理权限");
        Object o = session.getAttribute("adminId");
        if(o==null){
            logger.info("无管理权限，返回管理员登陆页");
            return "redirect:/admin/login";
        } else {
            logger.info("权限验证成功，管理员ID：{}",o);
        }

        logger.info("转到首页管理页");
        return "admin/include/homeManagePage";
    }

    //转到产品管理页
    @RequestMapping("admin/product")
    public String goProductManagePage(HttpSession session,Map<String, Object> map) {
        logger.info("验证管理权限");
        Object o = session.getAttribute("adminId");
        if(o==null){
            logger.info("无管理权限，返回管理员登陆页");
            return "redirect:/admin/login";
        } else {
            logger.info("权限验证成功，管理员ID：{}",o);
        }

        logger.info("获取产品种类列表");
        List<Category> categoryList = categoryService.getList(null,null);
        map.put("categoryList",categoryList);
        logger.info("获取前10条产品列表");
        List<Product> productList = productService.getList(null,null,null,new PageUtil(1,10));
        map.put("productList",productList);
        logger.info("获取产品总数量");
        Integer productCount = productService.getTotal(null,null);
        map.put("productCount",productCount);

        logger.info("转到产品管理页");
        return "admin/include/productManagePage";
    }

    //转到分类管理页
    @RequestMapping("admin/category")
    public String goCategoryManagePage(HttpSession session,Map<String, Object> map){
        logger.info("验证管理权限");
        Object o = session.getAttribute("adminId");
        if(o==null){
            logger.info("无管理权限，返回管理员登陆页");
            return "redirect:/admin/login";
        } else {
            logger.info("权限验证成功，管理员ID：{}",o);
        }

        logger.info("转到分类管理页");
        return "admin/include/categoryManagePage";
    }

    //转到用户管理页
    @RequestMapping("admin/user")
    public String goUserManagePage(HttpSession session,Map<String, Object> map){
        logger.info("验证管理权限");
        Object o = session.getAttribute("adminId");
        if(o==null){
            logger.info("无管理权限，返回管理员登陆页");
            return "redirect:/admin/login";
        } else {
            logger.info("权限验证成功，管理员ID：{}",o);
        }

        logger.info("转到用户管理页");
        return "admin/include/userManagePage";
    }

    //转到订单管理页
    @RequestMapping("admin/order")
    public String goProductOrderManagePage(HttpSession session,Map<String, Object> map){
        logger.info("验证管理权限");
        Object o = session.getAttribute("adminId");
        if(o==null){
            logger.info("无管理权限，返回管理员登陆页");
            return "redirect:/admin/login";
        } else {
            logger.info("权限验证成功，管理员ID：{}",o);
        }

        logger.info("转到订单管理页");
        return "admin/include/productOrderManagePage";
    }

    //转到账户管理页
    @RequestMapping("admin/account")
    public String goAccountManagePage(HttpSession session,Map<String, Object> map){
        logger.info("验证管理权限");
        Object o = session.getAttribute("adminId");
        if(o==null){
            logger.info("无管理权限，返回管理员登陆页");
            return "redirect:/admin/login";
        } else {
            logger.info("权限验证成功，管理员ID：",o);
        }

        logger.info("转到账户管理页");
        return "admin/include/accountManagePage";
    }

    //按多条件查询产品-AJAX
    @ResponseBody
    @RequestMapping(value = "admin/product/search",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getProductBySearch(Product product, @RequestParam(value = "product_isEnabled_array",required = false) Byte[] product_isEnabled_array) throws UnsupportedEncodingException {
        //如果产品状态全选，则忽略该条件
        if(product_isEnabled_array != null) {
            product_isEnabled_array = product_isEnabled_array.length >= 3 ? null : product_isEnabled_array;
        }
        //解决中文乱码
        if(product.getProduct_name() != null && !product.getProduct_name().equals("")){
            product.setProduct_name(URLDecoder.decode(product.getProduct_name(),"UTF-8"));
        }

        JSONObject object = new JSONObject();
        logger.info("按多条件获取产品列表");
        List<Product> productList = productService.getList(product, product_isEnabled_array, null, new PageUtil(1, 10));
        object.put("productList", JSONArray.parseArray(JSON.toJSONString(productList)));
        logger.info("按多条件获取产品总数量");
        Integer productCount = productService.getTotal(product, product_isEnabled_array);
        object.put("productCount", productCount);

        return object.toJSONString();
    }
}
