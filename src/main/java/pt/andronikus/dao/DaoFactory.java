package pt.andronikus.dao;

import pt.andronikus.dao.impl.AsmOrderDaoImpl;
import pt.andronikus.dao.impl.CustomerDAOImpl;
import pt.andronikus.database.ConnectionPool;

import java.sql.SQLException;

public class DaoFactory {
    public static CustomerDao createCustomerDao(boolean autoCommit) throws SQLException {
        return new CustomerDAOImpl(ConnectionPool.INSTANCE.getConnection(autoCommit));
    }

    public static AsmOrderDao createAsmOrderDao(boolean autoCommit) throws SQLException{
        return new AsmOrderDaoImpl(ConnectionPool.INSTANCE.getConnection(autoCommit));
    }
}
