package lk.ijse.gdse.supermarket.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "customer")

public class Customer{
    @Id
    private String id;
    private String name;
    private String nic;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL)
    private List<Order> orders;

    public Customer(String id, String name, String nic, String email, String phone) {

    }
}