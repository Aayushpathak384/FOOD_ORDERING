package food_ordering_app.repository.JPA;

import food_ordering_app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CustomerJPARepository extends JpaRepository<Customer , UUID> {
}
