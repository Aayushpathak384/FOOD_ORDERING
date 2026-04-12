package food_ordering_app.dto.responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthProviderResponse {
    private UUID id;
    private String provider;
    private String providerId;
    private UUID customerId;
}
