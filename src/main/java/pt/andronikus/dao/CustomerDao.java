package pt.andronikus.dao;

import pt.andronikus.entities.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List<Customer> getCustomers();
    Optional<Customer> getCustomer(String id);
}
