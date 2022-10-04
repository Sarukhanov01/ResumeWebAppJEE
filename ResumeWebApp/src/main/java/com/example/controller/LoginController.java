package com.example.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import context.Context;
import dao.inter.UserDaoInter;
import entity.User;
import util.ControllerUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static at.favre.lib.crypto.bcrypt.BCrypt.verifyer;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    UserDaoInter userDao = Context.instanceUserDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
//            BCrypt.Verifyer verifyer= BCrypt.verifyer()
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User user = userDao.findByEmailAndPassword(email, password);
//            User user2 = userDao.findByEmail(email);

            if (user == null) {
                throw new IllegalArgumentException("email or password is incorrect!!!");
            }
//            BCrypt.Result rs=verifyer.verify(password.toCharArray(),user2.getPassword().toCharArray()); Verifyes if encrypted pasword is
//                                                                                                         equal with typed one
//            if (!rs.verified){
//                throw new IllegalArgumentException("password is incorrect");
//            }
            request.getSession().setAttribute("loggedInUser", user);
//            response.sendRedirect("/"); // redirect to main page which is 'index'
            response.sendRedirect("users");
        }catch (Exception ex){
            ControllerUtil.errorPage(response,ex);
        }
    }
}
