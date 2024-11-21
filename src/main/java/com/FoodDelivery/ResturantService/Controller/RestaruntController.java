package com.FoodDelivery.ResturantService.Controller;


import com.FoodDelivery.ResturantService.Repository.RestaruntRepository;
import com.FoodDelivery.ResturantService.model.Orders;
import com.FoodDelivery.ResturantService.model.Restaurant;
import com.FoodDelivery.ResturantService.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

public class RestaruntController {

        @Autowired
        RestaruntRepository restaurantRepository;

        @Autowired
    RestaurantService restaurantService;


        @PostMapping("/restaurant")
        public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
            return restaurantRepository.save(restaurant);
        }
    @PutMapping("/restaurant")
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @GetMapping("/restaurant/menu/{restaurantId}")
    public List<Restaurant.MenuItem> getAllItemsInRestaurant(@PathVariable String restaurantId) {
        return restaurantRepository.findById(restaurantId).orElse(new Restaurant()).getMenu();
    }

    @GetMapping("/restaurant/orders/{restaurantId}")
    public List<Orders> fetchOrdersbyRestaurant(@PathVariable String restaurantId) {
        return restaurantService.fetchOrdersbyRestaurant(restaurantId);
    }

        @GetMapping("/restaurant")
        public List<Restaurant> getAllRestaurants() {
            return restaurantRepository.findAll();
        }

    @PutMapping("/restaurant/orders")
    public String updateOrdersStatus(@RequestBody Orders orders) {
        return restaurantService.updateOrdersStatus(orders);
    }

    }


