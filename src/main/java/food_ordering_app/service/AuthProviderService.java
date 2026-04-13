package food_ordering_app.service;

import food_ordering_app.entity.AuthProvider;
import food_ordering_app.repository.AuthProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthProviderService {
    private final AuthProviderRepository authProviderRepository;

    @Autowired
    public AuthProviderService(AuthProviderRepository authProviderRepository) {
        this.authProviderRepository = authProviderRepository;
    }

    public List<AuthProvider> getAllAuthProvider() {
        return this.authProviderRepository.findAllAuthProvider();
    }

    public AuthProvider getAuthProviderById(UUID id) {
        return this.authProviderRepository.findAuthProviderById(id);
    }

    public void createAuthProvider(AuthProvider authProvider) {
        this.authProviderRepository.save_update(authProvider);
    }

    public void updateAuthProvider(AuthProvider authProvider) {
        this.authProviderRepository.save_update(authProvider);
    }

    public void deleteAuthProviderById(UUID id) {
        this.authProviderRepository.deleteById(id);
    }
}