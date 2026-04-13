package food_ordering_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auth_provider", uniqueConstraints = {
        @UniqueConstraint(name = "uk_provider_provider_id", columnNames = {"provider", "provider_id"})
})
public class AuthProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(name = "provider_id", nullable = false)
    private String providerId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
