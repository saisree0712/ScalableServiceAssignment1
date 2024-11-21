package com.FoodDelivery.AdminService.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.awt.*;
import java.util.List;
@Data
public class Restaurant {
    @Id
    private String restaurantId;
    private String restaurantName;
    private String restaurantLocation;
    private String cuisine;
    private List<MenuItem> menu;

    @Data
    public static class MenuItem {
        private String item;
        private double price;

        // Getters and Setters
    }

    // Nested class for menu items

}
