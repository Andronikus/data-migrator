package pt.andronikus.dao;

import pt.andronikus.entities.Customer;
import pt.andronikus.enums.MigrationStatus;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    Optional<Customer> getCustomerToCreate();
    boolean updateCustomerMigrationState(String customerId, String orderCorrelationId, String migrationStatus);
}
