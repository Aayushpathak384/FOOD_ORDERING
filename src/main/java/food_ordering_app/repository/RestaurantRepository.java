package food_ordering_app.repository;

import food_ordering_app.Exception.NotFoundException;
import food_ordering_app.entity.Restaurant;
import food_ordering_app.repository.JPA.RestaurantJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class RestaurantRepository {
    private final RestaurantJPARepository restaurantJPARepository;

    @Autowired
    public RestaurantRepository(RestaurantJPARepository restaurantJPARepository) {
        this.restaurantJPARepository = restaurantJPARepository;
    }

    public List<Restaurant> findAllRestaurant() {
        return this.restaurantJPARepository.findAll();
    }

    public Restaurant findRestaurantById(UUID uuid) {
        return this.restaurantJPARepository.findById(uuid).orElseThrow(() -> new NotFoundException(Restaurant.class, "uuid", uuid));
    }

    public void save_update(Restaurant restaurant) {
        this.restaurantJPARepository.save(restaurant);
    }

    public void deleteById(UUID uuid) {
        this.restaurantJPARepository.deleteById(uuid);
    }
}
