package com.xq.tmall.controller.admin;

import com.xq.tmall.controller.BaseController;
import com.xq.tmall.entity.User;
import com.xq.tmall.service.AddressService;
import com.xq.tmall.service.UserService;
import com.xq.tmall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "admin/include/userManagePage";
    }


    //转到后台用户管理页
    @RequestMapping(value = "admin/user/{uid}",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getUserById(HttpSession session, Map<String,Object> map, @RequestParam(required = false) Integer uid){
        logger.info("检查管理员权限");
        Object adminld = checkAdmin(session);
        if(adminld == null){
            return null;
        }

        logger.info("根据uid获取一个用户的信息");
        User user = userService.get(uid);
        logger.info("根据用户地址id获取用户地址信息");
        String address_area_id = user.getUser_address().getAddress_areaId();
        String homeplace_area_id = user.getUser_homeplace().getAddress_areaId();
        user.setUser_address(addressService.get(address_area_id));
        user.setUser_homeplace(addressService.get(homeplace_area_id));
        map.put("user",user);
        return "admin/include/userDetails";
    }
}
