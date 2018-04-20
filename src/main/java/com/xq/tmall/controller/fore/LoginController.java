package com.xq.tmall.controller.fore;

import com.xq.tmall.controller.BaseController;
import com.xq.tmall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 前台天猫-登陆页
 */
@Controller
public class LoginController extends BaseController {
    @Resource(name = "userService")
    private UserService userService;

    //转到前台天猫-主页
    @RequestMapping("/login")
    public String goToPage(HttpSession session, Map<String, Object> map) {
        logger.info("转到前台天猫-登录页");
        return "fore/loginPage";
    }

    //登陆验证-ajax
    @ResponseBody
    @RequestMapping(value = "/login/doLogin", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String checkLogin(HttpSession session, @RequestParam String username, @RequestParam String password) {
        //参考后端的登录验证，注意注释
        return "to be continued";
    }
}
