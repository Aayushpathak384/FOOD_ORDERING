package food_ordering_app.repository;

import food_ordering_app.Exception.NotFoundException;
import food_ordering_app.entity.Orders;
import food_ordering_app.repository.JPA.OrdersJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class OrdersRepository {
    private final OrdersJPARepository ordersJPARepository;

    @Autowired
    public OrdersRepository(OrdersJPARepository ordersJPARepository) {
        this.ordersJPARepository = ordersJPARepository;
    }

    public List<Orders> findAllOrders() {
        return this.ordersJPARepository.findAll();
    }

    public Orders findOrdersById(UUID uuid) {
        return this.ordersJPARepository.findById(uuid).orElseThrow(() -> new NotFoundException(Orders.class, "uuid", uuid));
    }

    public void save_update(Orders orders) {
        this.ordersJPARepository.save(orders);
    }

    public void deleteById(UUID uuid) {
        this.ordersJPARepository.deleteById(uuid);
    }
}
