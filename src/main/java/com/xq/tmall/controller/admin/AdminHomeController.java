package com.xq.tmall.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xq.tmall.controller.BaseController;
import com.xq.tmall.entity.Admin;
import com.xq.tmall.entity.OrderGroup;
import com.xq.tmall.service.AdminService;
import com.xq.tmall.service.ProductOrderService;
import com.xq.tmall.service.ProductService;
import com.xq.tmall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * 后台管理-主页
 */
@Controller
public class AdminHomeController extends BaseController {
    @Resource(name = "adminService")
    private AdminService adminService;
    @Resource(name = "productOrderService")
    private ProductOrderService productOrderService;
    @Resource(name = "productService")
    private ProductService productService;
    @Resource(name = "userService")
    private UserService userService;

    //转到后台管理-主页
    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map) throws ParseException {
        logger.info("检查管理员权限");
        Object adminId = checkAdmin(session);
        if (adminId == null) {
            return "redirect:/admin/login";
        }

        logger.info("获取管理员信息");
        Admin admin = adminService.get(null, Integer.parseInt(adminId.toString()));
        map.put("admin", admin);
        logger.info("获取统计信息");
        Integer productTotal = productService.getTotal(null, new Byte[]{0, 2});
        Integer userTotal = userService.getTotal(null);
        Integer orderTotal = productOrderService.getTotal(null, new Byte[]{3});
        logger.info("获取图表信息");
        map.put("jsonObject", getChartData(null,null));
        map.put("productTotal", productTotal);
        map.put("userTotal", userTotal);
        map.put("orderTotal", orderTotal);

        logger.info("转到后台管理-主页");
        return "admin/homePage";
    }

    //转到后台管理-主页-ajax
    @RequestMapping(value = "admin/home", method = RequestMethod.GET)
    public String goToPageByAjax(HttpSession session, Map<String, Object> map) throws ParseException {
        logger.info("检查管理员权限");
        Object adminId = checkAdmin(session);
        if (adminId == null) {
            return "admin/include/loginMessage";
        }

        logger.info("获取管理员信息");
        Admin admin = adminService.get(null, Integer.parseInt(adminId.toString()));
        map.put("admin", admin);
        logger.info("获取统计信息");
        Integer productTotal = productService.getTotal(null, new Byte[]{0, 2});
        Integer userTotal = userService.getTotal(null);
        Integer orderTotal = productOrderService.getTotal(null, new Byte[]{3});
        logger.info("获取图表信息");
        map.put("jsonObject", getChartData(null, null));
        logger.info("获取图表信息");
        map.put("jsonObject", getChartData(null,null));
        map.put("productTotal", productTotal);
        map.put("userTotal", userTotal);
        map.put("orderTotal", orderTotal);
        logger.info("转到后台管理-主页-ajax方式");
        return "admin/homeManagePage";
    }

    //按日期查询图表数据-ajax
    @ResponseBody
    @RequestMapping(value = "admin/home/charts", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getChartDataByDate(@RequestParam(required = false) String beginDate, @RequestParam(required = false) String endDate) throws ParseException {
        if (beginDate != null && endDate != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return getChartData(simpleDateFormat.parse(beginDate), simpleDateFormat.parse(endDate)).toJSONString();
        } else {
            return getChartData(null, null).toJSONString();
        }
    }

    //获取图表的JSON数据
    private JSONObject getChartData(Date beginDate,Date endDate) throws ParseException {
        JSONObject jsonObject = new JSONObject();
        int day;
        if (beginDate == null || endDate == null) {
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -7);
            beginDate = time.parse(time.format(cal.getTime()));
            cal.add(Calendar.DATE, -1);
            endDate = time.parse(time.format(cal.getTime()));
            day = 7;
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            beginDate = simpleDateFormat.parse(simpleDateFormat.format(beginDate));
            endDate = simpleDateFormat.parse(simpleDateFormat.format(endDate));
            day = (int) ((endDate.getTime()-beginDate.getTime()) / (1000*3600*24)+1);
        }
        logger.info("获取总交易额订单列表");
        List<OrderGroup> orderGroupList = productOrderService.getTotalByDate(beginDate, endDate);
        logger.info("根据订单状态分类");
        int[] orderTotalArray = new int[day];//总交易订单数组
        int[] orderUnpaidArray = new int[day];//未付款订单数组
        int[] orderNotShippedArray = new int[day];//未发货订单叔祖
        int[] orderUnconfirmedArray = new int[day];//未确认订单数组
        int[] orderSuccessArray = new int[day];//交易成功数组
        for (int i=0,a=0,b=0,c=0,d=0; i < orderGroupList.size(); i++) {
            OrderGroup orderGroup = orderGroupList.get(i);
            switch (orderGroup.getProductOrder_status()) {
                case 0:
                    orderUnpaidArray[a] = orderGroup.getProductOrder_count();
                    a++;
                    break;
                case 1:
                    orderNotShippedArray[b] = orderGroup.getProductOrder_count();
                    b++;
                    break;
                case 2:
                    orderUnconfirmedArray[c] = orderGroup.getProductOrder_count();
                    c++;
                    break;
                case 3:
                    orderSuccessArray[d] = orderGroup.getProductOrder_count();
                    d++;
                    break;
                default:
                    break;
            }
        }
        String[] dateStr = new String[day];
        SimpleDateFormat time  = new SimpleDateFormat("MM/dd", Locale.UK);
        logger.info("获取时间段数组和总交易订单数组");
        for (int i = 0; i < dateStr.length; i++) {
            orderTotalArray[i] = orderUnpaidArray[i] + orderNotShippedArray[i] + orderUnconfirmedArray[i] + orderSuccessArray[i];
            Calendar cal = Calendar.getInstance();
            cal.setTime(beginDate);
            cal.add(Calendar.DATE, i);
            dateStr[i] = time.format(cal.getTime());
            logger.info(dateStr[i]);
        }
        logger.info("返回结果集map");
        jsonObject.put("orderTotalArray", JSONArray.parseArray(JSON.toJSONString(orderTotalArray)));
        jsonObject.put("orderUnpaidArray", JSONArray.parseArray(JSON.toJSONString(orderUnpaidArray)));
        jsonObject.put("orderNotShippedArray", JSONArray.parseArray(JSON.toJSONString(orderNotShippedArray)));
        jsonObject.put("orderUnconfirmedArray", JSONArray.parseArray(JSON.toJSONString(orderUnconfirmedArray)));
        jsonObject.put("orderSuccessArray", JSONArray.parseArray(JSON.toJSONString(orderSuccessArray)));
        jsonObject.put("dateStr",JSONArray.parseArray(JSON.toJSONString(dateStr)));
        return jsonObject;
    }
}
