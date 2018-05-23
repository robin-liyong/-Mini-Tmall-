package com.xq.tmall.controller.fore;

import com.alibaba.fastjson.JSONObject;
import com.xq.tmall.controller.BaseController;
import com.xq.tmall.entity.Address;
import com.xq.tmall.entity.User;
import com.xq.tmall.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class ForeUserController extends BaseController{
    @Resource(name="userService")
    private UserService userService;

    //转到前台天猫-用户详情页
    @RequestMapping(value = "fore/userDetail", method = RequestMethod.GET)
    public String goToUserdetail(HttpSession session, Map<String,Object> map){
        logger.info("检查用户是否登录");
        Object userId = checkUser(session);
        if (userId != null) {
            logger.info("获取用户信息");
            User user = userService.get(Integer.parseInt(userId.toString()));
            map.put("user", user);
        }
        return  "fore/userDetail";
    }
    //前台天猫-用户更换头像
    @ResponseBody
    @RequestMapping(value = "user/uploadUserHeadImage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public  String uploadUserHeadImage(@RequestParam MultipartFile file, HttpSession session
    ){
        String originalFileName = file.getOriginalFilename();
        logger.info("获取图片原始文件名：{}", originalFileName);
        String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        String fileName = UUID.randomUUID() + extension;
        String filePath = session.getServletContext().getRealPath("/") + "" + fileName;

        logger.info("文件上传路径：{}", filePath);
        JSONObject jsonObject = new JSONObject();
        try {
            logger.info("文件上传中...");
            file.transferTo(new File(filePath));
            logger.info("文件上传成功！");
            jsonObject.put("success", true);
            jsonObject.put("fileName", fileName);
        } catch (IOException e) {
            logger.warn("文件上传失败！");
            e.printStackTrace();
            jsonObject.put("success", false);
        }
        return jsonObject.toJSONString();
    }
    //前台天猫-用户详情更新
    @RequestMapping(value="user/update",method=RequestMethod.POST,produces ="application/json;charset=utf-8")
    public String userUpdate(HttpSession session,Map<String,Object> map,
                             @RequestParam(value = "user_nickname", required = false) String user_nickname  /*用户昵称 */,
                             @RequestParam(value = "user_password", required = false) String user_password  /*用户密码*/,
                             @RequestParam(value = "user_realname", required = false) String user_realname  /*真实姓名*/,
                             @RequestParam(value = "user_gender", required = false) Byte user_gender  /*用户性别*/,
                             @RequestParam(value = "user_birthday", required = false) Date user_birthday /*用户生日*/,
                             @RequestParam(value = "user_address", required = false) String user_address  /*用户所在地 */,
                             @RequestParam(value = "user_homeplace", required = false)String user_homeplace /* 用户家乡*/,
                             @RequestParam(value = "user_profile_picture_src", required = false)String user_profile_picture_src /* 用户头像*/
    ){
        logger.info("检查用户是否登录");
        Object userId = checkUser(session);
        if (userId != null) {
            logger.info("获取用户信息");
            User user = userService.get(Integer.parseInt(userId.toString()));
            map.put("user", user);
        }
        logger.info("创建用户对象");
        User   userUpdate=new User().setUser_id(Integer.parseInt(userId.toString()))
                .setUser_nickname(user_nickname).setUser_password(user_password)
                .setUser_realname(user_realname).setUser_gender(user_gender)
                .setUser_birthday(user_birthday)
                .setUser_address(new Address().setAddress_areaId(user_address))
                .setUser_homeplace(new Address().setAddress_areaId(user_homeplace))
                .setUser_profile_picture_src(user_profile_picture_src);
         logger.info("执行修改");
         if (userService.update(userUpdate)){
             logger.info("修改成功!跳转到用户详情页面");
             return "redirect:goToUserdetail";
         }
         throw  new  RuntimeException();
    }
}
