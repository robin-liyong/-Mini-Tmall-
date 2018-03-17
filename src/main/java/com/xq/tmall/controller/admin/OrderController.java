package com.xq.tmall.controller.admin;

import com.xq.tmall.entity.ProductOrder;
import com.xq.tmall.service.ProductOrderService;
import com.xq.tmall.util.PageUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 后台管理-订单页
 */
@Controller
public class OrderController {
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    @Resource(name="productOrderService")
    private ProductOrderService productOrderService;

    //转到后台管理-订单页
    @RequestMapping("admin/order")
    public String goToPage(HttpSession session, Map<String, Object> map){
        logger.info("验证管理权限");
        Object o=isLogin(session);
        if (o==null){
            return null;
        }

        logger.info("获取前10条订单信息列表");
        List<ProductOrder> productOrderList=productOrderService.getList(null,null,new PageUtil(1,10));
        map.put("productOrderList",productOrderList);

        logger.info("转到后台管理-订单页");
        return "admin/include/productOrderManagePage";
    }

    //验证权限
    private Object isLogin(HttpSession session){
        Object o = session.getAttribute("adminId");
        if(o==null){
            logger.info("无管理权限，返回管理员登陆页");
        } else {
            logger.info("权限验证成功，管理员ID：{}",o);
        }
        return o;
    }
}
