package com.cg.case_shopping.control;

import com.cg.case_shopping.entity.Category;
import com.cg.case_shopping.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
public class HomeServlet extends HttpServlet {
    protected void dogGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Assuming these attributes are set by a servlet that handles business logic
        List<Category> listCC = (List<Category>) request.getAttribute("listCC");
        Product product = (Product) request.getAttribute("product");
        List<Product> listP = (List<Product>) request.getAttribute("listP");
        request.setAttribute("listCC", listCC);
        request.setAttribute("p", product);
        request.setAttribute("listP", listP);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
        dispatcher.forward(request,response);
    }
}
