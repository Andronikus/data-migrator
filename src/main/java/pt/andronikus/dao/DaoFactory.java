package pt.andronikus.dao;

import pt.andronikus.dao.impl.AsmOrderDaoImpl;
import pt.andronikus.dao.impl.BillingAccountDaoImpl;
import pt.andronikus.dao.impl.CustomerDaoImpl;
import pt.andronikus.database.ConnectionPool;

import java.sql.SQLException;

public class DaoFactory {
    public static CustomerDao createCustomerDao(boolean autoCommit) throws SQLException {
        return new CustomerDaoImpl(ConnectionPool.INSTANCE.getConnection(autoCommit));
    }

    public static AsmOrderDao createAsmOrderDao(boolean autoCommit) throws SQLException{
        return new AsmOrderDaoImpl(ConnectionPool.INSTANCE.getConnection(autoCommit));
    }

    public static BillingAccountDao createBillingAccountDao(boolean autoCommit) throws SQLException {
        return new BillingAccountDaoImpl(ConnectionPool.INSTANCE.getConnection(autoCommit));
    }
}
