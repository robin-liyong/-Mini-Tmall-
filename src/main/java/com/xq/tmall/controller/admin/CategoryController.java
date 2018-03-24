package com.xq.tmall.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xq.tmall.controller.BaseController;
import com.xq.tmall.entity.Category;
import com.xq.tmall.service.CategoryService;
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
 * 后台管理-分类页
 */
@Controller
public class CategoryController extends BaseController{
    @Resource(name = "categoryService")
    private CategoryService categoryService;

    //转到后台管理-分类页-ajax
    @RequestMapping("admin/category")
    public String goToPage(HttpSession session, Map<String, Object> map){
        logger.info("检查管理员权限");
        Object adminId = checkAdmin(session);
        if(adminId == null){
            return null;
        }

        logger.info("获取前10条分类列表");
        List<Category> categoryList = categoryService.getList(null,new PageUtil(1,10));
        map.put("categoryList",categoryList);
        logger.info("获取分类总数量");
        Integer categoryCount = categoryService.getTotal(null);
        map.put("categoryCount", categoryCount);

        logger.info("转到后台管理-分类页-ajax方式");
        return "admin/categoryManagePage";
    }

    //转到后台管理-分类详情页-ajax
    @RequestMapping("admin/category/{cid}")
    public String getCategoryByCid(HttpSession session, Map<String, Object> map, @PathVariable Integer cid/* 分类ID */){
        logger.info("检查管理员权限");
        Object adminId=checkAdmin(session);
        if (adminId==null){
            return null;
        }

        logger.info("获取category_id为{}的分类信息",cid);
        Category category=categoryService.get(cid);
        map.put("category",category);

        logger.info("转到后台管理-分类详情页-ajax方式");
        return "admin/include/categoryDetails";
    }

    //按条件查询分类-ajax
    @ResponseBody
    @RequestMapping(value = "admin/category/{index}/{count}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getCategoryBySearch(@RequestParam(required = false) String category_name/* 分类名称 */,
                                      @PathVariable Integer index/* 页数 */,
                                      @PathVariable Integer count/* 行数 */) throws UnsupportedEncodingException {
        //移除不必要条件
        if (category_name != null) {
            //如果为非空字符串则解决中文乱码：URLDecoder.decode(String,"UTF-8");
            category_name = category_name.equals("") ? null : URLDecoder.decode(category_name, "UTF-8");
        }

        JSONObject object = new JSONObject();
        logger.info("按条件获取第{}页的{}条分类",index,count);
        List<Category> categoryList=categoryService.getList(category_name, new PageUtil(index,count));
        object.put("categoryList", JSONArray.parseArray(JSON.toJSONString(categoryList)));
        logger.info("按条件获取分类总数量");
        Integer categoryCount = categoryService.getTotal(category_name);
        object.put("categoryCount",categoryCount);

        return object.toJSONString();
    }
}

