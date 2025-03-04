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
@Table(name = "item")
public class Item {
    @Id
    private String itemId;
    private String itemName;
    private int quantity;
    private double price;

    @OneToMany(mappedBy = "item" , cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;

    public Item(String itemId, String itemName, int quantity, double price) {
    }

}