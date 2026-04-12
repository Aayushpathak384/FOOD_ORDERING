package food_ordering_app.repository;

import food_ordering_app.Exception.NotFoundException;
import food_ordering_app.entity.Customer;
import food_ordering_app.repository.JPA.CustomerJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository

public class CustomerRepository {
    private final CustomerJPARepository customerJPARepository;
    @Autowired

    public CustomerRepository(CustomerJPARepository customerJPARepository) {
        this.customerJPARepository = customerJPARepository;
    }
    public List<Customer> FindAllCustomer(){
        return this.customerJPARepository.findAll();
    }
    public Customer FindCustomerById(String email){
        return this.customerJPARepository.findById(email).orElseThrow(()->new NotFoundException(Customer.class ,"email" , email));
    }
    public void save_update(Customer customer){
        this.customerJPARepository.save(customer);
    }
    public void DeleteById(String email){
        this.customerJPARepository.deleteById(email);
    }
}
