package com.FoodDelivery.AdminService.service;

import com.FoodDelivery.AdminService.Repository.UserRepository;
import com.FoodDelivery.AdminService.model.Orders;
import com.FoodDelivery.AdminService.model.Restaurant;
import com.FoodDelivery.AdminService.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {
@Autowired
UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }



    public ResponseEntity<Object> updateUser(User user) {
        try {
            userRepository.save(user);
            return ResponseEntity.status(200).body("Updated Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("failed to update");
        }
    }


    public List<Orders> getAllOrdersDetails(String userId) {
        String url = "http://localhost:5001/orders"+userId;
        try {

            ResponseEntity<List<Orders>> response = restTemplate.exchange(url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Orders>>() {
                    }
            );

            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to fetch orders");
        }
    }
    public List<Restaurant> getAllRestaurantDetails() {
        String url = "http://localhost:5002/restaurant";
        try {

            ResponseEntity<List<Restaurant>> response = restTemplate.exchange(url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Restaurant>>() {
                    }
            );

            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to fetch Restaurant");
        }
    }


    public ResponseEntity<Object> placeOrder(Orders orders) {
        String url = "http://localhost:5001/orders";
        try {


             return restTemplate.postForEntity(url, orders, Object.class);
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to fetch Restaurant");
        }
    }
}
