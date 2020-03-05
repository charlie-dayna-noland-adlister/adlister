package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: show the registration form
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/profile");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO: ensure the submitted information is valid

        if(request.getParameter("username") != null && request.getParameter("email") != null && request.getParameter("password") != null) {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            if (username.matches("\\W+") || !email.matches(".+@.+\\.[a-z]{3,4}")) {
                return;
            }
            String pString = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\d)[a-zA-Z\\d]{6,}$";
            Pattern pattern = Pattern.compile(pString);
            Matcher matcher = pattern.matcher(password);
            if (!matcher.matches()) {
                return;
            }


            // TODO: create a new user based off of the submitted information
            User user = new User(username, email, password);
            DaoFactory.getUsersDao().insert(user);
            request.getSession().setAttribute("user", username);
            request.getSession().setAttribute("userObj", user);
            // TODO: if a user was successfully created, send them to their profile
            response.sendRedirect("/profile");
        }
    }
}
