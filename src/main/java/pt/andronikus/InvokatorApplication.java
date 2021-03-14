package pt.andronikus;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import pt.andronikus.client.ManageClient;
import pt.andronikus.configuration.InvokatorConfiguration;
import pt.andronikus.database.ManageConnectionPool;
import pt.andronikus.health.ConnectionPoolHealthCheck;
import pt.andronikus.resources.InvokatorResource;

import java.util.Properties;

public class InvokatorApplication extends Application<InvokatorConfiguration> {

    public static void main(String[] args) throws Exception{
        new InvokatorApplication().run(args[0], args[1]);
    }

    @Override
    public void initialize(Bootstrap<InvokatorConfiguration> bootstrap) {
        /*
        super.initialize(bootstrap);
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
         */
    }

    @Override
    public void run(InvokatorConfiguration configuration, Environment environment) throws Exception {
        final InvokatorResource invokatorResource = new InvokatorResource();

        environment.lifecycle().manage(new ManageConnectionPool(configuration));
        environment.lifecycle().manage(new ManageClient(configuration));

        environment.jersey().register(invokatorResource);


        final ConnectionPoolHealthCheck connectionPoolHealthCheck = new ConnectionPoolHealthCheck();
        environment.healthChecks().register("connectionPool",connectionPoolHealthCheck);
    }
}
