package com.xq.tmall.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.xq.tmall.controller.BaseController;
import com.xq.tmall.entity.Admin;
import com.xq.tmall.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * 后台管理-账户页
 */
@Controller
public class AccountController extends BaseController{
    @Resource(name = "adminService")
    private AdminService adminService;

    //转到后台管理-账户页-ajax
    @RequestMapping("admin/account")
    public String goToPage(HttpSession session, Map<String, Object> map){
        logger.info("检查管理员权限");
        Object adminId = checkAdmin(session);
        if(adminId == null){
            return null;
        }

        logger.info("获取目前登录的管理员信息，管理员ID：{}",adminId);
        Admin admin = adminService.get(null,Integer.parseInt(adminId.toString()));
        map.put("admin",admin);

        logger.info("转到后台管理-账户页-ajax方式");
        return "admin/accountManagePage";
    }
    //管理员修改密码
    @ResponseBody
    @RequestMapping(value = "admin/account/check", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatePassword(HttpSession session,@RequestParam String oldPwd,@RequestParam String newPwd){
        JSONObject jsonObject=new JSONObject();
        Object adminId=checkAdmin(session);
        Admin admin=adminService.get(null,Integer.valueOf(adminId.toString()));
        if(admin!=null){
            if (adminService.login(admin.getAdmin_name(),oldPwd)!=null){
                    logger.info("原密码正确");
                    Boolean isok=adminService.update(new Admin().setAdmin_id(Integer.valueOf(adminId.toString())).setAdmin_password(newPwd));
                    if (isok){
                        logger.info("修改密码成功！");
                        jsonObject.put("success",true);
                    }
            }
        }
        jsonObject.put("success",false);
        return jsonObject.toJSONString();
    }
    //管理员头像上传
    @ResponseBody
    @RequestMapping(value = "admin/account/uploadAdminHeadImage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String uploadAdminHeadImage(@RequestParam MultipartFile file){
        String originalFileName = file.getOriginalFilename();
        logger.info("获取图片原始文件名：{}", originalFileName);
        String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        String filePath="";
        String fileName = UUID.randomUUID() + extension;

        logger.info("文件上传路径：{}", filePath);
        JSONObject jsonObject = new JSONObject();
        try {
            logger.info("文件上传中...");
            file.transferTo(new File(filePath));
            logger.info("文件上传成功！");
            jsonObject.put("success",true);
            jsonObject.put("fileName",fileName);
        }catch (IOException e){
            logger.warn("文件上传失败！");
            e.printStackTrace();
            jsonObject.put("success",false);
        }
        return jsonObject.toJSONString();
    }
    //更新管理员信息
    @ResponseBody
    @RequestMapping(value = "admin/account/{admin_id}", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public String updateAdmin(@RequestParam String admin_name/*管理员名称*/,
            @RequestParam(required = false) String admin_nickname/*管理员昵称*/,
            @RequestParam(required = false) String admin_password/*管理员密码*/,
            @RequestParam(required = false) String admin_profile_picture_src/*管理员头像路径*/,
            @PathVariable("admin_id") String admin_id/*管理员编号*/){
        JSONObject jsonObject=new JSONObject();
        if("".equals(admin_name)&&"".equals(admin_nickname)&&
                "".equals(admin_password)&&"".equals(admin_profile_picture_src)){
            logger.info("所有字段为空，未做修改");
            jsonObject.put("success",true);
        }else{
            logger.info("更新管理员信息");
            Boolean isok=adminService.update(new Admin().setAdmin_id(Integer.valueOf(admin_id))
                    .setAdmin_name(admin_name)
                    .setAdmin_nickname(admin_nickname)
                    .setAdmin_password(admin_password)
                    .setAdmin_profile_picture_src(admin_profile_picture_src));
            if (isok) {
                logger.info("更新成功！");
                jsonObject.put("success",true);
            }else{
                logger.warn("更新失败！");
                jsonObject.put("success",false);
            }
        }
        return jsonObject.toJSONString();
    }
}
