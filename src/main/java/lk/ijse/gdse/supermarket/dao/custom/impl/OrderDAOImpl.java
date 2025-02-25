package lk.ijse.gdse.supermarket.dao.custom.impl;

import lk.ijse.gdse.supermarket.dao.custom.OrderDAO;
import lk.ijse.gdse.supermarket.db.DBConnection;
import lk.ijse.gdse.supermarket.entity.Order;
import lk.ijse.gdse.supermarket.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select order_id from orders order by order_id desc limit 1");

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
        return "O001";
    }

    @Override
    public boolean save(Order entity) throws SQLException {
       return CrudUtil.execute(
                "insert into orders values (?,?,?)",
               entity.getOrderId(),
               entity.getCustomerId(),
               entity.getOrderDate()
        );
    }



    @Override
    public ArrayList<Order> getAll() throws SQLException {
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
