package lk.ijse.gdse.supermarket.bo.custom;

import lk.ijse.gdse.supermarket.bo.SuperBO;
import lk.ijse.gdse.supermarket.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CustomerBO extends SuperBO {
    List<CustomerDTO> getAllCustomers() throws SQLException;

    Optional<String> getNextCustomerId() throws SQLException;

    boolean saveCustomer(CustomerDTO customerDTO) throws SQLException;

    boolean deleteCustomer(String customerId) throws SQLException;

    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException;

    List<String> getAllCustomerIds() throws SQLException;

    Optional<CustomerDTO> findById(String selectedCusId) throws SQLException;

}
