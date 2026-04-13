package food_ordering_app.dto.responce;

import food_ordering_app.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersResponse {
    private UUID id;
    private String customerEmail;
    private UUID restaurantId;
    private double totalPrice;
    private OrderStatus orderStatus;
    private List<UUID> orderItemIds;
}
