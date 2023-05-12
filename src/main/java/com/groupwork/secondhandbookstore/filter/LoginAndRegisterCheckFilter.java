package com.groupwork.secondhandbookstore.filter;

import com.alibaba.fastjson.JSONObject;
import com.groupwork.secondhandbookstore.pojo.Result;
import com.groupwork.secondhandbookstore.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginAndRegisterCheckFilter implements Filter {
    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) servletRequest;
        HttpServletResponse resp=(HttpServletResponse) servletResponse;
        //获取请求url
        String url=req.getRequestURL().toString();
        log.info("请求的url:{}",url);

        //判断请求中是否包含login，如果包含则说明是登录操作，放行
        if(url.contains("login")){
            log.info("登录操作，放行...");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //判断请求中是否包含register，如果包含则说明是注册操作，放行
        if(url.contains("register")){
            log.info("注册操作，放行...");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //获取请求头中的令牌（token)     -->这是不是登录操作的情况
        String jwt = req.getHeader("token");

        //判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象--json------->阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        //解析token，如果解析失败，返回错误结果（未登录）
        try{
            JwtUtils.parseJWT(jwt);
        }catch (Exception e){
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象--json------->阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        //放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
