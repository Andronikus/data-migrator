package pt.andronikus.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.constants.Global;
import pt.andronikus.dao.CustomerDao;
import pt.andronikus.database.tables.CustomerTable;
import pt.andronikus.entities.Customer;
import pt.andronikus.singletons.AppConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    private final Logger LOGGER = LoggerFactory.getLogger(CustomerDaoImpl.class);
    private final String LOG_PREFIX = CustomerDaoImpl.class.getSimpleName() + " :: ";
    private final Connection connection;

    public final static String GET_CUSTOMER_TO_CREATE = String.format("SELECT * FROM (SELECT * FROM %s WHERE %s = %s ORDER BY %s ASC) WHERE ROWNUM < ?",
            CustomerTable.VW_CUSTOMER_TO_CREATE,
            CustomerTable.PF,
            AppConfiguration.INSTANCE.getConfiguration(Global.TABLE_PARTITION),
            CustomerTable.CREATED_AT);

    public final static String UPDATE_CUSTOMER_STATE = String.format("UPDATE %s set %s = ?, UPDATED_AT = SYSDATE where %s = %s and %s = ? and %s = ? and %s = ?",
            CustomerTable.CUSTOMER,
            CustomerTable.MIG_STATUS,
            CustomerTable.PF,
            AppConfiguration.INSTANCE.getConfiguration(Global.TABLE_PARTITION),
            CustomerTable.OPERATOR_ID,
            CustomerTable.CUSTOMER_ID,
            CustomerTable.ORDER_CORRELATION_ID);

    public final static String GET_CUSTOMER_TO_CLOSE = String.format("SELECT * FROM %s WHERE %s = %s AND ROWNUM < ?",
            CustomerTable.VW_CUSTOMER_TO_CLOSE,
            CustomerTable.PF,
            AppConfiguration.INSTANCE.getConfiguration(Global.TABLE_PARTITION));

    public CustomerDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Customer> getCustomerToCreate(int nbrRecordsToLoad) {
        final String METHOD_NAME = LOG_PREFIX + " getCustomerToCreate ";

        List<Customer> customers = new ArrayList<>();

        if( nbrRecordsToLoad < 1){
            if (LOGGER.isWarnEnabled()){
                LOGGER.warn(METHOD_NAME + " nbrRecordsToLoad must be >= 1");
            }
            return customers;
        }

        try(PreparedStatement stm = connection.prepareStatement(GET_CUSTOMER_TO_CREATE)){

            stm.setInt(1, nbrRecordsToLoad + 1);

            try(ResultSet resultSet = stm.executeQuery()){
                while (resultSet.next()){
                    customers.add(createCustomer(resultSet));
                }
            }
        } catch (SQLException sqlException) {
            if (LOGGER.isWarnEnabled()){
                LOGGER.warn(METHOD_NAME + "SQLException - " + sqlException.getMessage() + " " + sqlException.getSQLState());
            }
        } catch (Exception e){
            LOGGER.error(METHOD_NAME + e.getMessage());
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public List<Customer> getCustomerToClose(int nbrRecordsToLoad) {
        final String METHOD_NAME = LOG_PREFIX + " getCustomerToClose ";

        List<Customer> customers = new ArrayList<>();

        if( nbrRecordsToLoad < 1){
            if (LOGGER.isWarnEnabled()){
                LOGGER.warn(METHOD_NAME + " nbrRecordsToLoad must be >= 1");
            }
            return customers;
        }

        try(PreparedStatement stm = connection.prepareStatement(GET_CUSTOMER_TO_CLOSE)){

            stm.setInt(1, nbrRecordsToLoad + 1);

            try(ResultSet resultSet = stm.executeQuery()){
                while (resultSet.next()){
                    customers.add(createCustomer(resultSet));
                }
            }
        } catch (SQLException sqlException) {
            if (LOGGER.isWarnEnabled()){
                LOGGER.warn(METHOD_NAME + "SQLException - " + sqlException.getMessage() + " " + sqlException.getSQLState());
            }
        } catch (Exception e){
            LOGGER.error(METHOD_NAME + e.getMessage());
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public boolean updateCustomerMigrationState(Customer customer, String migrationStatus) {
        final String METHOD_NAME = LOG_PREFIX + " updateCustomerMigrationState ";

        try (PreparedStatement stm = connection.prepareStatement(UPDATE_CUSTOMER_STATE)) {

            stm.setString(1, migrationStatus);
            stm.setInt(2, customer.getOperatorId());
            stm.setString(3, customer.getId());
            stm.setString(4, customer.getOrderCorrelationId());

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
