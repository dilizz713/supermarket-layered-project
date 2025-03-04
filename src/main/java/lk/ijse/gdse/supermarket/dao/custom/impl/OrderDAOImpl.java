package lk.ijse.gdse.supermarket.dao.custom.impl;

import lk.ijse.gdse.supermarket.config.FactoryConfiguration;
import lk.ijse.gdse.supermarket.dao.custom.OrderDAO;
import lk.ijse.gdse.supermarket.db.DBConnection;
import lk.ijse.gdse.supermarket.entity.Order;
import lk.ijse.gdse.supermarket.util.CrudUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAOImpl implements OrderDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public Optional<String> getNextId() throws SQLException {
       /* ResultSet rst = CrudUtil.execute("select order_id from orders order by order_id desc limit 1");

        if (rst.next()) {
            // @lastId: Retrieves the last order ID
            String lastId = rst.getString(1); // e.g., "O002"
            // @substring: Extracts the numeric part from the order ID
            String substring = lastId.substring(1); // e.g., "002"
            // @i: Converts the numeric part to an integer
            int i = Integer.parseInt(substring); // 2
            // @newIdIndex: Increments the numeric part by 1
            int newIdIndex = i + 1; // 3
            // Returns the new order ID, formatted as "O" followed by a 3-digit number (e.g., O003)
            return String.format("O%03d", newIdIndex);
        }
        // Returns "O001" if no previous orders are found
        return "O001";*/
        Session session = factoryConfiguration.getSession();
        String nextId = session
                .createQuery("select o.id from orders o order by o.id desc" , String.class)
                .setMaxResults(1)
                .uniqueResult();
        return Optional.ofNullable(nextId);

    }

    @Override
    public boolean save(Order order) throws SQLException {
       /*return CrudUtil.execute(
                "insert into orders values (?,?,?)",
               entity.getOrderId(),
               entity.getCustomerId(),
               entity.getOrderDate()
        );*/
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.persist(order);
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
    public List<Order> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(Order dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }
}
