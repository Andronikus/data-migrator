package pt.andronikus.dao;

import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.database.tables.AsmOrdersTable;
import pt.andronikus.entities.AsmOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class AsmOrderDaoImpl implements AsmOrderDao {
    private final Logger LOGGER = LoggerFactory.getLogger(AsmOrderDaoImpl.class);
    private final String LOG_PREFIX = AsmOrderDaoImpl.class.getSimpleName() + " :: ";
    private final Connection connection;

    public AsmOrderDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addAsmOrder(AsmOrder asmOrder) {
        final String METHOD_NAME = LOG_PREFIX + " addAsmOrder ";

        try (PreparedStatement stm = connection.prepareStatement(AsmOrdersTable.INSERT_ASM_ODER)){

            stm.setString(1, asmOrder.getOrderExternalId());
            stm.setString(2, asmOrder.getOrderCorrelationId());
            stm.setString(3, asmOrder.getEntityType().name());
            stm.setString(4, asmOrder.getOperation().name());
            stm.setString(5, asmOrder.getOrderStatus());

            try(ResultSet resultSet = stm.executeQuery()){
                if(resultSet.next()){
                   return true;
                }
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
}
