package food_ordering_app.service;

import food_ordering_app.entity.Address;
import food_ordering_app.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddress() {
        return this.addressRepository.findAllAddress();
    }

    public Address getAddressById(UUID id) {
        return this.addressRepository.findAddressById(id);
    }

    public void createAddress(Address address) {
        this.addressRepository.save_update(address);
    }

    public void updateAddress(Address address) {
        this.addressRepository.save_update(address);
    }

    public void deleteAddressById(UUID id) {
        this.addressRepository.deleteById(id);
    }
}