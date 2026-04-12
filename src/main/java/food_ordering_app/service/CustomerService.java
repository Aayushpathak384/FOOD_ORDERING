package food_ordering_app.service;

import food_ordering_app.Exception.NotFoundException;
import food_ordering_app.dto.request.AddressRequest;
import food_ordering_app.dto.request.CustomerRequest;
import food_ordering_app.dto.request.OrdersRequest;
import food_ordering_app.entity.*;
import food_ordering_app.repository.AddressRepository;
import food_ordering_app.repository.CustomerRepository;
import food_ordering_app.repository.OrderItemRepository;
import food_ordering_app.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrdersRepository ordersRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository, OrderItemRepository orderItemRepository, OrdersRepository ordersRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.orderItemRepository = orderItemRepository;
        this.ordersRepository = ordersRepository;
    }

    //create a Customer
    public String SaveCustomer(Customer customer){
        if (customer == null || customer.getEmail() == null || customer.getEmail().isBlank()) {
            throw new IllegalArgumentException("customer email is required");
        }

        try {
            this.customerRepository.FindCustomerById(customer.getEmail());
            return "user Already exists";
        } catch (NotFoundException ex) {
            this.customerRepository.save_update(customer);
            return "Account Created";
        }
    }
    //find a Customer
    public Customer FindCustomerById(String email){
        return this.customerRepository.FindCustomerById(email);
    }
    public void DeleteCustomerById(String email){
        this.customerRepository.DeleteById(email);
    }
    //delete a customer
    //loginpart
    public boolean CustomerLogin(CustomerRequest customerRequest){
        if (customerRequest == null || customerRequest.getEmail() == null || customerRequest.getEmail().isBlank()) {
            throw new IllegalArgumentException("email is required");
        }

        Customer customer = this.customerRepository.FindCustomerById(customerRequest.getEmail());
        return Objects.equals(customer.getPassword(), customerRequest.getPassword());
    }

    public void AddAddressCustomer(AddressRequest addressRequest , String email){
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email is required");
        }
        if (addressRequest == null) {
            throw new IllegalArgumentException("addressRequest is required");
        }

        Customer customer = this.customerRepository.FindCustomerById(email);
        List<Address> addressList = customer.getAddressList();
        if (addressList == null) {
            addressList = new ArrayList<>();
        }

        Address address = new Address();
        address.setCustomer(customer);
        address.setStreet(addressRequest.getStreet());
        address.setState(addressRequest.getState());
        address.setPincode(addressRequest.getPincode());

        addressList.add(address);
        customer.setAddressList(addressList);

        this.addressRepository.save_update(address);
        this.customerRepository.save_update(customer);
    }
    //Add order to customer
    public void AddOrdersCustomer(OrdersRequest ordersRequest, String email){
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email is required");
        }
        if (ordersRequest == null) {
            throw new IllegalArgumentException("ordersRequest is required");
        }
        if (ordersRequest.getOrderItemIds() == null || ordersRequest.getOrderItemIds().isEmpty()) {
            throw new IllegalArgumentException("orderItemIds is required");
        }

        Customer customer = this.customerRepository.FindCustomerById(email);

        Orders orders = new Orders();
        orders.setCustomer(customer);
        orders.setOrderStatus(OrderStatus.PREPARING);
        List<OrderItem> orderItemList = ordersRequest.getOrderItemIds().stream()
                        .map(el->this.orderItemRepository.findOrderItemById(el))
                                .toList();

        double totalPrice = orderItemList.stream()
                        .mapToDouble(el -> el.getQuantity()* el.getMenuItem().getPrice())
                                .sum();
        orders.setTotalPrice(totalPrice);
        orders.setOrderItemList(orderItemList);

        this.ordersRepository.save_update(orders);

        orderItemList.forEach(item -> {
            item.setOrders(orders);
            this.orderItemRepository.save_update(item);
        });

        List<Orders> customerOrdersList = customer.getOrdersList();
        if (customerOrdersList == null) {
            customerOrdersList = new ArrayList<>();
        }
        customerOrdersList.add(orders);
        customer.setOrdersList(customerOrdersList);

        this.customerRepository.save_update(customer);
    }
}
