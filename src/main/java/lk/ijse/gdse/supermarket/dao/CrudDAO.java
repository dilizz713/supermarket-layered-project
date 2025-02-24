package lk.ijse.gdse.supermarket.dao;

import lk.ijse.gdse.supermarket.dto.CustomerDTO;
import lk.ijse.gdse.supermarket.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    String getNextId() throws SQLException;

    boolean save(T dto) throws SQLException;

    ArrayList<T> getAll() throws SQLException;

    boolean update(T dto) throws SQLException;

    boolean delete(String id) throws SQLException;
}
