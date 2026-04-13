package food_ordering_app.service;

import food_ordering_app.dto.request.OrdersRequest;
import food_ordering_app.entity.*;
import food_ordering_app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final MenuItemRepository menuItemRepository;
    private final CustomerRepository customerRepository;
    private final OrderItemRepository orderItemRepository;
    private final RestaurantRepository restaurantRepository;
    @Autowired

    public OrdersService(OrdersRepository ordersRepository, MenuItemRepository menuItemRepository, CustomerRepository customerRepository, OrderItemRepository orderItemRepository, RestaurantRepository restaurantRepository) {
        this.ordersRepository = ordersRepository;
        this.menuItemRepository = menuItemRepository;
        this.customerRepository = customerRepository;
        this.orderItemRepository = orderItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<Orders> findAllOrders() {
        return this.ordersRepository.findAllOrders();
    }

    public Orders findOrderById(UUID orderId) {
        return this.ordersRepository.findOrdersById(orderId);
    }

    public List<Orders> findOrdersByCustomerEmail(String email) {
        Customer customer = this.customerRepository.FindCustomerById(email);
        if (customer.getOrdersList() == null) {
            return List.of();
        }
        return customer.getOrdersList();
    }

    public Orders updateOrderStatus(UUID orderId, OrderStatus newStatus) {
        Orders orders = this.ordersRepository.findOrdersById(orderId);
        orders.setOrderStatus(newStatus);
        this.ordersRepository.save_update(orders);
        return orders;
    }

    public void cancelOrder(UUID orderId) {
        Orders orders = this.ordersRepository.findOrdersById(orderId);
        if (orders.getOrderStatus() == OrderStatus.CANCELLED) {
            return;
        }
        if (orders.getOrderStatus() == OrderStatus.DELIVERED) {
            throw new IllegalArgumentException("Delivered order cannot be cancelled");
        }

        if (orders.getOrderItemList() != null) {
            for (OrderItem orderItem : orders.getOrderItemList()) {
                MenuItem menuItem = orderItem.getMenuItem();
                if (menuItem != null) {
                    menuItem.setCount(menuItem.getCount() + orderItem.getQuantity());
                    this.menuItemRepository.save_update(menuItem);
                }
            }
        }

        orders.setOrderStatus(OrderStatus.CANCELLED);
        this.ordersRepository.save_update(orders);
    }

    //create order
    public void CreateOrder(OrdersRequest ordersRequest , String email){
        Customer customer = this.customerRepository.FindCustomerById(email);
        Restaurant restaurant = this.restaurantRepository.findRestaurantById(ordersRequest.getReustrantId());
        Double TotalPrice = ordersRequest.getOrderItemIds().stream()
                .mapToDouble(el ->{
                    OrderItem orderItem = this.orderItemRepository.findOrderItemById(el);
                    int count = orderItem.getMenuItem().getCount();
                    if(count< orderItem.getQuantity())
                        throw new IllegalArgumentException("count is less than orderItem");
                    return orderItem.getMenuItem().getPrice()*orderItem.getQuantity();
                }).sum();
        if(restaurant == null) throw new IllegalArgumentException("resturent not present");
        Orders orders = new Orders();
        orders.setOrderStatus(OrderStatus.PENDING);
        List<OrderItem> orderItemList = ordersRequest.getOrderItemIds().stream()
                        .map(el->
                        {
                            OrderItem orderItem = this.orderItemRepository.findOrderItemById(el);
                            MenuItem menuItem  = orderItem.getMenuItem();
                            int count  = orderItem.getMenuItem().getCount();
                            menuItem.setCount(count - orderItem.getQuantity());
                            this.menuItemRepository.save_update(menuItem);
                            return orderItem;
                        })
                                .toList();

        orders.setOrderItemList(orderItemList);
        orders.setTotalPrice(TotalPrice);
        orders.setCustomer(customer);
        orders.setRestaurant(restaurant);
        orders.setAddress(ordersRequest.getAddress());

        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrders(orders);
            this.orderItemRepository.save_update(orderItem);
        }

        List<Orders> ordersList = customer.getOrdersList();
        if (ordersList == null) {
            ordersList = new ArrayList<>();
            customer.setOrdersList(ordersList);
        }
        ordersList.add(orders);

        List<Orders> ordersList_resturent = restaurant.getOrdersList();
        if (ordersList_resturent == null) {
            ordersList_resturent = new ArrayList<>();
            restaurant.setOrdersList(ordersList_resturent);
        }
        ordersList_resturent.add(orders);

        this.restaurantRepository.save_update(restaurant);
        this.customerRepository.save_update(customer);
        this.ordersRepository.save_update(orders);
    }
}