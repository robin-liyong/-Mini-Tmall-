package com.xq.tmall.controller.admin;

import com.xq.tmall.controller.BaseController;
import com.xq.tmall.entity.User;
import com.xq.tmall.service.AddressService;
import com.xq.tmall.service.ReviewService;
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
        List<User> userList = userService.getList(null, new PageUtil(1, 10));
        map.put("userList", userList);

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
    public String getUserBySearch(@PathVariable Integer index/* 页数 */,
                                   @PathVariable Integer count/* 行数 */){
        return "To be perfected";
    }
}
