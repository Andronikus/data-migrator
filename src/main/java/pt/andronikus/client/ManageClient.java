package pt.andronikus.client;

import io.dropwizard.lifecycle.Managed;
import pt.andronikus.configuration.InvokatorConfiguration;

public class ManageClient implements Managed {

    private final InvokatorConfiguration configuration;

    public ManageClient(InvokatorConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void start() throws Exception {
        System.out.println("Starting ManageClient!");
        ClientPool.INSTANCE.createPool(configuration.getAsmServers());
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Stopping ManageClient!");
        ClientPool.INSTANCE.destroyPool();
    }
}
