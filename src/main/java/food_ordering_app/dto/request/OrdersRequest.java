package food_ordering_app.dto.request;

import food_ordering_app.entity.Address;
import food_ordering_app.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersRequest {
    private UUID ReustrantId;
    private List<UUID> orderItemIds;
    private Address address;
}
