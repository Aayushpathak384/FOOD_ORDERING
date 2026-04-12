package food_ordering_app.repository;

import food_ordering_app.Exception.NotFoundException;
import food_ordering_app.entity.OrderItem;
import food_ordering_app.repository.JPA.OrderItemJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class OrderItemRepository {
    private final OrderItemJPARepository orderItemJPARepository;

    @Autowired
    public OrderItemRepository(OrderItemJPARepository orderItemJPARepository) {
        this.orderItemJPARepository = orderItemJPARepository;
    }

    public List<OrderItem> findAllOrderItem() {
        return this.orderItemJPARepository.findAll();
    }

    public OrderItem findOrderItemById(UUID uuid) {
        return this.orderItemJPARepository.findById(uuid).orElseThrow(() -> new NotFoundException(OrderItem.class, "uuid", uuid));
    }

    public void save_update(OrderItem orderItem) {
        this.orderItemJPARepository.save(orderItem);
    }

    public void deleteById(UUID uuid) {
        this.orderItemJPARepository.deleteById(uuid);
    }
}
