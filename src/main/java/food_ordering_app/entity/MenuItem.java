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

public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "price")
    private Double price;

    @Column(name = "count")
    private int count;

    @ManyToOne
    private Restaurant restaurant;
}
