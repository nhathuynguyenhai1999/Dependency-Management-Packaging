package com.cg.case_shopping.context;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBContext {
    /*USE BELOW METHOD FOR YOUR DATABASE CONNECTION FOR BOTH SINGLE AND MULTILPE SQL SERVER INSTANCE(s)*/
    /*DO NOT EDIT THE BELOW METHOD, YOU MUST USE ONLY THIS ONE FOR YOUR DATABASE CONNECTION*/
    public Connection getConnection() throws Exception {
        // MySQL default port
        String portNumber = "3306";
        /*Insert your other code right after this comment*/
        /*Change/update information of your database connection, DO NOT change name of instance variables in this class*/
        String serverName = "localhost";
        String dbName = "wish";
        String url = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        String userID = "root";
        String password = "0848101999";
        return DriverManager.getConnection(url, userID, password);
    }
}

 