package com.xq.tmall.controller.fore;

import com.xq.tmall.controller.BaseController;
import com.xq.tmall.entity.Category;
import com.xq.tmall.entity.Product;
import com.xq.tmall.entity.Review;
import com.xq.tmall.entity.User;
import com.xq.tmall.service.*;
import com.xq.tmall.util.OrderUtil;
import com.xq.tmall.util.PageUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


public class ForeProductListController extends BaseController {
    @Resource(name = "productService")
    private ProductService productService;
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "categoryService")
    private CategoryService categoryService;
    @Resource(name = "productImageService")
    private ProductImageService productImageService;
    @Resource(name = "reviewService")
    private ReviewService reviewService;
    @Resource(name = "productOrderService")
    private ProductOrderService productOrderService;


    @RequestMapping(value = "product/{index}/{count}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String goToPage(Map<String, Object> map,
                           @PathVariable("index") Integer index/* 页数 */,
                           @PathVariable("count") Integer count/* 行数*/,
                           HttpSession session,
                           @RequestParam(value = "category_id", required = false) Integer category_id,
                           @RequestParam(value = "product_name", required = false) String product_name,
                           @RequestParam(required = false) String orderBy/* 排序字段 */,
                           @RequestParam(required = false, defaultValue = "true") Boolean isDesc/* 是否倒序 */) {
        logger.info("获取用户信息");
        User user = null;
        if (checkUser(session) != null) {
            user = userService.get((Integer) session.getAttribute("user_id"));
        }
        logger.info("获取分类对象");
        Category category;
        Product product;
        logger.info("整合产品信息");
        category = new Category()
                .setCategory_id(category_id);
        product = new Product()
                .setProduct_name(product_name)
                .setProduct_category(category);
        Review review = new Review()
                .setReview_product(product);
        OrderUtil orderUtil = null;
        if (orderBy != null) {
            logger.info("根据{}排序，是否倒序:{}", orderBy, isDesc);
            orderUtil = new OrderUtil(orderBy, isDesc);
        }
        logger.info("获取商品列表");
        List<Product> productList = productService.getList(product, new Byte[]{0, 2}, orderUtil, new PageUtil(index, count));
        if (productList != null) {
            for (Product p : productList) {
                p.setSingleProductImageList(productImageService.getList(p.getProduct_id(), (byte) 0, null));
                p.setReviewList(reviewService.getList(review, null));
                p.setProduct_sale_count(product.getProduct_sale_count());
                p.setProduct_category(categoryService.get(category_id));
            }
        }
        map.put("productList", productList);
        return "";
    }


}
