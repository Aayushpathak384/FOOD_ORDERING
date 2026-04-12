package food_ordering_app.repository;

import food_ordering_app.Exception.NotFoundException;
import food_ordering_app.entity.AuthProvider;
import food_ordering_app.repository.JPA.AuthProviderJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class AuthProviderRepository {
    private final AuthProviderJPARepository authProviderJPARepository;

    @Autowired
    public AuthProviderRepository(AuthProviderJPARepository authProviderJPARepository) {
        this.authProviderJPARepository = authProviderJPARepository;
    }

    public List<AuthProvider> findAllAuthProvider() {
        return this.authProviderJPARepository.findAll();
    }

    public AuthProvider findAuthProviderById(UUID uuid) {
        return this.authProviderJPARepository.findById(uuid).orElseThrow(() -> new NotFoundException(AuthProvider.class, "uuid", uuid));
    }

    public void save_update(AuthProvider authProvider) {
        this.authProviderJPARepository.save(authProvider);
    }

    public void deleteById(UUID uuid) {
        this.authProviderJPARepository.deleteById(uuid);
    }
}
