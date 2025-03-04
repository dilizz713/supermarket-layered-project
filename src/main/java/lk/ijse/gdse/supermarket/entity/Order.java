package lk.ijse.gdse.supermarket.entity;

import jakarta.persistence.*;
import lk.ijse.gdse.supermarket.dto.OrderDetailsDTO;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter                 // @Getter: Automatically generates getter methods for all fields
@Setter                 // @Setter: Automatically generates setter methods for all fields
@AllArgsConstructor     // @AllArgsConstructor: Generates a constructor with all fields as parameters
@NoArgsConstructor      // @NoArgsConstructor: Generates a no-argument constructor
@ToString               // @ToString: Automatically generates a string representation of the object
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String orderId;
    private String customerId;
    private Date orderDate;

    // @orderDetailsDTOS: A list of OrderDetailsDTO objects, each representing an item in the order
    private ArrayList<OrderDetailsDTO> orderDetailsDTOS;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "orders" , cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;

    public Order(String orderId, String customerId, Date orderDate, ArrayList<OrderDetailsDTO> orderDetailsDTOS) {
    }
}
