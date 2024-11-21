package com.FoodDelivery.ResturantService.Repository;

import com.FoodDelivery.ResturantService.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaruntRepository extends MongoRepository<Restaurant, String> {
}

