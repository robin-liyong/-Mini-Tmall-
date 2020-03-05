package com.xq.tmall.controller.fore;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xq.tmall.controller.BaseController;
import com.xq.tmall.entity.Reward;
import com.xq.tmall.entity.User;
import com.xq.tmall.service.RewardService;
import com.xq.tmall.service.UserService;
import com.xq.tmall.util.OrderUtil;
import com.xq.tmall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 打赏信息管理
 * @author 贤趣项目小组
 */
@Controller
public class ForeRewardController extends BaseController {
    @Resource(name = "rewardService")
    private RewardService rewardService;
    @Resource(name = "userService")
    private UserService userService;

    //添加打赏信息-ajax
    @ResponseBody
    @RequestMapping(value = "reward/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String addReward(HttpSession session, @RequestParam String name, @RequestParam String message) {
        JSONObject jsonObject = new JSONObject();
        logger.info("检查用户是否登录");
        Object userId = checkUser(session);
        User user;
        if (userId == null) {
            userId = 0;
        }
        logger.info("整合打赏信息");
        Reward reward = new Reward()
                .setReward_name(name)
                .setReward_content(message)
                .setReward_state(0)
                .setReward_amount(0.00)
                .setReward_createDate(new Date())
                .setReward_user(new User().setUser_id((Integer) userId));
        logger.info("添加打赏");
        Boolean yn = rewardService.add(reward);
        if (!yn) {
            throw new RuntimeException();
        }
        jsonObject.put("success", true);
        return jsonObject.toJSONString();
    }

    //查询打赏信息-ajax
    @ResponseBody
    @RequestMapping(value = "reward/list/{index}/{count}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getReward(@PathVariable Integer index/* 页数 */,
                            @PathVariable Integer count/* 行数 */) {
        JSONObject object = new JSONObject();
        logger.info("获取第{}页的{}条打赏，按金额数目从大到小排序", index + 1, count);
        PageUtil pageUtil = new PageUtil(index, count);
        OrderUtil orderUtil = new OrderUtil("reward_amount",true);
        List<Reward> rewardList = rewardService.getList(null, null, orderUtil, pageUtil);
        object.put("rewardList", JSONArray.parseArray(JSON.toJSONString(rewardList)));
        logger.info("获取打赏总数量");
        Integer rewardCount = rewardService.getTotal(null,null);
        object.put("rewardCount", rewardCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(rewardCount);
        object.put("totalPage", pageUtil.getTotalPage());
        object.put("pageUtil", pageUtil);

        return object.toJSONString();
    }
}
