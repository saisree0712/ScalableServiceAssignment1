package com.FoodDelivery.AdminService.Controller;

import com.FoodDelivery.AdminService.Repository.UserRepository;
import com.FoodDelivery.AdminService.model.Orders;
import com.FoodDelivery.AdminService.model.Restaurant;
import com.FoodDelivery.AdminService.model.User;
import com.FoodDelivery.AdminService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;



@RestController
public class AdminController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    UserService userService;



    @PutMapping("/users")
    public ResponseEntity <Object> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUsers(@PathVariable String id) {
         userRepository.deleteById(id);
        return "Successfully Deleted";

    }

        @GetMapping("/users/{id}")
        public User getUsers(@PathVariable String id) {
            return userRepository.findById(id).orElse(new User());

        }


        @GetMapping("/users")
        public ResponseEntity<List<User>> getAllUsers() {
            return ResponseEntity.ok(userService.getAllUsers());
        }


        @PostMapping("/users/Registration")
        public ResponseEntity<User> saveUser(@RequestBody User user) {
            return ResponseEntity.ok(userService.saveUser(user));
        }

        @GetMapping("/user/restaurant")
        public List<Restaurant> fetchAllRestaurant() {
            return userService.getAllRestaurantDetails();
        }

        @GetMapping("/user/orders/{userId}")
        public List<Orders> fetchAllOrders(@PathVariable String userId) {
            return userService.getAllOrdersDetails(userId);
        }

    @PostMapping("/user/orders")
    public ResponseEntity<Object> placeOrders(@RequestBody Orders orders) {
        return userService.placeOrder(orders);
    }





    }


