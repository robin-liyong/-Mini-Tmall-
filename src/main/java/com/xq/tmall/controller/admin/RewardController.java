package com.xq.tmall.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xq.tmall.controller.BaseController;
import com.xq.tmall.entity.Reward;
import com.xq.tmall.service.LastIDService;
import com.xq.tmall.service.RewardService;
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
 * 后台管理-打赏信息页
 */
@Controller
public class RewardController extends BaseController {
    @Resource(name = "rewardService")
    private RewardService rewardService;
    @Resource(name = "lastIDService")
    private LastIDService lastIDService;
    @Resource(name = "userService")
    private UserService userService;

    //转到后台管理-打赏信息页-ajax
    @RequestMapping(value = "admin/reward", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map) {
        logger.info("获取前10条打赏信息列表");
        PageUtil pageUtil = new PageUtil(0, 10);
        OrderUtil orderUtil = new OrderUtil("reward_createDate",true);
        List<Reward> rewardList = rewardService.getList(null,null, orderUtil, pageUtil);
        map.put("rewardList", rewardList);
        logger.info("获取打赏总数量");
        Integer rewardCount = rewardService.getTotal(null,null);
        map.put("rewardCount", rewardCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(rewardCount);
        map.put("pageUtil", pageUtil);

        logger.info("转到后台管理-打赏信息页-ajax方式");
        return "admin/rewardManagePage";
    }

    //转到后台管理-打赏信息添加页-ajax
    @RequestMapping(value = "admin/reward/new", method = RequestMethod.GET)
    public String goToAddPage(HttpSession session, Map<String, Object> map) {
        logger.info("转到后台管理-打赏信息添加页-ajax方式");
        return "admin/include/rewardDetails";
    }

    //按条件查询产品-ajax
    @ResponseBody
    @RequestMapping(value = "admin/reward/{index}/{count}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getRewardBySearch(@RequestParam(required = false) String reward_name/* 打赏人名称 */,
                                     @RequestParam(required = false) Double reward_lowest_amount/* 打赏最低金额 */,
                                     @RequestParam(required = false) Double reward_highest_amount/* 打赏最高金额 */,
                                     @RequestParam(required = false) Byte[] reward_isEnabled_array/* 打赏状态数组 */,
                                     @RequestParam(required = false) String orderBy/* 排序字段 */,
                                     @RequestParam(required = false,defaultValue = "true") Boolean isDesc/* 是否倒序 */,
                                     @PathVariable Integer index/* 页数 */,
                                     @PathVariable Integer count/* 行数 */) throws UnsupportedEncodingException {
        //移除不必要条件
        if (reward_isEnabled_array != null && (reward_isEnabled_array.length <= 0 || reward_isEnabled_array.length >=3)) {
            reward_isEnabled_array = null;
        }
        if (reward_name != null) {
            //如果为非空字符串则解决中文乱码：URLDecoder.decode(String,"UTF-8");
            reward_name = "".equals(reward_name) ? null : URLDecoder.decode(reward_name, "UTF-8");
        }
        if (orderBy != null && "".equals(orderBy)) {
            orderBy = null;
        }
        //封装查询条件
        Reward reward = new Reward()
                .setReward_name(reward_name)
                .setReward_lowest_amount(reward_lowest_amount)
                .setReward_amount(reward_highest_amount);
        OrderUtil orderUtil = null;
        if (orderBy != null) {
            logger.info("根据{}排序，是否倒序:{}",orderBy,isDesc);
            orderUtil = new OrderUtil(orderBy, isDesc);
        }

        JSONObject object = new JSONObject();
        logger.info("按条件获取第{}页的{}条打赏", index + 1, count);
        PageUtil pageUtil = new PageUtil(index, count);
        List<Reward> rewardList = rewardService.getList(reward, reward_isEnabled_array, orderUtil, pageUtil);
        object.put("rewardList", JSONArray.parseArray(JSON.toJSONString(rewardList)));
        logger.info("按条件获取打赏总条数");
        Integer rewardCount = rewardService.getTotal(reward, reward_isEnabled_array);
        object.put("rewardCount", rewardCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(rewardCount);
        object.put("totalPage", pageUtil.getTotalPage());
        object.put("pageUtil", pageUtil);

        return object.toJSONString();
    }
}
