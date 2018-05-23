package com.xq.tmall.controller.fore;
import com.xq.tmall.controller.BaseController;
import com.xq.tmall.entity.Address;
import com.xq.tmall.entity.User;
import com.xq.tmall.service.AddressService;
import com.xq.tmall.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ForeRegisterController extends BaseController{
    @Resource(name = "addressService")
    private AddressService addressService;
    @Resource(name="userService")
    private UserService userService;
    //转到前台天猫-用户注册页
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String goToPage(Map<String,Object> map) {
        String addressId = "110000";
        String cityAddressId = "110100";
        logger.info("获取省份信息");
        List<Address> addressList = addressService.getRoot();
        logger.info("获取addressId为{}的市级地址信息", addressId);
        List<Address> cityAddress = addressService.getList(null, addressId);
        logger.info("获取cityAddressId为{}的区级地址信息", cityAddressId);
        List<Address> districtAddress = addressService.getList(null, cityAddressId);

        map.put("addressList", addressList);
        map.put("cityList", cityAddress);
        map.put("districtList", districtAddress);
        map.put("addressId", addressId);
        map.put("cityAddressId", cityAddressId);
        logger.info("转到前台-用户注册页");
        return "fore/register";
    }
    //天猫前台-用户注册方法
    @RequestMapping(value = "register/toRegister", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String register(
            @RequestParam(value = "user_name", required = false) String user_name  /*用户名 */,
            @RequestParam(value = "user_nickname", required = false) String user_nickname  /*用户昵称 */,
            @RequestParam(value = "user_password", required = false) String user_password  /*用户密码*/,
            @RequestParam(value = "user_gender", required = false) Byte user_gender  /*用户性别*/,
            @RequestParam(value = "user_birthday", required = false) Date user_birthday /*用户生日*/,
            @RequestParam(value = "user_address", required = false) String user_address  /*用户所在地 */,
            @RequestParam(value = "user_homeplace", required = false)String user_homeplace /* 用户家乡*/
    ){
        logger.info("创建用户对象");
        User user=new User().setUser_name(user_name)
                .setUser_nickname(user_nickname)
                .setUser_password(user_password)
                .setUser_gender(user_gender)
                .setUser_birthday(user_birthday)
                .setUser_address(new Address().setAddress_areaId(user_address))
                .setUser_homeplace(new Address().setAddress_areaId(user_homeplace));
        logger.info("用户注册");
        if(userService.add(user)){
                logger.info("注册成功!跳转到登录界面");
                return "redirect:/goToPage";
        }
        throw  new RuntimeException();
    }
}
