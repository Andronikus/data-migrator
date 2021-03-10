package pt.andronikus.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public enum ConnectionPool {
    INSTANCE;
    private final Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);

    private HikariDataSource dataSource;

    public synchronized void create(){
        final HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:oracle:thin:@10.112.83.206:1521/smdecare");
        // config.setJdbcUrl("jdbc:oracle:thin:@10.112.97.223:1521/sldesdp");
        config.setUsername("SMARTTOM_MIG_FE");
        config.setPassword("SMARTTOM_MIG_FE");
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
