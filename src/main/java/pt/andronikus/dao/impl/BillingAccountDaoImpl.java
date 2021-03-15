package pt.andronikus.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.dao.BillingAccountDao;
import pt.andronikus.database.tables.BillingAccountTable;
import pt.andronikus.entities.BillingAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillingAccountDaoImpl implements BillingAccountDao {
    private final Logger LOGGER = LoggerFactory.getLogger(BillingAccountDaoImpl.class);
    private final String LOG_PREFIX = BillingAccountDaoImpl.class.getSimpleName() + " :: ";
    private final Connection connection;

    public BillingAccountDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public BillingAccount getBillingAccount() {
        final String METHOD_NAME = LOG_PREFIX + " getBillingAccount ";

        try(PreparedStatement stm = connection.prepareStatement(BillingAccountTable.GET_BILLING_ACCOUNT)){
            try(ResultSet resultSet = stm.executeQuery()){
                if(resultSet.next()){
                    return createBillingAccount(resultSet);
                }
            }
        } catch (SQLException sqlException) {
            if (LOGGER.isWarnEnabled()){
                LOGGER.warn(METHOD_NAME + "SQLException - " + sqlException.getMessage() + " " + sqlException.getSQLState());
            }
        } catch (Exception e){
            LOGGER.error(METHOD_NAME + e.getMessage());
        }

        return null;
    }

    private BillingAccount createBillingAccount(ResultSet resultSet) throws SQLException {
        BillingAccount billingAccount = new BillingAccount();

        billingAccount.setOperatorId(resultSet.getInt(BillingAccountTable.OPERATOR_ID));
        billingAccount.setAccountId(resultSet.getString(BillingAccountTable.ACCOUNT_ID));
        billingAccount.setPf(resultSet.getInt(BillingAccountTable.PF));
        billingAccount.setAccountName(resultSet.getString(BillingAccountTable.ACCOUNT_NAME));
        billingAccount.setCustomerId(resultSet.getString(BillingAccountTable.CUSTOMER_ID));
        billingAccount.setBillingCycleDay(resultSet.getInt(BillingAccountTable.BILLING_CYCLE_DAY));
        billingAccount.setCorrelationId(resultSet.getString(BillingAccountTable.CORRELATION_ID));
        billingAccount.setMigStatus(resultSet.getString(BillingAccountTable.MIG_STATUS));
        billingAccount.setCorrelationId(resultSet.getString(BillingAccountTable.CORRELATION_ID));
        billingAccount.setOrderCorrelationId(resultSet.getString(BillingAccountTable.ORDER_CORRELATION_ID));

        return billingAccount;
    }
}
