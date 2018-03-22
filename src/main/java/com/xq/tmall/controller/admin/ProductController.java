package com.xq.tmall.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xq.tmall.controller.BaseController;
import com.xq.tmall.entity.Category;
import com.xq.tmall.entity.Product;
import com.xq.tmall.service.CategoryService;
import com.xq.tmall.service.ProductImageService;
import com.xq.tmall.service.ProductService;
import com.xq.tmall.util.OrderUtil;
import com.xq.tmall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
public class ProductController extends BaseController{
    @Resource(name = "categoryService")
    private CategoryService categoryService;
    @Resource(name = "productService")
    private ProductService productService;
    @Resource(name = "productImageService")
    private ProductImageService productImageService;
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
    @RequestMapping(value = "admin/product/{index}/{count}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getProductBySearch(@RequestParam(required = false) String product_name/* 产品名称 */,
                                     @RequestParam(required = false) Integer category_id/* 产品类型ID */,
                                     @RequestParam(required = false) Double product_sale_price/* 产品最低价 */,
                                     @RequestParam(required = false) Double product_price/* 产品最高价 */,
                                     @RequestParam(required = false) Byte[] product_isEnabled_array/* 产品状态数组 */,
                                     @RequestParam(required = false) String orderBy/* 排序字段 */,
                                     @RequestParam(required = false,defaultValue = "true") Boolean isDesc/* 是否倒序 */,
                                     @PathVariable Integer index/* 页数 */,
                                     @PathVariable Integer count/* 行数 */) throws UnsupportedEncodingException {
        //移除不必要条件
        if (product_isEnabled_array != null && (product_isEnabled_array.length <= 0 || product_isEnabled_array.length >=3)) {
            product_isEnabled_array = null;
        }
        if (category_id != null && category_id == 0) {
            category_id = null;
        }
        if (product_name != null) {
            //如果为非空字符串则解决中文乱码：URLDecoder.decode(String,"UTF-8");
            product_name = product_name.equals("") ? null : URLDecoder.decode(product_name, "UTF-8");
        }
        if(orderBy != null && orderBy.equals("")){
            orderBy = null;
        }
        //封装查询条件
        Product product = new Product()
                .setProduct_name(product_name)
                .setProduct_category(new Category().setCategory_id(category_id))
                .setProduct_price(product_price)
                .setProduct_sale_price(product_sale_price);
        OrderUtil orderUtil = null;
        if (orderBy != null) {
            logger.info("根据{}排序，是否倒序:{}",orderBy,isDesc);
            orderUtil = new OrderUtil(orderBy, isDesc);
        }

        JSONObject object = new JSONObject();
        logger.info("按条件获取第{}页的{}条产品",index,count);
        List<Product> productList = productService.getList(product, product_isEnabled_array, orderUtil, new PageUtil(index, count));
        object.put("productList", JSONArray.parseArray(JSON.toJSONString(productList)));
        logger.info("按条件获取产品总数量");
        Integer productCount = productService.getTotal(product, product_isEnabled_array);
        object.put("productCount", productCount);

        return object.toJSONString();
    }

    //转到后台产品展示页
    @RequestMapping(value="admin/product/{pid}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getProductByPid(HttpSession session,@RequestParam(required = false) Integer pid,Map<String, Object> map){
        logger.info("检查管理员权限");
        Object adminld = checkAdmin(session);
        if(adminld == null){
            return null;
        }
        logger.info("根据pid获取单个产品信息");
        Product product = productService.get(pid);
        logger.info("根据产品category_id获取产品分类的信息");
        Integer category_id = product.getProduct_category().getCategory_id();
        product.setProduct_category(categoryService.get(category_id));
        logger.info("根据产品id获取产品展示图片的信息");
        Integer product_id =product.getProduct_id();
        product.setSingleProductImageList(productImageService.getList(product_id,Byte.parseByte("0"),null));
        logger.info("根据产品id获取产品详情图片的信息");
        product.setDetailProductImageList(productImageService.getList(product_id,Byte.parseByte("1"),null));
        map.put("product",product);
        return "admin/include/productDetails";
    }
}