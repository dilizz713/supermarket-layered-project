package lk.ijse.gdse.supermarket.dao.custom;

import lk.ijse.gdse.supermarket.dao.CrudDAO;
import lk.ijse.gdse.supermarket.dto.ItemDTO;
import lk.ijse.gdse.supermarket.dto.OrderDetailsDTO;
import lk.ijse.gdse.supermarket.entity.Item;
import lk.ijse.gdse.supermarket.entity.OrderDetails;

import java.sql.SQLException;

import java.util.List;
import java.util.Optional;

public interface ItemDAO extends CrudDAO<Item> {
    List<String> getAllItemIds() throws SQLException;

    Optional<Item> findById(String selectedItemId) throws SQLException;

   //boolean reduceQty(OrderDetailsDTO dto) throws SQLException;
}
