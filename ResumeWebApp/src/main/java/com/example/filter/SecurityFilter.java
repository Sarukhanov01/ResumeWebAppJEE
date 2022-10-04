package com.example.filter;

import util.ControllerUtil;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "JSPFileFilter", urlPatterns = {"*"})
public class SecurityFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) {
        try {
            HttpServletResponse res = (HttpServletResponse) response;
            HttpServletRequest req = (HttpServletRequest) request;

            if (!req.getRequestURI().contains("/login") && req.getSession().getAttribute("loggedInUser") == null) {
                res.sendRedirect("login");
            } else {
                chain.doFilter(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
