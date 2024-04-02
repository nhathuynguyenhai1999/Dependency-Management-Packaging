package com.cg.case_shopping.dao;

import com.cg.case_shopping.context.DBContext;
import com.cg.case_shopping.entity.Category;
import com.cg.case_shopping.entity.Product;
import javax.naming.directory.SearchResult;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    static Connection conn = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;

    public void saveProduct(Product newProduct, HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
        // Lưu sản phẩm vào cơ sở dữ liệu
        ProductDAO productDAO = new ProductDAO();
        productDAO.saveProduct(newProduct);
        // Chuyển hướng người dùng đến trang quản lý sản phẩm
        resp.sendRedirect(req.getContextPath() + "/products");
    }


    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM product p join category c where p.category_id = c.cid order by p.id;"; // Corrected SQL query
        try {
            conn = new DBContext().getConnection(); // Open connection
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getInt("cid"), rs.getString("cname"));
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"), // Corrected column name
                        rs.getString("image"),
                        rs.getDouble("price"), // Corrected column name
                        rs.getString("title"), // Corrected column name
                        rs.getString("description"),
                        c)); // Corrected column name
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return list;
    }

    public List<Product> getAllProductByCategoryId(int categoryId) {
        List<Product> list = new ArrayList<>();
        // Corrected SQL query
        try {
            conn = new DBContext().getConnection(); // Open connection
            String query = null;
            if(categoryId == -1){
                query = "SELECT * FROM product p join category c " +
                        "where p.category_id = c.cid order by p.id;";
                ps = conn.prepareStatement(query);
            }else{
                query = "SELECT * FROM product p join category c " +
                        "where p.category_id = c.cid and p.category_id = ? order by p.id;";
                ps = conn.prepareStatement(query);
                ps.setInt(1, categoryId);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getInt("cid"), rs.getString("cname"));
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"), // Corrected column name
                        rs.getString("image"),
                        rs.getDouble("price"), // Corrected column name
                        rs.getString("title"), // Corrected column name
                        rs.getString("description"),
                        c)); // Corrected column name
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return list;
    }
    public void updateProduct(Product updatedProduct){
        try {
            // Get a connection to the database
            Connection connection = new DBContext().getConnection();
            // Prepare the SQL statement to update the product information
            String sql = "UPDATE product SET name = ?, image = ?, price = ?, title = ?, description = ?, category_id = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            // Set the parameters for the SQL statement
            ps.setString(1,updatedProduct.getName());
            ps.setString(2,updatedProduct.getImage());
            ps.setDouble(3, updatedProduct.getPrice());
            ps.setString(4,updatedProduct.getTitle());
            ps.setString(5,updatedProduct.getDescription());
            ps.setInt(6,updatedProduct.getCategory().getCid());
            ps.setInt(7,updatedProduct.getId());
            // Execute the SQL statement to update the product
            int rowsAffected = ps.executeUpdate();
            // Close the PreparedStatement and Connection
            ps.close();
            connection.close();
            // Check if the product was successfully updated
            if (rowsAffected > 0){
                // Product update successfully
                System.out.println("Product updated successfully");
            }
            // Product update failed
            else {
                System.out.println("Product update failed");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Product getProductById(int productID) {
        Product product = null;
        String query = "SELECT * FROM product WHERE id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"), // Corrected column name
                        rs.getString("image"),
                        rs.getDouble("price"), // Corrected column name
                        rs.getString("title"), // Corrected column name
                        rs.getString("description")); // Corrected column name
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return product;
    }

    public void deleteProduct(int productID) {
        String query = "DELETE FROM product WHERE id = ?";
        try (Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, productID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    private static void closeResources() {
        try {
            if (rs != null){
                rs.close();
            }
            if (ps != null){
                ps.close();
            }
            if (conn != null){
                conn.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteProduct(int productID,HttpServletRequest request, HttpServletResponse response) {
        String query = "DELETE FROM product WHERE id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }
    private void saveProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productIdStr = req.getParameter("productID");
        if (productIdStr == null || productIdStr.isEmpty()) {
            // Xử lý khi productID không được gửi hoặc là chuỗi rỗng
            // Ví dụ: Hiển thị thông báo lỗi, quay trở lại trang trước đó, hoặc thực hiện hành động khác
            // Ở đây, tôi sẽ chỉ in ra console để debug:
            System.out.println("Product ID is missing or empty.");
            return;
        }

        try {
            int productID = Integer.parseInt(productIdStr);
            // Tiếp tục xử lý khi productID hợp lệ
            // ...
        } catch (NumberFormatException e) {
            // Xử lý ngoại lệ khi không thể chuyển đổi productIdStr thành số nguyên
            // Ví dụ: Hiển thị thông báo lỗi, quay trở lại trang trước đó, hoặc thực hiện hành động khác
            // Ở đây, tôi sẽ chỉ in ra console để debug:
            System.out.println("Invalid product ID format: " + productIdStr);
            e.printStackTrace();
        }
    }

    public void saveProduct(Product newProduct) {
        try {
            // Get a connection to the database
            Connection connection = new DBContext().getConnection();
            // Prepare the SQL statement to insert the product
            String sql = "INSERT INTO product (name, image, price, title, description, category_id) VALUES (?, ?, ?,?,?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            // Set the parameters for the SQL statement
            ps.setString(1, newProduct.getName());
            ps.setString(2, newProduct.getImage());
            ps.setDouble(3, newProduct.getPrice());
            ps.setString(4, newProduct.getTitle());
            ps.setString(5, newProduct.getDescription());
            ps.setInt(6, newProduct.getCategory().getCid());
            int rowsAffected = ps.executeUpdate();// Execute the SQL statement to insert the product
            // Close the PreparedStatement and Connection
            ps.close();
            connection.close();
            // Check if the product was successfully inserted
            if (rowsAffected > 0){
                // Product inserted successfully
                System.out.println("Product insert successfully");
            }else {
                // Product insert failed
                System.out.println("Product insert failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the PreparedStatement and Connection
            if (ps != null){
                try {
                    ps.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
                }
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Product> searchProductByKw(String kw) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM product p join category c " +
                "where p.category_id = c.cid and p.name like ? " +
                "order by p.id;"; // Corrected SQL query
        try {
            conn = new DBContext().getConnection(); // Open connection
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + kw + "%");

            System.out.println("query: " + ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getInt("cid"), rs.getString("cname"));
                list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"), // Corrected column name
                        rs.getString("image"),
                        rs.getDouble("price"), // Corrected column name
                        rs.getString("title"), // Corrected column name
                        rs.getString("description"),
                        c)); // Corrected column name
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return list;
    }
}


