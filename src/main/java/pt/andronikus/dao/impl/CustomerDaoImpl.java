package pt.andronikus.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.constants.Global;
import pt.andronikus.dao.CustomerDao;
import pt.andronikus.database.tables.AsmOrdersTable;
import pt.andronikus.database.tables.CustomerTable;
import pt.andronikus.entities.Customer;
import pt.andronikus.enums.MigrationStatus;
import pt.andronikus.singletons.AppConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {
    private final Logger LOGGER = LoggerFactory.getLogger(CustomerDaoImpl.class);
    private final String LOG_PREFIX = CustomerDaoImpl.class.getSimpleName() + " :: ";
    private final Connection connection;


    public final static String GET_CUSTOMER_TO_CREATE = String.format("SELECT * FROM (SELECT * FROM %s WHERE %s = %s AND %s = '%s' ORDER BY %s ASC) WHERE ROWNUM < 2",
            CustomerTable.CUSTOMER,
            CustomerTable.PF,
            AppConfiguration.INSTANCE.getConfiguration(Global.TABLE_PARTITION),
            CustomerTable.MIG_STATUS,
            MigrationStatus.NEW.name(),
            CustomerTable.CREATED_AT);

    public final static String UPDATE_CUSTOMER_STATE = String.format("UPDATE %s set %s = ?, UPDATED_AT = SYSDATE where %s = %s and %s = ? and %s = ?",
            CustomerTable.CUSTOMER,
            CustomerTable.MIG_STATUS,
            CustomerTable.PF,
            AppConfiguration.INSTANCE.getConfiguration(Global.TABLE_PARTITION),
            CustomerTable.CUSTOMER_ID,
            CustomerTable.ORDER_CORRELATION_ID);

    public CustomerDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Customer> getCustomerToCreate(){
        final String METHOD_NAME = LOG_PREFIX + " getCustomerToCreate ";

        Customer customer = null;

        try (PreparedStatement stm = connection.prepareStatement(GET_CUSTOMER_TO_CREATE);
             ResultSet resultSet = stm.executeQuery()) {

                if(resultSet.next()){
                    customer = createCustomer(resultSet);
                }
        }catch (SQLException e){
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn(METHOD_NAME + "SQLException - " + e.getMessage() + " " + e.getSQLState());
            }
        }catch (Exception e){
            LOGGER.error(METHOD_NAME + e.getMessage());
        }

        return Objects.isNull(customer) ? Optional.empty() : Optional.of(customer);
    }

    @Override
    public boolean updateCustomerMigrationState(String customerId, String orderCorrelationId, String migrationStatus) {
        final String METHOD_NAME = LOG_PREFIX + " updateCustomerMigrationState ";

        try (PreparedStatement stm = connection.prepareStatement(UPDATE_CUSTOMER_STATE)) {

            stm.setString(1, migrationStatus);
            stm.setString(2, customerId);
            stm.setString(3, orderCorrelationId);

            if (stm.executeUpdate() > 0){
                return true;
            }
        }catch (SQLException e){
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn(METHOD_NAME + "SQLException - " + e.getMessage() + " " + e.getSQLState());
            }
        }catch (Exception e){
            LOGGER.error(METHOD_NAME + e.getMessage());
        }

        return false;
    }


    private Customer createCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();

        customer.setId(resultSet.getString(CustomerTable.CUSTOMER_ID));
        customer.setName(resultSet.getString(CustomerTable.CUSTOMER_NAME));
        customer.setAddress(resultSet.getString(CustomerTable.ADDRESS));
        customer.setEmail(resultSet.getString(CustomerTable.EMAIL));
        customer.setLocale(resultSet.getString(CustomerTable.LOCALE));
        customer.setOperatorId(resultSet.getInt(CustomerTable.OPERATOR_ID));
        customer.setPhone(resultSet.getString(CustomerTable.PHONE));
        customer.setStatus(resultSet.getString(CustomerTable.CUSTOMER_STATUS));
        customer.setTaxNumber(resultSet.getString(CustomerTable.TAX_NUMBER));
        customer.setCorrelationId(resultSet.getString(CustomerTable.CORRELATION_ID));
        customer.setOrderCorrelationId(resultSet.getString(CustomerTable.ORDER_CORRELATION_ID));

        return customer;
    }
}
