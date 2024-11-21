package com.FoodDelivery.AdminService.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDetails {
    public List<String> claims;
    public String userName;
    public String password;

    public UserDetails(String userName, String password, List<String> claims) {
        this.userName = userName;
        this.password =password;
        this.claims = new ArrayList<>();
    }
}
