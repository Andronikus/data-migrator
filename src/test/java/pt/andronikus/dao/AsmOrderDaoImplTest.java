package pt.andronikus.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pt.andronikus.client.enums.OperationType;
import pt.andronikus.configuration.InvokatorConfiguration;
import pt.andronikus.database.ConnectionPool;
import pt.andronikus.entities.AsmOrder;
import pt.andronikus.enums.EntityType;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AsmOrderDaoImplTest {

    @BeforeAll
    public static void initializeConnectionPool(){
        System.out.println("initializeConnectionPool");

        InvokatorConfiguration cfg = new InvokatorConfiguration();

        ConnectionPool.INSTANCE.create(cfg);
    }

    /*
    @Test
    public void shouldInsertAsmOrderWithSuccess(){
        try {
            AsmOrderDao asmOrderDao = DaoFactory.createAsmOrderDao(true);

            AsmOrder asmOrder = new AsmOrder();
            asmOrder.setOrderExternalId(UUID.randomUUID().toString());
            asmOrder.setOrderCorrelationId(UUID.randomUUID().toString());
            asmOrder.setOrderStatus("Okey!");
            asmOrder.setOperation(OperationType.CREATE);
            asmOrder.setEntityType(EntityType.CUSTOMER);

            assertTrue(asmOrderDao.addAsmOrder(asmOrder));

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    */

}