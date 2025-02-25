package lk.ijse.gdse.supermarket.bo.custom.impl;

import lk.ijse.gdse.supermarket.bo.custom.OrderBO;
import lk.ijse.gdse.supermarket.dao.custom.OrderDAO;
import lk.ijse.gdse.supermarket.dao.custom.impl.OrderDAOImpl;
import lk.ijse.gdse.supermarket.dto.OrderDTO;
import lk.ijse.gdse.supermarket.entity.Order;

import java.sql.SQLException;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public String getNextOrderId() throws SQLException {
        return orderDAO.getNextId();
    }

    @Override
    public boolean saveOrder(OrderDTO orderDTO) throws SQLException {
        return orderDAO.save(new Order(
                orderDTO.getOrderId(),
                orderDTO.getCustomerId(),
                orderDTO.getOrderDate(),
                orderDTO.getOrderDetailsDTOS()
        ));
    }
}
