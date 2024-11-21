package com.FoodDelivery.ResturantService.service;


import com.FoodDelivery.ResturantService.Repository.RestaruntRepository;
import com.FoodDelivery.ResturantService.model.Orders;
import com.FoodDelivery.ResturantService.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaruntRepository restaruntRepository;
    @Autowired
    RestTemplate restTemplate;

    public List<Restaurant> fetchAllRestaurant() {
        String url = "http://localhost:5002/restaurants";
        try {

            ResponseEntity<List<Restaurant>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Restaurant>>() {}
            );

            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to fetch restaurants");
        }
    }



    public List<Restaurant> getAllRestaurant() {
        return restaruntRepository.findAll();
    }

    public Restaurant getRestaurantById(String id) {
       return restaruntRepository.findById(id).orElse(null);

    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaruntRepository.save(restaurant);
    }


    public List<Orders> fetchAllOrders(String restaurantId) {
        if(getRestaurantById(restaurantId)==null){
            return null;
        }
        String url = "http://localhost:5001/order/restaurant/"+restaurantId;
        try {

            ResponseEntity<List<Orders>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Orders>>() {}
            );

            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to fetch orders");
        }
    }

    public List<Orders> fetchOrdersbyRestaurant(String restaurantId) {
        String url = "http://localhost:5001/orders/restaurant/"+restaurantId;
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

    public String updateOrdersStatus(Orders orders) {
        String url = "http://localhost:5001/orders/update";
        try {


             restTemplate.put(url, orders);
             return "updated Successfully";
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to fetch Restaurant");
        }

    }
}

