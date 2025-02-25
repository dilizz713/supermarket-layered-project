package lk.ijse.gdse.supermarket.bo.custom;

import lk.ijse.gdse.supermarket.bo.SuperBO;
import lk.ijse.gdse.supermarket.dto.OrderDTO;
import lk.ijse.gdse.supermarket.entity.Order;

import java.io.Serializable;
import java.sql.SQLException;

public interface OrderBO extends SuperBO{
    String getNextOrderId() throws SQLException;

    boolean saveOrder(OrderDTO orderDTO) throws SQLException;
}
