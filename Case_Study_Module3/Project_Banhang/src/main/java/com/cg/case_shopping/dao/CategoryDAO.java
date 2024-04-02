package com.cg.case_shopping.dao;

import com.cg.case_shopping.context.DBContext;
import com.cg.case_shopping.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    static Connection connection = null;
    public List<Category> getAllCategories(){
        List<Category> list = new ArrayList<>();
        String query = "SELECT * from category";
        try {
            connection = new DBContext().getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int categoryID = rs.getInt("cid");
                String categoryName = rs.getString("cname");
                Category category = new Category(categoryID,categoryName);
                list.add(category);
            }
            rs.close();
            ps.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return list;
    }
    private static void closeConnection(){
        try {
            if (connection != null){
                connection.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Category findCategoryById(int categoryId) {
        Category category = null;
        String query = "SELECT * FROM category WHERE cid = ?";
        try {
            connection = new DBContext().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                category = new Category(resultSet.getInt("cid"),
                        resultSet.getString("cname"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            closeConnection();
        }
        return category;
    }
}
