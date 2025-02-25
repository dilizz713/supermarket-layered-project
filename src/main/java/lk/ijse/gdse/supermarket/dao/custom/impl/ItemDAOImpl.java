package lk.ijse.gdse.supermarket.dao.custom.impl;

import lk.ijse.gdse.supermarket.dao.custom.ItemDAO;
import lk.ijse.gdse.supermarket.dto.ItemDTO;
import lk.ijse.gdse.supermarket.dto.OrderDetailsDTO;
import lk.ijse.gdse.supermarket.entity.Item;
import lk.ijse.gdse.supermarket.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select item_id from item order by item_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("I%03d", newIdIndex);
        }
        return "I001";
    }

    @Override
    public boolean save(Item entity) throws SQLException {
        return CrudUtil.execute(
                "insert into item values (? , ? , ? , ? )",
                entity.getItemId(),
                entity.getItemName(),
                entity.getQuantity(),
                entity.getPrice()
        );
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from item");
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
        return items;
    }

    @Override
    public boolean update(Item entity) throws SQLException {
        return CrudUtil.execute(
                "update item set item_name = ?, quantity = ?, price = ? where item_id = ?",
                entity.getItemName(),
                entity.getQuantity(),
                entity.getPrice(),
                entity.getItemId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute(
                "delete from item where item_id=?",id

        );
    }

    public ArrayList<String> getAllItemIds() throws SQLException {
        // Execute SQL query to get all item IDs
        ResultSet rst = CrudUtil.execute("select item_id from item");

        // Create an ArrayList to store the item IDs
        ArrayList<String> itemIds = new ArrayList<>();

        // Iterate through the result set and add each item ID to the list
        while (rst.next()) {
            itemIds.add(rst.getString(1));
        }

        // Return the list of item IDs
        return itemIds;
    }


    public Item findById(String selectedItemId) throws SQLException {
        // Execute SQL query to find the item by ID
        ResultSet rst = CrudUtil.execute("select * from item where item_id=?", selectedItemId);

        // If the item is found, create an ItemDTO object with the retrieved data
        if (rst.next()) {
            return new Item(
                    rst.getString(1),  // Item ID
                    rst.getString(2),  // Item Name
                    rst.getInt(3),     // Item Quantity
                    rst.getDouble(4)   // Item Price
            );
        }

        // Return null if the item is not found
        return null;
    }

    public boolean reduceQty(OrderDetailsDTO orderDetailsDTO) throws SQLException {
        // Execute SQL query to update the item quantity in the database
        return CrudUtil.execute(
                "update item set quantity = quantity - ? where item_id = ?",
                orderDetailsDTO.getQuantity(),   // Quantity to reduce
                orderDetailsDTO.getItemId()      // Item ID
        );
    }
}
