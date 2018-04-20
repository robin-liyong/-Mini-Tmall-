package com.xq.tmall.controller.fore;

import com.xq.tmall.controller.BaseController;
import com.xq.tmall.entity.User;
import com.xq.tmall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 前台天猫-主页
 */
@Controller
public class HomeController extends BaseController {
    @Resource(name = "userService")
    private UserService userService;

    //转到前台天猫-主页
    @RequestMapping("/")
    public String goToPage(HttpSession session, Map<String, Object> map) {
        logger.info("检查用户是否登录");
        //在BaseController中编写CheckUser方法，检测用户是否登录，可参考checkAdmin()，此处注释应删除
        Object userId = checkUser(session);
        if (userId != null) {
            logger.info("获取用户信息");
            User user = userService.get(Integer.parseInt(userId.toString()));
            map.put("user", user);
        }
        logger.info("获取产品分类列表");
        //获取所有的产品分类数据，此处注释应删除
        logger.info("获取每个分类下的一组复杂产品数据");
        /*
         * 挑战题：
         * 遍历上面获取的产品分类列表，然后获取每个分类中定义的一个二维产品集合
         * 然后获取分类的id，并且建立一个循环8次的循环（i从1开始），这里的i作为分页中的index使用
         * 通过分页和分类id查询产品并返回集合，非空判断后，通过产品id拿到每一个产品的第一张预览图片，预览图片的type值为0
         * 核心代码：productImageService.getList(产品id, (byte) 0, new PageUtil(0, 1))
         * 最后将此产品集合放入二维产品集合中并赋给分类的产品集合。
         */

        /*
         * 最后：将分类集合传给前台
         */

        logger.info("转到后台管理-主页");
        return "fore/homePage";
    }
}
