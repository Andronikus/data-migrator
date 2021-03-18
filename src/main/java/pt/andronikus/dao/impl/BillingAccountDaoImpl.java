package pt.andronikus.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.constants.Global;
import pt.andronikus.dao.BillingAccountDao;
import pt.andronikus.database.tables.BillingAccountTable;
import pt.andronikus.entities.BillingAccount;
import pt.andronikus.singletons.AppConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillingAccountDaoImpl implements BillingAccountDao {
    private final Logger LOGGER = LoggerFactory.getLogger(BillingAccountDaoImpl.class);
    private final String LOG_PREFIX = BillingAccountDaoImpl.class.getSimpleName() + " :: ";
    private final Connection connection;

    public final static String GET_BILLING_ACCOUNT_TO_CREATE = String.format("SELECT * FROM (SELECT * FROM %s WHERE %s = %s ORDER BY %s ASC) WHERE ROWNUM < ?",
            BillingAccountTable.VW_BILLING_ACC_TO_CREATE,
            BillingAccountTable.PF,
            AppConfiguration.INSTANCE.getConfiguration(Global.TABLE_PARTITION),
            BillingAccountTable.CREATED_AT);

    public final static String UPDATE_BILLING_ACCOUNT_STATE = String.format("UPDATE %s set %s = ?, UPDATED_AT = SYSDATE where %s = %s and %s = ? and %s = ? and %s = ?",
            BillingAccountTable.CDM_BILLING_ACCOUNT,
            BillingAccountTable.MIG_STATUS,
            BillingAccountTable.PF,
            AppConfiguration.INSTANCE.getConfiguration(Global.TABLE_PARTITION),
            BillingAccountTable.OPERATOR_ID,
            BillingAccountTable.ACCOUNT_ID,
            BillingAccountTable.ORDER_CORRELATION_ID);


    public BillingAccountDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<BillingAccount> getBillingAccountToCreate(int nbrRecordsToLoad) {
        final String METHOD_NAME = LOG_PREFIX + " getBillingAccountToCreate ";

        List<BillingAccount> billingAccounts = new ArrayList<>();

        if( nbrRecordsToLoad < 1){
            if (LOGGER.isWarnEnabled()){
                LOGGER.warn(METHOD_NAME + " nbrRecordsToLoad must be >= 1");
            }
            return billingAccounts;
        }

        try(PreparedStatement stm = connection.prepareStatement(GET_BILLING_ACCOUNT_TO_CREATE)){

            stm.setInt(1, nbrRecordsToLoad + 1);

            try(ResultSet resultSet = stm.executeQuery()){
                while (resultSet.next()){
                    billingAccounts.add(createBillingAccount(resultSet));
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

        return billingAccounts;
    }

    @Override
    public boolean updateBillingAccountMigrationState(BillingAccount billingAccount, String migrationStatus) {
        final String METHOD_NAME = LOG_PREFIX + " updateBillingAccountMigrationState ";

        try (PreparedStatement stm = connection.prepareStatement(UPDATE_BILLING_ACCOUNT_STATE)) {

            stm.setString(1, migrationStatus);
            stm.setInt(2, billingAccount.getOperatorId());
            stm.setString(3, billingAccount.getAccountId());
            stm.setString(4, billingAccount.getOrderCorrelationId());

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
        billingAccount.setOrderCorrelationId(resultSet.getString(BillingAccountTable.ORDER_CORRELATION_ID));

        return billingAccount;
    }
}
