package com.cg.case_shopping.control;

import com.cg.case_shopping.context.DBContext;
import com.cg.case_shopping.dao.CategoryDAO;
import com.cg.case_shopping.dao.ProductDAO;
import com.cg.case_shopping.entity.Category;
import com.cg.case_shopping.entity.Product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;
    public void init() {
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showFormCreate(request, response);
                break;
            case "edit":
                showFormEdit(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            default:
                showListProducts(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Nhận id sản phẩm cần sửa từ request
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                saveProduct(req, resp);
                break;
            case "edit":
                updateProduct(req,resp);
            default:
//                showListProducts(request, response);
        }
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        try {
            // Retrieve product information from the request parameters
            String name = req.getParameter("name");
            String img = req.getParameter("image");
            double price = Double.parseDouble(req.getParameter("price"));
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            int productId = Integer.parseInt(req.getParameter("id"));
            // Get the existing product from the database
            Product existingProduct = ProductDAO.getProductById(productId);
            if (existingProduct != null){
                // Update the product information
                existingProduct.setName(name);
                existingProduct.setImage(img);
                existingProduct.setPrice(price);
                existingProduct.setTitle(title);
                existingProduct.setDescription(description);
                // Retrieve the category information from the database
                int categoryId = Integer.parseInt(req.getParameter("category"));
                Category category = categoryDAO.getAllCategories().get(categoryId);
                if(category != null){
                    existingProduct.setCategory(category);
                }else {
                    // Handle case where category does not exist
                    System.out.println("Category with ID " + categoryId + " does not exist.");
                }
                productDAO.updateProduct(existingProduct);
                resp.sendRedirect(req.getContextPath() + "/products");
            } else {
                // Handle case where product does not exist
                System.out.println("Product with ID " + productId + " does not exist.");
                resp.sendRedirect(req.getContextPath() + "/products");
            }
        } catch (NumberFormatException e){
            // Handle case where there's an error in parsing numeric values
            e.printStackTrace();
            // You can forward to an error page or display a message on the same page
            // For simplicity, I'm redirecting back to the product list page
            resp.sendRedirect(req.getContextPath() + "/products");
        }
    }

    private void saveProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        // Retrieve product information from the request parameters
        String name = req.getParameter("name");
        String img = req.getParameter("image");
        double price = Double.parseDouble(req.getParameter("price"));
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        int categoryId = Integer.parseInt(req.getParameter("category"));
        Category category = categoryDAO.findCategoryById(categoryId);
        // Create a Product object with the retrieved information
        //public Product(int id, String name, String image, double price, String title, String description)
        Product newProduct = new Product(-1, name,img,price,title,description, category);
        // Insert the product into the database using a ProductDAO
        productDAO.saveProduct(newProduct);
        resp.sendRedirect(req.getContextPath() + "/products");
    }

    private void showListProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productDAO.getAllProduct();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/ManagerProduct.jsp").forward(request, response);
    }

    private void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("id"));
        Product product = ProductDAO.getProductById(productID);
        request.setAttribute("productEdit", product);

        List<Category> categories = categoryDAO.getAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/edit-product.jsp").forward(request, response);
    }

    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Category> categories = categoryDAO.getAllCategories();

        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/create-product.jsp").forward(request,response);
    }
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productID = Integer.parseInt(request.getParameter("id"));
        productDAO.deleteProduct(productID);
        response.sendRedirect(request.getContextPath() + "/products");
    }
}

