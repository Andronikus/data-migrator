package pt.andronikus.database;

import io.dropwizard.Configuration;
import io.dropwizard.lifecycle.Managed;
import pt.andronikus.configuration.InvokatorConfiguration;

public class ManageConnectionPool implements Managed {

    private final InvokatorConfiguration cfg;

    public ManageConnectionPool(InvokatorConfiguration cfg) {
        this.cfg = cfg;
    }

    @Override
    public void start() throws Exception {
        ConnectionPool.INSTANCE.create(cfg);
    }

    @Override
    public void stop() throws Exception {
        ConnectionPool.INSTANCE.destroy();
    }
}
