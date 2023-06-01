package com.secondhandbookstore.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.secondhandbookstore.pojo.Result;
import com.secondhandbookstore.utils.JwtUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        System.out.println("拦截到请求");
        //获取请求url
        String url=req.getRequestURL().toString();
        log.info("请求的url:{}",url);

        //判断请求中是否包含login，如果包含则说明是登录操作，放行
        if(url.contains("Login")){
            log.info("登录操作，放行...");
            return true;
        }
        //判断请求中是否包含register，如果包含则说明是注册操作，放行
        if(url.contains("Register")){
            log.info("注册操作，放行...");
            return true;
        }
        if(url.contains("index")) {
            log.info("首页，放行...");
            return true;
        }

        //获取请求头中的令牌（token)
        String jwt = req.getHeader("Authorization");

        //判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头Authorization为空，返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换 对象--json------->阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
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
            return false;
        }

        //放行
        log.info("令牌合法，放行");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
