package lk.ijse.gdse.supermarket.dao.custom.impl;

import lk.ijse.gdse.supermarket.config.FactoryConfiguration;
import lk.ijse.gdse.supermarket.dao.custom.ItemDAO;
import lk.ijse.gdse.supermarket.dto.ItemDTO;
import lk.ijse.gdse.supermarket.dto.OrderDetailsDTO;
import lk.ijse.gdse.supermarket.entity.Customer;
import lk.ijse.gdse.supermarket.entity.Item;
import lk.ijse.gdse.supermarket.entity.OrderDetails;
import lk.ijse.gdse.supermarket.util.CrudUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemDAOImpl implements ItemDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public Optional<String> getNextId() throws SQLException {
        /*ResultSet rst = CrudUtil.execute("select item_id from item order by item_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("I%03d", newIdIndex);
        }
        return "I001";*/
        Session session = factoryConfiguration.getSession();
        String nextId = session
                .createQuery("select i.id from item i order by i.id desc", String.class)
                .setMaxResults(1)
                .uniqueResult();
        return Optional.ofNullable(nextId);
    }

    @Override
    public boolean save(Item item) throws SQLException {
       /* return CrudUtil.execute(
                "insert into item values (? , ? , ? , ? )",
                entity.getItemId(),
                entity.getItemName(),
                entity.getQuantity(),
                entity.getPrice()
        );*/
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(item);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Item> getAll() throws SQLException {
       /* ResultSet rst = CrudUtil.execute("select * from item");
        ArrayList<Item> items = new ArrayList<>();
        while (rst.next()) {
            Item item = new Item(
                    rst.getString(1),       //item id
                    rst.getString(2),       //item name
                    rst.getInt(3),          //qty
                    rst.getDouble(4)       //price
            );
            items.add(item);
        }
        return items;*/
        Session session = factoryConfiguration.getSession();
        Query<Item> query = session.createQuery("from Item", Item.class);
        List<Item> list = query.list();
        return list;
    }

    @Override
    public boolean update(Item item) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(item);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Item item = session.get(Item.class, id);
            if (item != null) {
                return false;
            }
            session.remove(item);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<String> getAllItemIds() throws SQLException {
        // Execute SQL query to get all item IDs
        /*ResultSet rst = CrudUtil.execute("select item_id from item");

        // Create an ArrayList to store the item IDs
        ArrayList<String> itemIds = new ArrayList<>();

        // Iterate through the result set and add each item ID to the list
        while (rst.next()) {
            itemIds.add(rst.getString(1));
        }

        // Return the list of item IDs
        return itemIds;*/
        Session session = factoryConfiguration.getSession();
        List<String> itemIds = session
                .createQuery("select i.id from item", String.class)
                .list();
        session.close();
        return itemIds;
    }


    public Optional<Item> findById(String selectedItemId) throws SQLException {
        // Execute SQL query to find the item by ID
       /* ResultSet rst = CrudUtil.execute("select * from item where item_id=?", selectedItemId);

        // If the item is found, create an ItemDTO object with the retrieved data
        if (rst.next()) {
            return new Item(
                    rst.getString(1),  // Item ID
                    rst.getString(2),  // Item Name
                    rst.getInt(3),     // Item Quantity
                    rst.getDouble(4)   // Item Price
            );*/
        // Return null if the item is not found
        /*return null;*/
        Session session = factoryConfiguration.getSession();
        Item item = session.get(Item.class, selectedItemId);
        session.close();
        if (item == null) {
            return Optional.empty();
        }
        return Optional.of(item);

    }


    /*public boolean reduceQty(OrderDetailsDTO dto) throws SQLException {
        // Execute SQL query to update the item quantity in the database
        return CrudUtil.execute(
                "update item set quantity = quantity - ? where item_id = ?",
                dto.getQuantity(),   // Quantity to reduce
                dto.getItemId()      // Item ID
        );
    }*/
}


