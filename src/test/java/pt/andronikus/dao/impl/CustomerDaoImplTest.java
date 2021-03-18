package pt.andronikus.dao.impl;

import org.junit.jupiter.api.*;
import pt.andronikus.configuration.CallbackServerConfiguration;
import pt.andronikus.configuration.InvokatorConfiguration;
import pt.andronikus.configuration.MigrationProcessInfo;
import pt.andronikus.configuration.OracleDB;
import pt.andronikus.dao.CustomerDao;
import pt.andronikus.database.ConnectionPool;
import pt.andronikus.entities.Customer;
import pt.andronikus.enums.MigrationStatus;
import pt.andronikus.singletons.AppConfiguration;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class CustomerDaoImplTest {

    private boolean alreadySetUp = false;

    @BeforeEach
    void setUp() {
        if(!alreadySetUp){
            createConnectionPool();
            alreadySetUp = true;
        }
    }

    /*
    @Test
    void shouldGetCustomerFromDBToCreate() {
        try {
            CustomerDao customerDao = new CustomerDaoImpl(ConnectionPool.INSTANCE.getConnection(true));

            Optional<Customer> customerOpt = customerDao.getCustomerToCreate();

            customerOpt.ifPresent(System.out::println);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

     */

    /*
    @Test
    void shouldUpdateCustomerMigStatusWithSuccess() {
        try {
            CustomerDao customerDao = new CustomerDaoImpl(ConnectionPool.INSTANCE.getConnection(true));

            boolean wasUpdated = customerDao.updateCustomerMigrationState("f97369847cTR01", "BD992E4CFD434CA6E053CE53700A52B0", MigrationStatus.WAITING_CREATE.name());
            assertTrue(wasUpdated);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

     */

    @AfterAll
    static void destroyConnection(){
        ConnectionPool.INSTANCE.destroy();
    }

    private void createConnectionPool(){

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