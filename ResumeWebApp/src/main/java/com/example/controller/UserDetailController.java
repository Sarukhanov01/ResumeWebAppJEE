package com.example.controller;

import context.Context;
import dao.inter.UserDaoInter;
import entity.Country;
import entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "UserDetailController", value = "/userdetail")
public class UserDetailController extends HttpServlet {
    private UserDaoInter userDao = Context.instanceUserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");
        if(action.equals("update")){
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String profileDescription = request.getParameter("profileDescription");
        Date birthdate = Date.valueOf(request.getParameter("birthdate"));
        User user = userDao.getById(id);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setProfileDesc(profileDescription);
        user.setBirthDate(birthdate);
        userDao.updateUser(user);
        }else if(action.equals("delete")){
            userDao.removeUser(id);
        }
            response.sendRedirect("users");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        String userIdStr = request.getParameter("id");
        if (userIdStr==null || userIdStr.trim().isEmpty()){
            throw new IllegalArgumentException("id is not specified");
        }
        Integer userId = Integer.parseInt(userIdStr);
        User u = userDao.getById(userId);
        if (u == null){
            throw new IllegalArgumentException("There is no user with this id");
        }
        request.setAttribute("user",u);
        request.getRequestDispatcher("userdetails.jsp").forward(request,response);
        } catch (Exception ex){
            ex.printStackTrace();
            response.sendRedirect("error?msg="+ex.getMessage());
        }

    }

}
