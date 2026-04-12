package food_ordering_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {


    @Column(name = "name", nullable = false)
    private String name;
    @Id
    @Column(name ="email" , unique = true, nullable = false)
    private String email;

    @Column(name = "password" , nullable = true)
    private String password;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "customer")
    private List<Orders> ordersList;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "customer")
    private List<Address> addressList;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<AuthProvider> authProviders;
}
