package pt.andronikus.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.dao.CustomerDao;
import pt.andronikus.database.tables.CustomerTable;
import pt.andronikus.entities.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {
    private final Logger LOGGER = LoggerFactory.getLogger(CustomerDaoImpl.class);
    private final String LOG_PREFIX = CustomerDaoImpl.class.getSimpleName() + " :: ";
    private final Connection connection;

    public CustomerDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Customer> getCustomers() {
        final String METHOD_NAME = LOG_PREFIX + " getCustomers ";

        List<Customer> customers = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(CustomerTable.GET_NEW_CUSTOMER);
             ResultSet resultSet = stm.executeQuery()){

            while (resultSet.next()){
                customers.add(createCustomer(resultSet));
            }

        }catch (SQLException sqlException){
            if (LOGGER.isWarnEnabled()){
                LOGGER.warn(METHOD_NAME + "SQLException - " + sqlException.getMessage() + " " + sqlException.getSQLState());
            }
        }catch (Exception e){
            LOGGER.error(METHOD_NAME + e.getMessage());
        }
        return customers;
    }

    @Override
    public Optional<Customer> getCustomer(String id){

        Customer customer = null;
        PreparedStatement stm;
        ResultSet resultSet;

        try{
            stm = connection.prepareStatement(CustomerTable.GET_NEW_CUSTOMER);
            stm.setString(1, id);
            resultSet = stm.executeQuery();

            if (resultSet.next()) {
                customer = createCustomer(resultSet);
            }

        } catch (SQLException e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn(LOG_PREFIX + "SQLException - " + e.getMessage() + " " + e.getSQLState());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Objects.isNull(customer) ? Optional.empty() : Optional.of(customer);
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
