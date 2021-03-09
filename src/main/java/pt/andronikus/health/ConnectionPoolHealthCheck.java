package pt.andronikus.health;

import com.codahale.metrics.health.HealthCheck;
import pt.andronikus.database.ConnectionPool;

public class ConnectionPoolHealthCheck extends HealthCheck {
    @Override
    protected Result check() throws Exception {
        ConnectionPool.INSTANCE.ping();
        return Result.healthy();
    }
}
