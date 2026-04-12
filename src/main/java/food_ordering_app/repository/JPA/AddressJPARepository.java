package food_ordering_app.repository.JPA;

import food_ordering_app.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressJPARepository extends JpaRepository<Address, UUID> {
}
