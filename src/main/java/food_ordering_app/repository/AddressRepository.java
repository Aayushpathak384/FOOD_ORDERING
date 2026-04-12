package food_ordering_app.repository;

import food_ordering_app.Exception.NotFoundException;
import food_ordering_app.entity.Address;
import food_ordering_app.repository.JPA.AddressJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class AddressRepository {
    private final AddressJPARepository addressJPARepository;

    @Autowired
    public AddressRepository(AddressJPARepository addressJPARepository) {
        this.addressJPARepository = addressJPARepository;
    }

    public List<Address> findAllAddress() {
        return this.addressJPARepository.findAll();
    }

    public Address findAddressById(UUID uuid) {
        return this.addressJPARepository.findById(uuid).orElseThrow(() -> new NotFoundException(Address.class, "uuid", uuid));
    }

    public void save_update(Address address) {
        this.addressJPARepository.save(address);
    }

    public void deleteById(UUID uuid) {
        this.addressJPARepository.deleteById(uuid);
    }
}
