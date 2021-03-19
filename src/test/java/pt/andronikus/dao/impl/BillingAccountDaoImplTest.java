package pt.andronikus.dao.impl;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pt.andronikus.configuration.CallbackServerConfiguration;
import pt.andronikus.configuration.InvokatorConfiguration;
import pt.andronikus.configuration.MigrationProcessInfo;
import pt.andronikus.configuration.OracleDB;
import pt.andronikus.dao.BillingAccountDao;
import pt.andronikus.dao.CustomerDao;
import pt.andronikus.dao.DaoFactory;
import pt.andronikus.database.ConnectionPool;
import pt.andronikus.entities.BillingAccount;
import pt.andronikus.entities.Customer;
import pt.andronikus.singletons.AppConfiguration;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BillingAccountDaoImplTest {
    @BeforeAll
    static void connectionPool(){
        createConnectionPool();
    }

    @AfterAll
    static void destroyConnection(){
        ConnectionPool.INSTANCE.destroy();
    }

    @Test
    @Ignore
    void whenNoBillingAccountToCreate_shouldReturnAnEmptyList(){
        try {
            BillingAccountDao billingAccountDao = DaoFactory.createBillingAccountDao(true);
            List<BillingAccount> billingAccounts = billingAccountDao.getBillingAccountToCreate(1);

            assertEquals(0, billingAccounts.size());
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Test
    @Ignore
    void whenNoBillingAccountToClose_shouldReturnAnEmptyList(){
        try {
            BillingAccountDao billingAccountDao = DaoFactory.createBillingAccountDao(true);
            List<BillingAccount> billingAccounts = billingAccountDao.getBillingAccountToClose(10);

            assertEquals(0, billingAccounts.size());
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    private static void createConnectionPool(){

        OracleDB oracleDB = new OracleDB();
        oracleDB.setIpAddress("10.112.83.206");
        oracleDB.setPort("1521");
        oracleDB.setSid("smdecare");
        oracleDB.setUsername("SMARTTOM_MIG_FE");
        oracleDB.setPassword("SMARTTOM_MIG_FE");
        oracleDB.setPartition(1);

        CallbackServerConfiguration callback = new CallbackServerConfiguration();
        callback.setIpAddress("10.112.97.222");
        callback.setPort(1521);
        callback.setEndpoint("fulfillment");

        MigrationProcessInfo migInfo = new MigrationProcessInfo();
        migInfo.setOrderSource("MIG-R8-R9");
        migInfo.setChannel("M2M-MIG");

        InvokatorConfiguration cfg = new InvokatorConfiguration();
        cfg.setOracleDB(oracleDB);
        cfg.setMigrationProcessInfo(migInfo);
        cfg.setCallbackServerConfiguration(callback);

        AppConfiguration.INSTANCE.setAppCfg(cfg);
        ConnectionPool.INSTANCE.create(cfg);
        System.out.println("createConnectionPool");
    }
}