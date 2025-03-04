package lk.ijse.gdse.supermarket.bo.custom;

import lk.ijse.gdse.supermarket.bo.SuperBO;
import lk.ijse.gdse.supermarket.dto.ItemDTO;
import lk.ijse.gdse.supermarket.dto.OrderDetailsDTO;
import lk.ijse.gdse.supermarket.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ItemBO extends SuperBO {
    Optional<String> getNextItemId() throws SQLException;

    boolean saveItem(ItemDTO itemDTO) throws SQLException;

    List<ItemDTO> getAllItems() throws SQLException;

    boolean updateItem(ItemDTO itemDTO) throws SQLException;

    boolean deleteItem(String id) throws SQLException;

    List<String> getAllItemIds() throws SQLException;

    Optional<ItemDTO> findById(String selectedItemId) throws SQLException;

    boolean reduceQty(OrderDetailsDTO orderDetailsDTO) throws SQLException;
}
