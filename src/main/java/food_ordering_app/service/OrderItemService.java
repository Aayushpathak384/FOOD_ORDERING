package food_ordering_app.service;

import food_ordering_app.entity.OrderItem;
import food_ordering_app.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> getAllOrderItem() {
        return this.orderItemRepository.findAllOrderItem();
    }

    public OrderItem getOrderItemById(UUID id) {
        return this.orderItemRepository.findOrderItemById(id);
    }

    public void createOrderItem(OrderItem orderItem) {
        this.orderItemRepository.save_update(orderItem);
    }

    public void updateOrderItem(OrderItem orderItem) {
        this.orderItemRepository.save_update(orderItem);
    }

    public void deleteOrderItemById(UUID id) {
        this.orderItemRepository.deleteById(id);
    }
}