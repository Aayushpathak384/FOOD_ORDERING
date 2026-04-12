package food_ordering_app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemRequest {
    private String itemName;
    private Double price;
    private int count;
    private UUID restaurantId;
}
