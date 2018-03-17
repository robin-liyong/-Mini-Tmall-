package com.xq.tmall.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xq.tmall.entity.Category;
import com.xq.tmall.entity.Product;
import com.xq.tmall.service.CategoryService;
import com.xq.tmall.service.ProductService;
import com.xq.tmall.util.OrderUtil;
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
 * 后台管理-产品页
 */
@Controller
public class ProductController {
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    @Resource(name = "categoryService")
    private CategoryService categoryService;
    @Resource(name = "productService")
    private ProductService productService;

    //转到后台管理-产品页-ajax
    @RequestMapping("admin/product")
    public String goToPage(HttpSession session, Map<String, Object> map) {
        logger.info("检查管理员权限");
        Object adminId = checkAdmin(session);
        if(adminId == null){
            return null;
        }

        logger.info("获取产品种类列表");
        List<Category> categoryList = categoryService.getList(null, null);
        map.put("categoryList", categoryList);
        logger.info("获取前10条产品列表");
        List<Product> productList = productService.getList(null, null, null, new PageUtil(1, 10));
        map.put("productList", productList);
        logger.info("获取产品总数量");
        Integer productCount = productService.getTotal(null, null);
        map.put("productCount", productCount);

        logger.info("转到后台管理-产品页--ajax方式");
        return "admin/include/productManagePage";
    }

    //按条件查询产品-AJAX
    @ResponseBody
    @RequestMapping(value = "admin/product/search", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getProductBySearch(@RequestParam(value = "product_name", required = false) String product_name,
                                     @RequestParam(value = "category_id", required = false) Integer category_id,
                                     @RequestParam(value = "product_sale_price", required = false) Double product_sale_price,
                                     @RequestParam(value = "product_price", required = false) Double product_price,
                                     @RequestParam(value = "product_isEnabled_array", required = false) Byte[] product_isEnabled_array,
                                     @RequestParam(value = "orderBy",required = false) String orderBy,
                                     @RequestParam(value = "isDesc",required = false) Boolean isDesc) throws UnsupportedEncodingException {
        try {
            //移除不必要条件
            if (product_isEnabled_array != null && (product_isEnabled_array.length <= 0 || product_isEnabled_array.length >=3)) {
                product_isEnabled_array = null;
            }
            if (category_id != null && category_id == 0) {
                category_id = null;
            }
            //解决中文乱码：URLDecoder.decode(String,"UTF-8");
            if (product_name != null) {
                product_name = product_name.equals("") ? null : URLDecoder.decode(product_name, "UTF-8");
            }
            //封装数据
            Product product = new Product()
                    .setProduct_name(product_name)
                    .setProduct_category(new Category().setCategory_id(category_id))
                    .setProduct_price(product_price)
                    .setProduct_sale_price(product_sale_price);
            //排序
            OrderUtil orderUtil = null;
            if (orderBy != null && !orderBy.equals("")) {
                orderUtil = new OrderUtil(orderBy, isDesc);
            }
            JSONObject object = new JSONObject();
            logger.info("按条件获取前10条产品列表");
            List<Product> productList = productService.getList(product, product_isEnabled_array, orderUtil, new PageUtil(1, 10));
            object.put("productList", JSONArray.parseArray(JSON.toJSONString(productList)));
            logger.info("按条件获取产品总数量");
            Integer productCount = productService.getTotal(product, product_isEnabled_array);
            object.put("productCount", productCount);

            return object.toJSONString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //检查管理员权限
    private Object checkAdmin(HttpSession session){
        Object o = session.getAttribute("adminId");
        if(o==null){
            logger.info("无管理权限，返回管理员登陆页");
            return null;
        }
        logger.info("权限验证成功，管理员ID：{}",o);
        return o;
    }
}