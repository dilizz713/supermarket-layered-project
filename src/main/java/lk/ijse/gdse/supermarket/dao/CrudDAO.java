package lk.ijse.gdse.supermarket.dao;

import lk.ijse.gdse.supermarket.dto.CustomerDTO;
import lk.ijse.gdse.supermarket.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CrudDAO<T> extends SuperDAO{
    Optional<String> getNextId() throws SQLException;

    boolean save(T dto) throws SQLException;

    List<T> getAll() throws SQLException;

    boolean update(T dto) throws SQLException;

    boolean delete(String id) throws SQLException;
}
