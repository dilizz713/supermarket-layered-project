package lk.ijse.gdse.supermarket.bo.custom.impl;

import lk.ijse.gdse.supermarket.bo.custom.ItemBO;
import lk.ijse.gdse.supermarket.config.FactoryConfiguration;
import lk.ijse.gdse.supermarket.dao.DAOFactory;
import lk.ijse.gdse.supermarket.dao.custom.CustomerDAO;
import lk.ijse.gdse.supermarket.dao.custom.ItemDAO;
import lk.ijse.gdse.supermarket.dao.custom.impl.ItemDAOImpl;
import lk.ijse.gdse.supermarket.dto.CustomerDTO;
import lk.ijse.gdse.supermarket.dto.ItemDTO;
import lk.ijse.gdse.supermarket.dto.OrderDetailsDTO;
import lk.ijse.gdse.supermarket.entity.Customer;
import lk.ijse.gdse.supermarket.entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    @Override
    public Optional<String> getNextItemId() throws SQLException {
        return itemDAO.getNextId();
    }

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException {
        return itemDAO.save(new Item(
                itemDTO.getItemId(),
                itemDTO.getItemName(),
                itemDTO.getQuantity(),
                itemDTO.getPrice()
        ));
    }

    @Override
    public List<ItemDTO> getAllItems() throws SQLException {
       /* ArrayList<Item> items = itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOs = new ArrayList<>();
        for (Item item : items) {
            itemDTOs.add(new ItemDTO(
                    item.getItemId(),
                    item.getItemName(),
                    item.getQuantity(),
                    item.getPrice()
            ));
        }
        return itemDTOs;*/
        List<Item> items = itemDAO.getAll();
        return items.stream()
                .map(item -> new ItemDTO(
                        item.getItemId(),
                        item.getItemName(),
                        item.getQuantity(),
                        item.getPrice()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException {
        return itemDAO.update(new Item(
                itemDTO.getItemId(),
                itemDTO.getItemName(),
                itemDTO.getQuantity(),
                itemDTO.getPrice()
        ));
    }

    @Override
    public boolean deleteItem(String id) throws SQLException {
        return itemDAO.delete(id);
    }

    @Override
    public List<String> getAllItemIds() throws SQLException {
      /*  ArrayList<String> items = itemDAO.getAllItemIds();
        ArrayList<String> itemIds = new ArrayList<>();

        for (String itemId : items) {
            itemIds.add(itemId);
        }
        return itemIds;*/
        return itemDAO.getAllItemIds();

    }

    @Override
    public Optional<ItemDTO> findById(String selectedItemId) throws SQLException {
        /*Item items = itemDAO.findById(selectedItemId);
        if (items == null) {
            return null;  // or handle the error as needed
        }
        return new ItemDTO(
                items.getItemId(),
                items.getItemName(),
                items.getQuantity(),
                items.getPrice()
        );*/
        Optional<Item> item = itemDAO.findById(selectedItemId);
        return item.map(i -> new ItemDTO(
                i.getItemId(),
                i.getItemName(),
                i.getQuantity(),
                i.getPrice()
        ));

    }

    @Override
    public boolean reduceQty(OrderDetailsDTO orderDetailsDTO) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Item item = session.get(Item.class, orderDetailsDTO.getItemId());
            if(item == null || item.getQuantity() < orderDetailsDTO.getQuantity()){
                return false;
            }
            item.setQuantity(item.getQuantity() - orderDetailsDTO.getQuantity());
            session.merge(item);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }
}
