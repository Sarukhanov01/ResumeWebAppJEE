package com.example.controller;

import context.Context;
import dao.inter.UserDaoInter;
import entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserController", value = "/users")
public class UserController extends HttpServlet {
    private UserDaoInter userDao = Context.instanceUserDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String nationalityStr = request.getParameter("nid");
        Integer nationalityID = null;
        if (nationalityStr != null && !nationalityStr.trim().isEmpty()) {
            nationalityID = Integer.parseInt(nationalityStr);
        }
        List<User> users = userDao.getAll(name, surname,null);
        request.setAttribute("users",users);
        request.getRequestDispatcher("users.jsp").forward(request,response);
    }
}
