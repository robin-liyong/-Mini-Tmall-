package com.xq.tmall.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xq.tmall.controller.BaseController;
import com.xq.tmall.entity.Category;
import com.xq.tmall.entity.Product;
import com.xq.tmall.entity.Property;
import com.xq.tmall.entity.PropertyValue;
import com.xq.tmall.service.*;
import com.xq.tmall.util.OrderUtil;
import com.xq.tmall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
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
    @Resource(name = "propertyService")
    private PropertyService propertyService;
    @Resource(name = "propertyValueService")
    private PropertyValueService propertyValueService;

    //转到后台管理-产品页-ajax
    @RequestMapping(value = "admin/product",method = RequestMethod.GET)
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

        logger.info("转到后台管理-产品页-ajax方式");
        return "admin/productManagePage";
    }

    //转到后台管理-产品详情页-ajax
    @RequestMapping(value="admin/product/{pid}",method = RequestMethod.GET)
    public String goToDetailsPage(HttpSession session,Map<String, Object> map, @PathVariable Integer pid/* 产品ID */){
        logger.info("检查管理员权限");
        Object adminId = checkAdmin(session);
        if(adminId == null){
            return null;
        }
        logger.info("获取product_id为{}的产品信息",pid);
        Product product = productService.get(pid);
        logger.info("获取产品详情-分类信息");
        Integer category_id = product.getProduct_category().getCategory_id();
        product.setProduct_category(categoryService.get(category_id));
        logger.info("获取产品详情-展示图片信息");
        Integer product_id =product.getProduct_id();
        product.setSingleProductImageList(productImageService.getList(product_id,Byte.parseByte("0"),null));
        logger.info("获取产品详情-详情图片信息");
        product.setDetailProductImageList(productImageService.getList(product_id,Byte.parseByte("1"),null));
        map.put("product",product);
        logger.info("获取产品详情-属性值信息");
        List<PropertyValue> propertyValueList = propertyValueService.getList(new PropertyValue().setPropertyValue_product(product),null);
        logger.info("获取产品详情-分类信息对应的属性列表");
        List<Property> propertyList = propertyService.getList(new Property().setProperty_category(product.getProduct_category()),null);
        logger.info("属性列表和属性值列表合并");
        for(Property property : propertyList){
            for(PropertyValue propertyValue : propertyValueList){
                if(property.getProperty_id().equals(propertyValue.getPropertyValue_property().getProperty_id())){
                    List<PropertyValue> property_value_item = new ArrayList<>(1);
                    property_value_item.add(propertyValue);
                    property.setPropertyValueList(property_value_item);
                    break;
                }
            }
        }
        map.put("propertyList",propertyList);
        logger.info("获取分类列表");
        List<Category> categoryList = categoryService.getList(null,null);
        map.put("categoryList",categoryList);

        logger.info("转到后台管理-产品详情页-ajax方式");
        return "admin/include/productDetails";
    }

    //转到后台管理-产品添加页-ajax
    @RequestMapping(value = "admin/product/new",method = RequestMethod.GET)
    public String goToAddPage(HttpSession session,Map<String, Object> map){
        logger.info("检查管理员权限");
        Object adminId = checkAdmin(session);
        if(adminId == null){
            return null;
        }

        logger.info("获取分类列表");
        List<Category> categoryList = categoryService.getList(null,null);
        map.put("categoryList",categoryList);
        logger.info("获取第一个分类信息对应的属性列表");
        List<Property> propertyList = propertyService.getList(new Property().setProperty_category(categoryList.get(0)),null);
        map.put("propertyList",propertyList);

        logger.info("转到后台管理-产品添加页-ajax方式");
        return "admin/include/productDetails";
    }

    //添加产品-ajax
    @ResponseBody
    @RequestMapping(value = "admin/product", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String addProduct(@RequestParam Product product/* 产品对象 */){
        return "";
    }

    //更新产品-ajax
    @ResponseBody
    @RequestMapping(value = "admin/product", method = RequestMethod.PUT,produces = "application/json;charset=utf-8")
    public String updateProduct(@RequestParam Product product/* 产品对象 */){
        return "";
    }

    //按条件查询产品-ajax
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

    //按类型查询属性-ajax
    @ResponseBody
    @RequestMapping(value = "admin/property/type/{property_category_id}", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getPropertyByCategory(@PathVariable Integer property_category_id/* 属性所属类型ID*/){
        //封装查询条件
        Category category = new Category()
                .setCategory_id(property_category_id);

        JSONObject object = new JSONObject();
        logger.info("按类型获取属性列表，类型ID：{}",property_category_id);
        List<Property> propertyList = propertyService.getList(new Property().setProperty_category(category),null);
        object.put("propertyList",JSONArray.parseArray(JSON.toJSONString(propertyList)));

        return object.toJSONString();
    }
}