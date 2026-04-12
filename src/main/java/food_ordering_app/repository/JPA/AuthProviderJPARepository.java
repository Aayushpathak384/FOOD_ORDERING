package food_ordering_app.repository.JPA;

import food_ordering_app.entity.AuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthProviderJPARepository extends JpaRepository<AuthProvider, UUID> {
}
