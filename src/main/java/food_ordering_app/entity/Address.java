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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "street")
    private String street;

    @Column(name ="state" )
    private String state;

    @Column(name = "pincode")
    private String pincode;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Restaurant restaurant;

}
