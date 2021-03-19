package pt.andronikus.dao.impl;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pt.andronikus.configuration.CallbackServerConfiguration;
import pt.andronikus.configuration.InvokatorConfiguration;
import pt.andronikus.configuration.MigrationProcessInfo;
import pt.andronikus.configuration.OracleDB;
import pt.andronikus.dao.DaoFactory;
import pt.andronikus.dao.ResourceDao;
import pt.andronikus.database.ConnectionPool;
import pt.andronikus.entities.Resource;
import pt.andronikus.singletons.AppConfiguration;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResourceDaoImplTest {
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
    void shouldReturnAResourceToCreate(){
        try {
            ResourceDao resourceDao = DaoFactory.createResourceDao(true);
            List<Resource> resources = resourceDao.getResourceToCreate(2);

            assertEquals(2, resources.size());
            assertNull(resources.get(0).getTariffPlans().getVoiceTariffPlan());
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Test
    @Ignore
    void whenNoResourcesToSuspend_shouldReturnAListWithSizeZero(){
        try {
            ResourceDao resourceDao = DaoFactory.createResourceDao(true);
            List<Resource> resources = resourceDao.getResourceToSuspend(1);

            assertEquals(0, resources.size());
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Test
    @Ignore
    void whenThereAreResourcesToSuspend_shouldReturnAListWithResourcesToSuspend(){
        try {
            ResourceDao resourceDao = DaoFactory.createResourceDao(true);
            List<Resource> resources = resourceDao.getResourceToSuspend(1);

            assertEquals(0, resources.size());
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