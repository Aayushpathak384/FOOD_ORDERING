package food_ordering_app.service;

import food_ordering_app.entity.Restaurant;
import food_ordering_app.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAllRestaurant() {
        return this.restaurantRepository.findAllRestaurant();
    }

    public Restaurant getRestaurantById(UUID id) {
        return this.restaurantRepository.findRestaurantById(id);
    }

    public Restaurant getRestaurantByEmail(String email) {
        return this.restaurantRepository.findRestaurantByEmail(email);
    }

    public void createRestaurant(Restaurant restaurant) {
        this.restaurantRepository.save_update(restaurant);
    }

    public void updateRestaurant(Restaurant restaurant) {
        this.restaurantRepository.save_update(restaurant);
    }

    public void deleteRestaurantById(UUID id) {
        this.restaurantRepository.deleteById(id);
    }
}