package com.cg.case_shopping.control;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        // Validate username and password here, you can use your authentication logic
        // For demo purposes, let's assume the login is successful
        if (username.equals("root") && password.equals("123456")) {
            request.getSession().setAttribute("loggedIn", true);
            response.sendRedirect(request.getContextPath() + "/products");
        } else {
            request.setAttribute("errorMessage", "Invalid username or password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
