package com.gaorui.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gaorui on 16/10/21.
 */
public class MeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        //response.setHeader("Access-Control-Allow-Origin", "*");
        //HttpSession session = req.getSession();
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
