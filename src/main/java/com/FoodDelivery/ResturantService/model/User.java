package com.FoodDelivery.ResturantService.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data

public class User {
    @Id
    private String userId;
    private String username;
    private String email;
    private String password;
    private boolean admin;


}
