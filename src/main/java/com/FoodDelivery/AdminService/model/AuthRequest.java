package com.FoodDelivery.AdminService.model;

import lombok.Data;

@Data
public class AuthRequest {
    private String userName;
    private String password;
    private String code; // Encrypted username and password

    // Constructor, getters, and setters
    public AuthRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.code = encrypt(userName, password);
    }

    private String encrypt(String userName, String password) {
        // Implement encryption logic here
        return userName + "." + password;
    }

    // Getters and Setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
