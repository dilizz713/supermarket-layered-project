package lk.ijse.gdse.supermarket.dao.custom.impl;

import lk.ijse.gdse.supermarket.config.FactoryConfiguration;
import lk.ijse.gdse.supermarket.dao.DAOFactory;
import lk.ijse.gdse.supermarket.dao.custom.ItemDAO;
import lk.ijse.gdse.supermarket.dao.custom.OrderDetailsDAO;
import lk.ijse.gdse.supermarket.dto.OrderDetailsDTO;
import lk.ijse.gdse.supermarket.entity.OrderDetails;
import lk.ijse.gdse.supermarket.util.CrudUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

   /* ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);*/

   /* public boolean saveOrderDetailsList(ArrayList<OrderDetails> entity) throws SQLException {
        // Iterate through each order detail in the list
        for (OrderDetails orderDetails : entity) {
            // @isOrderDetailsSaved: Saves the individual order detail
            boolean isOrderDetailsSaved = save(orderDetails);
            if (!isOrderDetailsSaved) {
                // Return false if saving any order detail fails
                return false;
            }

            // @isItemUpdated: Updates the item quantity in the stock for the corresponding order detail
            boolean isItemUpdated = itemDAO.reduceQty(orderDetails);
            if (!isItemUpdated) {
                // Return false if updating the item quantity fails
                return false;
            }
        }
        // Return true if all order details are saved and item quantities updated successfully
        return true;
    }*/

    @Override
    public boolean save(OrderDetails orderDetails) throws SQLException {
        // Executes an insert query to save the order detail into the database
       /* return CrudUtil.execute(
                "insert into orderdetails values (?,?,?,?)",
                entity.getOrderId(),
                entity.getItemId(),
                entity.getQuantity(),
                entity.getPrice()
        );*/
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.persist(orderDetails);
            transaction.commit();
            return true;
        }catch (Exception e) {
            transaction.rollback();
            return false;
        }finally {
            if(session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<String> getNextId() throws SQLException {
        return null;
    }

    @Override
    public List<OrderDetails> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(OrderDetails dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }
}