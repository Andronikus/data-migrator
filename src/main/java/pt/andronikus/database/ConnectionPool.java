package pt.andronikus.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.dropwizard.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.configuration.InvokatorConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public enum ConnectionPool {
    INSTANCE;
    private final Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);

    private HikariDataSource dataSource;

    public synchronized void create(InvokatorConfiguration cfg){
        final HikariConfig config = new HikariConfig();

        String jdbcUrl = "jdbc:oracle:thin:@" + cfg.getOracleDB().getIpAddress()
                                              + ":" + cfg.getOracleDB().getPort()
                                              + "/" + cfg.getOracleDB().getSid();

        config.setJdbcUrl(jdbcUrl);
        config.setUsername(cfg.getOracleDB().getUsername());
        config.setPassword(cfg.getOracleDB().getPassword());
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        dataSource = new HikariDataSource(config);
    }

    public synchronized void destroy(){
        dataSource.close();
    }

    public void ping() throws Exception{
        final String DUAL = "select 1 from dual";
        try(Connection conn = getConnection(false);
            PreparedStatement stm = conn.prepareStatement(DUAL)) {
            boolean wasExecutedWithSuccess = stm.execute();
        }
    }

    public Connection getConnection(boolean autoCommit) throws SQLException {
        Connection newConnection = dataSource.getConnection();
        newConnection.setAutoCommit(autoCommit);
        return newConnection;
    }
}
