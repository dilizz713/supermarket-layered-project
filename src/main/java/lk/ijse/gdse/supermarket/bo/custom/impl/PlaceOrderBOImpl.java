package lk.ijse.gdse.supermarket.bo.custom.impl;

import lk.ijse.gdse.supermarket.bo.BOFactory;
import lk.ijse.gdse.supermarket.bo.custom.OrderDetailsBO;
import lk.ijse.gdse.supermarket.bo.custom.PlaceOrderBO;
import lk.ijse.gdse.supermarket.dao.DAOFactory;
import lk.ijse.gdse.supermarket.dao.custom.OrderDAO;
import lk.ijse.gdse.supermarket.db.DBConnection;
import lk.ijse.gdse.supermarket.dto.OrderDTO;
import lk.ijse.gdse.supermarket.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER);
   OrderDetailsBO orderDetailsBO = (OrderDetailsBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.ORDERDETAIL);

    public boolean saveOrder(OrderDTO orderDTO) throws SQLException {
        // @connection: Retrieves the current connection instance for the database
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            // @autoCommit: Disables auto-commit to manually control the transaction
            connection.setAutoCommit(false); // 1

            // @isOrderSaved: Saves the order details into the orders table
            boolean isOrderSaved = orderDAO.save(new Order(orderDTO.getOrderId(),orderDTO.getCustomerId(),orderDTO.getOrderDate(),orderDTO.getOrderDetailsDTOS()));
            // If the order is saved successfully
            if (isOrderSaved) {
                // @isOrderDetailListSaved: Saves the list of order details
                System.out.println(orderDTO.getOrderId());
                boolean isOrderDetailListSaved = orderDetailsBO.saveOrderDetailsList(orderDTO.getOrderDetailsDTOS());
                if (isOrderDetailListSaved) {
                    // @commit: Commits the transaction if both order and details are saved successfully
                    connection.commit(); // 2
                    return true;
                }
            }
            // @rollback: Rolls back the transaction if order details saving fails
            connection.rollback(); // 3
            return false;
        } catch (Exception e) {
            // @catch: Rolls back the transaction in case of any exception
            connection.rollback();
            return false;
        } finally {
            // @finally: Resets auto-commit to true after the operation
            connection.setAutoCommit(true); // 4
        }
    }
}
