package com.groupwork.secondhandbookstore.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")         // “/*”代表拦截所有请求
public class DemoFilter implements Filter {
    @Override   //初始化方法，只调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override   //销毁方法，只调用一次
    public void destroy() {
        Filter.super.destroy();
    }

    @Override   //拦截到请求后调用，调用多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截成功");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
