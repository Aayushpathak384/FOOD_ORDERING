package food_ordering_app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthProviderRequest {
    private String provider;
    private String providerId;
    private UUID customerId;
}
