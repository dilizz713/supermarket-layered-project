package lk.ijse.gdse.supermarket.bo.custom;

import lk.ijse.gdse.supermarket.bo.SuperBO;
import lk.ijse.gdse.supermarket.dto.OrderDTO;

import java.sql.SQLException;
import java.util.Optional;

public interface OrderBO extends SuperBO{
    Optional<String> getNextOrderId() throws SQLException;

    boolean saveOrder(OrderDTO orderDTO) throws SQLException;
}
