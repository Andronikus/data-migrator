package pt.andronikus.database;

import io.dropwizard.lifecycle.Managed;

public class ManageConnectionPool implements Managed {
    @Override
    public void start() throws Exception {
        ConnectionPool.INSTANCE.create();
    }

    @Override
    public void stop() throws Exception {
        ConnectionPool.INSTANCE.destroy();
    }
}
