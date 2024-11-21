package com.FoodDelivery.AdminService.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Orders {
    @Id
    private String orderId;
    private String userId;
    private String restaurantId;
    private double totalPrice;
    private String status;

    private String foodItem;
    private int quantity;

    private Long orderedAt;



}
