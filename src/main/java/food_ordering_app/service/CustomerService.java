package food_ordering_app.service;

import food_ordering_app.entity.Customer;
import food_ordering_app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomer() {
        return this.customerRepository.FindAllCustomer();
    }

    public Customer getCustomerByEmail(String email) {
        return this.customerRepository.FindCustomerById(email);
    }

    public void createCustomer(Customer customer) {
        this.customerRepository.save_update(customer);
    }

    public void updateCustomer(Customer customer) {
        this.customerRepository.save_update(customer);
    }

    public void deleteCustomerByEmail(String email) {
        this.customerRepository.DeleteById(email);
    }
}