package lk.ijse.gdse.supermarket.dao.custom;

import lk.ijse.gdse.supermarket.dao.CrudDAO;
import lk.ijse.gdse.supermarket.dto.CustomerDTO;
import lk.ijse.gdse.supermarket.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CustomerDAO extends CrudDAO<Customer> {
    List<String> getAllCustomerIds() throws SQLException;

    Optional findById(String selectedCusId) throws SQLException;
}
