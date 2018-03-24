package com.xq.tmall.controller.admin;

import com.xq.tmall.controller.BaseController;
import com.xq.tmall.entity.ProductOrder;
import com.xq.tmall.service.AddressService;
import com.xq.tmall.service.ProductOrderItemService;
import com.xq.tmall.service.ProductOrderService;
import com.xq.tmall.service.UserService;
import com.xq.tmall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 后台管理-订单页
 */
@Controller
public class OrderController extends BaseController{
    @Resource(name="productOrderService")
    private ProductOrderService productOrderService;
    @Resource(name = "addressService")
    private AddressService addressService;
    @Resource(name="userService")
    private UserService userService;
    @Resource(name = "productOrderItemService")
    private ProductOrderItemService productOrderItemService;

    //转到后台管理-订单页-ajax
    @RequestMapping("admin/order")
    public String goToPage(HttpSession session, Map<String, Object> map){
        logger.info("检查管理员权限");
        Object adminId = checkAdmin(session);
        if(adminId == null){
            return null;
        }

        logger.info("获取前10条订单列表");
        List<ProductOrder> productOrderList=productOrderService.getList(null,null,new PageUtil(1,10));
        map.put("productOrderList",productOrderList);

        logger.info("转到后台管理-订单页-ajax方式");
        return "admin/OrderManagePage";
    }

    //转到后台管理-订单详情页-ajax
    @RequestMapping("admin/order/{oid}")
    public String getOrderByOid(HttpSession session,Map<String, Object> map, @PathVariable Integer oid/* 订单ID */){
        logger.info("检查管理员权限");
        Object adminId = checkAdmin(session);
        if(adminId == null){
            return null;
        }

        logger.info("获取order_id为{}的订单信息",oid);
        ProductOrder order = productOrderService.get(oid);
        logger.info("获取订单详情-地址信息");
        order.setProductOrder_address(addressService.get(order.getProductOrder_address().getAddress_areaId()));
        logger.info("获取订单详情-用户信息");
        order.setProductOrder_user(userService.get(order.getProductOrder_user().getUser_id()));
        logger.info("获取订单详情-订单项信息");
        order.setProductOrderItemList(productOrderItemService.getListByOrderId(oid,new PageUtil(1,5)));
        map.put("order",order);

        logger.info("转到后台管理-订单详情页-ajax方式");
        return "admin/include/orderDetails";
    }

    //按条件查询订单-ajax
    @ResponseBody
    @RequestMapping(value = "admin/order/{index}/{count}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getOrderBySearch(@PathVariable Integer index/* 页数 */,
                                   @PathVariable Integer count/* 行数 */){
        return "To be perfected";
    }
}
