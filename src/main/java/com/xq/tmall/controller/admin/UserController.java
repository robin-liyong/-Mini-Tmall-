package com.xq.tmall.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xq.tmall.controller.BaseController;
import com.xq.tmall.entity.User;
import com.xq.tmall.service.AddressService;
import com.xq.tmall.service.ReviewService;
import com.xq.tmall.service.UserService;
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
 * 后台管理-用户页
 */
@Controller
public class UserController extends BaseController{
    @Resource(name="userService")
    private UserService userService;
    @Resource(name="addressService")
    private AddressService addressService;
    @Resource(name ="reviewService")
    private ReviewService reviewService;

    //转到后台管理-用户页-ajax
    @RequestMapping("admin/user")
    public String goUserManagePage(HttpSession session, Map<String, Object> map){
        logger.info("检查管理员权限");
        Object adminId = checkAdmin(session);
        if(adminId == null){
            return null;
        }

        logger.info("获取前十条用户信息");
        List<User> userList = userService.getList(null, null, new PageUtil(1, 10));
        map.put("userList", userList);
        logger.info("获取用户总数量");
        Integer userCount = userService.getTotal(null);
        map.put("userCount", userCount);

        logger.info("转到后台管理-用户页-ajax方式");
        return "admin/userManagePage";
    }


    //转到后台管理-用户详情页-ajax
    @RequestMapping(value = "admin/user/{uid}")
    public String getUserById(HttpSession session, Map<String,Object> map, @PathVariable Integer uid/* 用户ID */){
        logger.info("检查管理员权限");
        Object adminId = checkAdmin(session);
        if(adminId == null){
            return null;
        }

        logger.info("获取user_id为{}的用户信息",uid);
        User user = userService.get(uid);
        logger.info("获取用户详情-所在地地址信息");
        String address_area_id = user.getUser_address().getAddress_areaId();
        user.setUser_address(addressService.get(address_area_id));
        logger.info("获取用户详情-家乡地址信息");
        String homeplace_area_id = user.getUser_homeplace().getAddress_areaId();
        user.setUser_homeplace(addressService.get(homeplace_area_id));
        logger.info("获取用户详情-评论信息");
        user.setReviewList(reviewService.getListByUserId(uid,new PageUtil(1,5)));
        map.put("user",user);

        logger.info("转到后台管理-用户详情页-ajax方式");
        return "admin/include/userDetails";
    }

    //按条件查询用户-ajax
    @ResponseBody
    @RequestMapping(value = "admin/user/{index}/{count}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getUserBySearch(@RequestParam(required = false) String user_name/* 用户名称 */,
                                  @RequestParam(required = false) Byte[] user_gender_array/* 用户性别数组 */,
                                  @RequestParam(required = false) String orderBy/* 排序字段 */,
                                  @RequestParam(required = false,defaultValue = "true") Boolean isDesc/* 是否倒序 */,
                                  @PathVariable Integer index/* 页数 */,
                                  @PathVariable Integer count/* 行数 */) throws UnsupportedEncodingException {
        //移除不必要条件
        Byte gender = null;
        if (user_gender_array != null && user_gender_array.length == 1) {
            gender = user_gender_array[0];
        }

        if (user_name != null) {
            //如果为非空字符串则解决中文乱码：URLDecoder.decode(String,"UTF-8");
            user_name = user_name.equals("") ? null : URLDecoder.decode(user_name, "UTF-8");
        }
        if(orderBy != null && orderBy.equals("")){
            orderBy = null;
        }
        //封装查询条件
        User user = new User()
                .setUser_name(user_name)
                .setUser_gender(gender);

        OrderUtil orderUtil = null;
        if (orderBy != null) {
            logger.info("根据{}排序，是否倒序:{}",orderBy,isDesc);
            orderUtil = new OrderUtil(orderBy, isDesc);
        }

        JSONObject object = new JSONObject();
        logger.info("按条件获取第{}页的{}条用户",index,count);
        List<User> userList = userService.getList(user, orderUtil, new PageUtil(index, count));
        object.put("userList", JSONArray.parseArray(JSON.toJSONString(userList)));
        logger.info("按条件获取用户总数量");
        Integer userCount = userService.getTotal(user);
        object.put("userCount", userCount);

        return object.toJSONString();
    }
}
