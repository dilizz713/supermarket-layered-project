package lk.ijse.gdse.supermarket.bo.custom.impl;

import lk.ijse.gdse.supermarket.bo.BOFactory;
import lk.ijse.gdse.supermarket.bo.custom.ItemBO;
import lk.ijse.gdse.supermarket.bo.custom.OrderDetailsBO;
import lk.ijse.gdse.supermarket.dao.DAOFactory;
import lk.ijse.gdse.supermarket.dao.custom.CustomerDAO;
import lk.ijse.gdse.supermarket.dao.custom.ItemDAO;
import lk.ijse.gdse.supermarket.dao.custom.OrderDAO;
import lk.ijse.gdse.supermarket.dao.custom.OrderDetailsDAO;
import lk.ijse.gdse.supermarket.dao.custom.impl.ItemDAOImpl;
import lk.ijse.gdse.supermarket.dao.custom.impl.OrderDAOImpl;
import lk.ijse.gdse.supermarket.dao.custom.impl.OrderDetailsDAOImpl;
import lk.ijse.gdse.supermarket.dto.OrderDetailsDTO;
import lk.ijse.gdse.supermarket.entity.OrderDetails;
import lk.ijse.gdse.supermarket.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsBOImpl implements OrderDetailsBO {
   /* ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);*/
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.ITEM);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDERDETAIL);

    public boolean saveOrderDetailsList(ArrayList<OrderDetailsDTO> dtos) throws SQLException {
        // Iterate through each order detail in the list
        for (OrderDetailsDTO orderDetails : dtos) {
            // @isOrderDetailsSaved: Saves the individual order detail
            boolean isOrderDetailsSaved = save(orderDetails);
            if (!isOrderDetailsSaved) {
                // Return false if saving any order detail fails
                return false;
            }

            // @isItemUpdated: Updates the item quantity in the stock for the corresponding order detail
            boolean isItemUpdated = itemBO.reduceQty(orderDetails);
            if (!isItemUpdated) {
                // Return false if updating the item quantity fails
                return false;
            }
        }
        // Return true if all order details are saved and item quantities updated successfully
        return true;
    }


    public boolean save(OrderDetailsDTO dto) throws SQLException {
        return orderDetailsDAO.save(new OrderDetails(
                dto.getOrderId(),
                dto.getItemId(),
                dto.getQuantity(),
                dto.getPrice()
        ));
    }


}
