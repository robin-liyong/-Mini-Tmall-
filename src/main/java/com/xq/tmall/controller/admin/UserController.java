package com.xq.tmall.controller.admin;

import com.xq.tmall.controller.BaseController;
import com.xq.tmall.entity.User;
import com.xq.tmall.service.UserService;
import com.xq.tmall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
