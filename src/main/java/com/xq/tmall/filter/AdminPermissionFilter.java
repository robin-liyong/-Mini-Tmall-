package com.xq.tmall.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 后台权限过滤器
 */
@WebFilter(filterName="adminPermissionFilter",urlPatterns= {"/admin/*"})
public class AdminPermissionFilter implements Filter {
    //log4j2
    protected Logger logger = LogManager.getLogger(AdminPermissionFilter.class);

    @Override
    public void init(FilterConfig filterConfig) { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        //如果是登录界面，直接放行
        if(servletRequest.getRequestURI().contains("/admin/login")){
            chain.doFilter(request, response);
        } else {
            logger.info("检查管理员权限");
            Object o = servletRequest.getSession().getAttribute("adminId");
            if(o == null){
                logger.info("无管理权限，返回管理员登陆页");
                request.getRequestDispatcher("/admin/login").forward(request, response);
            } else {
                logger.info("权限验证成功，管理员ID：{}",o);
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() { }
}
