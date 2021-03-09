package pt.andronikus.client;

import pt.andronikus.configuration.ASMServerConfiguration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.concurrent.TimeUnit;

public class ClientConfiguration {
    private final Client client;
    private final WebTarget target;

    public ClientConfiguration(ASMServerConfiguration serverConfiguration) {
        String target = "http://" + serverConfiguration.getIpAddress() + ":" + serverConfiguration.getPort();

        this.client = ClientBuilder.newBuilder().connectTimeout(1, TimeUnit.SECONDS).build();
        this.target = this.client.target(target).path(serverConfiguration.getEndpoint());
    }

    public Client getClient() {
        return client;
    }

    public WebTarget getTarget() {
        return target;
    }
}
