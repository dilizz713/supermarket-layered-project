package lk.ijse.gdse.supermarket.dao.custom.impl;

import lk.ijse.gdse.supermarket.config.FactoryConfiguration;
import lk.ijse.gdse.supermarket.dao.custom.CustomerDAO;
import lk.ijse.gdse.supermarket.dto.CustomerDTO;
import lk.ijse.gdse.supermarket.entity.Customer;
import lk.ijse.gdse.supermarket.util.CrudUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    public Optional<String> getNextId() throws SQLException {
       /* ResultSet rst = CrudUtil.execute("select customer_id from customer order by customer_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("C%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "C001"; */// Return the default customer ID if no data is found

        Session session = factoryConfiguration.getSession();
        String nextId = session
                .createQuery("select c.id from customer c order by c.id desc" , String.class)
                .setMaxResults(1)
                .uniqueResult();
        return Optional.ofNullable(nextId);
    }

    public boolean save(Customer customer) throws SQLException {
       /* return CrudUtil.execute(
                "insert into customer values (?,?,?,?,?)",
                entity.getId(),
                entity.getName(),
                entity.getNic(),
                entity.getEmail(),
                entity.getPhone()
        );*/
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.persist(customer);
            transaction.commit();
            return true;
        }catch (Exception e) {
            transaction.rollback();
            return false;
        }finally {
            if(session != null) {
                session.close();
            }
        }
    }

    public List<Customer> getAll() throws SQLException {
       /* ResultSet rst = CrudUtil.execute("select * from customer");

        ArrayList<Customer> customers = new ArrayList<>();

        while (rst.next()) {
            Customer entity = new Customer(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Name
                    rst.getString(3),  // NIC
                    rst.getString(4),  // Email
                    rst.getString(5)   // Phone
            );
            customers.add(entity);
        }
        return customers;*/
        Session session = factoryConfiguration.getSession();
        Query<Customer> query = session.createQuery("from Customer", Customer.class);
        List<Customer> list = query.list();
        return list;
    }

    public boolean update(Customer customer) throws SQLException {
      /*  return CrudUtil.execute(
                "update customer set name=?, nic=?, email=?, phone=? where customer_id=?",
                entity.getName(),
                entity.getNic(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getId()
        );*/
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.merge(customer);
            transaction.commit();
            return true;
        }catch (Exception e) {
            transaction.rollback();
            return false;
        }finally {
            if(session != null) {
                session.close();
            }
        }
    }


    public boolean delete(String customerId) throws SQLException {
       /* return CrudUtil.execute("delete from customer where customer_id=?", customerId);*/
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Customer customer = session.get(Customer.class , customerId);
            if(customer != null) {
                return false;
            }
            session.remove(customer);
            transaction.commit();
            return true;
        }catch (Exception e) {
            transaction.rollback();
            return false;
        }finally {
            if(session != null) {
                session.close();
            }
        }
    }

    public List<String> getAllCustomerIds() throws SQLException {
       /* ResultSet rst = CrudUtil.execute("select customer_id from customer");

        ArrayList<String> customerIds = new ArrayList<>();

        while (rst.next()) {
            customerIds.add(rst.getString(1));
        }

        return customerIds;*/
        Session session = factoryConfiguration.getSession();
        List<String> customerIds = session
                .createQuery("select c.id from customer" , String.class)
                .list();
        session.close();
        return customerIds;
    }

    public Optional<Customer> findById(String selectedCusId) throws SQLException {
        /*ResultSet rst = CrudUtil.execute("select * from customer where customer_id=?", selectedCusId);

        if (rst.next()) {
            return new Customer(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Name
                    rst.getString(3),  // NIC
                    rst.getString(4),  // Email
                    rst.getString(5)   // Phone
            );
        }
        else{
            return null;*/
        Session session = factoryConfiguration.getSession();
        Customer customer = session.get(Customer.class, selectedCusId);
        session.close();
        if(customer == null) {
            return Optional.empty();
        }
        return Optional.of(customer);
    }
}
