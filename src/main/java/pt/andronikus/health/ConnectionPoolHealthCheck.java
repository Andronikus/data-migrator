package pt.andronikus.health;

import com.codahale.metrics.health.HealthCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.dao.impl.AsmOrderDaoImpl;
import pt.andronikus.database.ConnectionPool;

public class ConnectionPoolHealthCheck extends HealthCheck {
    private final Logger LOGGER = LoggerFactory.getLogger(ConnectionPoolHealthCheck.class);

    @Override
    protected Result check() throws Exception {
        if(LOGGER.isInfoEnabled()){
            LOGGER.info("ConnectionPoolHealthCheck :: check");
        }
        ConnectionPool.INSTANCE.ping();
        return Result.healthy();
    }
}
