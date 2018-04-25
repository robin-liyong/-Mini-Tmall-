package com.xq.tmall.controller.fore;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xq.tmall.controller.BaseController;
import com.xq.tmall.entity.*;
import com.xq.tmall.service.*;
import com.xq.tmall.util.PageUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ForeProductDetailsController extends BaseController {
    @Resource(name="productService")
    private ProductService productService;
    @Resource(name ="userService")
    private UserService userService;
    @Resource(name="productImageService")
    private ProductImageService productImageService;
    @Resource(name="categoryService")
    private CategoryService categoryService;
    @Resource(name="propertyValueService")
    private PropertyValueService propertyValueService;
    @Resource(name="propertyService")
    private PropertyService propertyService;
    @Resource(name="reviewService")
    private ReviewService reviewService;

    //转到前台产品详情页
    @RequestMapping(value="product/{pid}",method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map,
                           @PathVariable("pid") String pid /*产品ID*/){
            logger.info("获取用户信息");
            User user;
            Object object=checkUser(session);
            if (object!=null){
                user=userService.get(Integer.parseInt(object.toString()));
                map.put("user",user);
            }
            logger.info("获取产品ID");
            Integer product_id=Integer.parseInt(pid);
            logger.info("获取商品信息");
            Product product=productService.get(product_id);
            if (product!=null){
                logger.info("获取商品图片");
                List<ProductImage> productImageList = productImageService.getList(product_id, null, null);
                List<ProductImage> singleProductImageList = new ArrayList<>(5);
                List<ProductImage> detailsProductImageList = new ArrayList<>(8);
                for (ProductImage productImage : productImageList) {
                    if (productImage.getProductImage_type() == 0) {
                        singleProductImageList.add(productImage);
                    } else {
                        detailsProductImageList.add(productImage);
                    }
                }
                product.setSingleProductImageList(singleProductImageList);
                product.setDetailProductImageList(detailsProductImageList);
                logger.info("获取产品详情-属性值信息");
                List<PropertyValue> propertyValueList= propertyValueService.getList(new PropertyValue().setPropertyValue_product(product),null);
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
                map.put("product",product);
            }
        logger.info("转到前台-产品详情页");
        return "";
    }
    //按产品ID加载产品评论列表-ajax
    @ResponseBody
    @RequestMapping(value="review/{pid}",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String loadProductReviewList(@PathVariable("pid") String pid/*产品ID*/,
                                        @RequestParam Integer index/* 页数 */,
                                        @RequestParam Integer count/* 行数 */){
        logger.info("获取产品ID");
        Integer product_id=Integer.parseInt(pid);
        logger.info("获取产品评论列表");
        List<Review> reviewList=reviewService.getListByProductId(product_id,new PageUtil(index,count));
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("reviewList",JSONArray.parseArray(JSON.toJSONString(reviewList)));

        return jsonObject.toJSONString();
    }
    //按产品ID加载产品属性列表-ajax
    @ResponseBody
    @RequestMapping(value="property/{pid}",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String loadProductPropertyList(@PathVariable("pid") String pid/*产品ID*/){
        logger.info("获取产品ID");
        Integer product_id=Integer.parseInt(pid);

        logger.info("获取产品详情-属性值信息");
        Product product=new Product();
        product.setProduct_id(product_id);
        List<PropertyValue> propertyValueList= propertyValueService.getList(new PropertyValue().setPropertyValue_product(product),null);

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
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("propertyList",JSONArray.parseArray(JSON.toJSONString(propertyList)));

        return jsonObject.toJSONString();
    }
    //加载猜你喜欢列表-ajax
    @ResponseBody
    @RequestMapping(value="guess/{cid}",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String guessYouLike(@PathVariable("cid") Integer cid){
        int productCount=productService.getTotal(null,new Byte[]{0,2});
        logger.info("获取猜你喜商品列表");
        List<Product> productList=new ArrayList<>(3);
        Integer pid = productService.getList(new Product().setProduct_category(new Category().setCategory_id(cid)),new Byte[]{0,2},null,new PageUtil(0,1)).get(0).getProduct_id();
        int number = 0;
        int index = 0;
        while (true){
            //如果生成次数达到3次
            if(index==3){
                break;
            }
            //生成随机数
            int i = new Random().nextInt(productCount);
            if(i==number){
                continue;
            }
            number = i;
            Product product= productService.get(pid+i);
            if(product==null){
                continue;
            }
            product.setSingleProductImageList(productImageService.getList(product.getProduct_id(),(byte)0,new PageUtil(0,1)));
            productList.add(product);
            index++;
        }
        JSONObject jsonObject=new JSONObject();
        if(productList.size()==0){
            logger.info("获取数据成功！");
            jsonObject.put("success",false);
        } else {
            logger.info("获取数据失败！");
            jsonObject.put("success", true);
            jsonObject.put("productList",JSONArray.parseArray(JSON.toJSONString(productList)));
        }
        return jsonObject.toJSONString();
    }
}
