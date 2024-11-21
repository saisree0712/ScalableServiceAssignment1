package com.FoodDelivery.ResturantService.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data  // Lombok annotation that generates getters, setters, toString, equals, and hashCode methods
@Document(collection = "Restaurant")
public class Restaurant {
    @Id
    private String restaurantId;
    private String restaurantName;
    private String restaurantLocation;
    private String cuisine;
    private List<MenuItem> menu;

    // Nested class for menu items
    @Data  // Lombok annotation for MenuItem class
    public static class MenuItem {
        private String item;
        private double price;
    }
}
