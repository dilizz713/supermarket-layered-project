package lk.ijse.gdse.supermarket.bo.custom.impl;

import lk.ijse.gdse.supermarket.bo.custom.CustomerBO;
import lk.ijse.gdse.supermarket.dao.DAOFactory;
import lk.ijse.gdse.supermarket.dao.custom.CustomerDAO;
import lk.ijse.gdse.supermarket.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.gdse.supermarket.dto.CustomerDTO;
import lk.ijse.gdse.supermarket.entity.Customer;
import lk.ijse.gdse.supermarket.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    public String getNextCustomerId() throws SQLException {
        return customerDAO.getNextId();
    }

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException {
        ArrayList<Customer> customers = customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOs = new ArrayList<>();

        for (Customer customer : customers) {
            customerDTOs.add(new CustomerDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getNic(),
                    customer.getEmail(),
                    customer.getPhone()
            ));

        }
        return customerDTOs;

    }

    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException {
        return customerDAO.save(new Customer(
                customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getNic(),
                customerDTO.getEmail(),
                customerDTO.getPhone()
        ));
    }

    public boolean deleteCustomer(String customerId) throws SQLException {
        return customerDAO.delete(customerId);
    }

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException {
        return customerDAO.update(new Customer(
                customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getNic(),
                customerDTO.getEmail(),
                customerDTO.getPhone()
        ));
    }

    public ArrayList<String> getAllCustomerIds() throws SQLException {
       ArrayList<String> customers = customerDAO.getAllCustomerIds();
       ArrayList<String> customerIds = new ArrayList<>();

       for (String id : customers) {
           customerIds.add(id);
       }
       return customerIds;
    }

    public CustomerDTO findById(String selectedCusId) throws SQLException {
        Customer customers = customerDAO.findById(selectedCusId);

        if(customers == null){
            return null;
        }

        return new CustomerDTO(
                customers.getId(),
                customers.getName(),
                customers.getNic(),
                customers.getEmail(),
                customers.getPhone()
        );

    }


}
