package com.cg.case_shopping.service;

public class LoginService {
    // You can implement your login logic here, such as checking username and password against database
    public boolean authenticate(String username,String password){
        // For demo purposes, let's assume authentication is successful if username and password are not empty
        return username != null && !username.isEmpty() && password != null && !password.isEmpty();
    }
}
