package com.xq.tmall.controller.fore;

import com.xq.tmall.controller.BaseController;
import com.xq.tmall.entity.Category;
import com.xq.tmall.entity.Product;
import com.xq.tmall.entity.User;
import com.xq.tmall.service.CategoryService;
import com.xq.tmall.service.ProductImageService;
import com.xq.tmall.service.ProductService;
import com.xq.tmall.service.UserService;
import com.xq.tmall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 前台天猫-主页
 */
@Controller
public class ForeHomeController extends BaseController {
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name="categoryService")
    private CategoryService categoryService;
    @Resource(name="productService")
    private ProductService productService;
    @Resource(name="productImageService")
    private ProductImageService productImageService;

    //转到前台天猫-主页
    @RequestMapping("/")
    public String goToPage(HttpSession session, Map<String, Object> map) {
        logger.info("检查用户是否登录");
        Object userId = checkUser(session);
        if (userId != null) {
            logger.info("获取用户信息");
            User user = userService.get(Integer.parseInt(userId.toString()));
            map.put("user", user);
        }
        logger.info("获取产品分类列表");
        List<Category> categoryList = categoryService.getList(null,null);
        logger.info("获取每个分类下的一组复杂产品数据");
       for(Category category : categoryList){
           List<List<Product>> productList = new ArrayList<>(8);
           Integer category_id = category.getCategory_id();
           for (int i = 1; i <= 8; i++) {
               logger.info("获取分类id为{}的产品集合");
               List<Product> products = productService.getList(new Product().setProduct_category(new Category().setCategory_id(category_id)), null, null, new PageUtil(i, 8));
               if (i == 1 && products != null) {
                   for(Product product : products){
                       Integer product_id = product.getProduct_id();
                       productImageService.getList(product_id,(byte)0,new PageUtil(0,1));
                   }
               }
               productList.add(products);
           }
           category.setProductList(productList);
       }
        map.put("categoryList",categoryList);
        logger.info("转到后台管理-主页");
        return "fore/homePage";
    }
}
