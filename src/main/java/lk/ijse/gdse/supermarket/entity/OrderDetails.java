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
@Table(name = "order_details")
public class OrderDetails {
    @Id
    private String orderId;
    private String itemId;
    private int quantity;
    private double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private List<Order> orders;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;


    public OrderDetails(String orderId, String itemId, int quantity, double price) {
    }
}