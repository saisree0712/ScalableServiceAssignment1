package com.FoodDelivery.AdminService.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "UserDetails" )
public class User {
    @Id
    private String userId;
    private String username;
    private String email;
    private String password;
    private boolean admin;



}
