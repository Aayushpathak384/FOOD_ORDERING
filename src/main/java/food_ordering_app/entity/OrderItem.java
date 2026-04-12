package food_ordering_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @Column(name = "quantity")
    private int quantity;

    @OneToOne
    private MenuItem menuItem;
}
