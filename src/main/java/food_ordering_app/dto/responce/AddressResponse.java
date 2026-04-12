package food_ordering_app.dto.responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {
    private UUID id;
    private String street;
    private String state;
    private String pincode;
    private UUID customerId;
    private UUID restaurantId;
}
