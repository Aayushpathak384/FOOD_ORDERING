package food_ordering_app.dto.responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemResponse {
    private UUID id;
    private String itemName;
    private Double price;
    private int count;
    private UUID restaurantId;
    private UUID orderItemId;
}
