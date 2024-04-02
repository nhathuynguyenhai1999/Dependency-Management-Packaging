/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cg.case_shopping.control;


import com.cg.case_shopping.dao.CategoryDAO;
import com.cg.case_shopping.dao.DAO;
import com.cg.case_shopping.dao.ProductDAO;
import com.cg.case_shopping.entity.Category;
import com.cg.case_shopping.entity.Product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.naming.directory.SearchResult;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author trinh
 */
@WebServlet(name = "HomeControl", urlPatterns = {"/home"})
public class HomeControlServlet extends HttpServlet {
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    @Override
    public void init() throws ServletException {
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //b1: get data from dao
        /*
        DAO dao = new DAO();
        List<Product> list = dao.getAllProduct();
        List<Category> listC = dao.getAllCategory();
        Product last = dao.getLast();
        
        //b2: set data to jsp
        request.setAttribute("listP", list);
        request.setAttribute("listCC", listC);
        request.setAttribute("p", last);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
        //404 -> url
        //500 -> jsp properties
        */
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "category":
                showHomePage(request, response);
                break;
            case "search":
                showSearchPage(request, response);
                break;
            default:
                showHomePage(request, response);
        }
    }

    private void showSearchPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String kw = request.getParameter("kw");
        if (kw == null) {
            kw = "";
        }
        List<Product> results = productDAO.searchProductByKw(kw);
        List<Category> listC = categoryDAO.getAllCategories();
        request.setAttribute("listP",results);
        request.setAttribute("listCC", listC);
        request.setAttribute("kw", kw);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
    }

    private void showHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId = -1;
        if (request.getParameter("id") != null) {
            categoryId = Integer.parseInt(request.getParameter("id"));
        }
        List<Product> list = productDAO.getAllProductByCategoryId(categoryId);
        List<Category> listC = categoryDAO.getAllCategories();
        request.setAttribute("listP", list);
        request.setAttribute("listCC", listC);
//        request.setAttribute("p", last);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
