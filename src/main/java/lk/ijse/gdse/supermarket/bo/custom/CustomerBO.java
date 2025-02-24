package lk.ijse.gdse.supermarket.bo.custom;

import lk.ijse.gdse.supermarket.bo.SuperBO;
import lk.ijse.gdse.supermarket.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    ArrayList<CustomerDTO> getAllCustomers() throws SQLException;

    String getNextCustomerId() throws SQLException;

    boolean saveCustomer(CustomerDTO customerDTO) throws SQLException;

    boolean deleteCustomer(String customerId) throws SQLException;

    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException;
}
