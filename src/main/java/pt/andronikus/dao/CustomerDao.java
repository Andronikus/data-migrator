package pt.andronikus.dao;

import pt.andronikus.database.tables.CustomerTable;
import pt.andronikus.entities.BillingAccount;
import pt.andronikus.entities.Customer;
import pt.andronikus.enums.MigrationStatus;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CustomerDao {
    List<Customer> getCustomerToCreate(int nbrRecordsToLoad);
    boolean updateCustomerMigrationState(Customer customer, String migrationStatus);

}
