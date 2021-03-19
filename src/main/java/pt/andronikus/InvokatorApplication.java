package pt.andronikus;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import pt.andronikus.client.ManageClient;
import pt.andronikus.configuration.InvokatorConfiguration;
import pt.andronikus.database.ManageConnectionPool;
import pt.andronikus.health.ConnectionPoolHealthCheck;
import pt.andronikus.resources.InvokatorResource;
import pt.andronikus.singletons.AppConfiguration;

public class InvokatorApplication extends Application<InvokatorConfiguration> {

    public static void main(String[] args) throws Exception{
        new InvokatorApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<InvokatorConfiguration> bootstrap) {

    }

    @Override
    public void run(InvokatorConfiguration configuration, Environment environment) throws Exception {
        AppConfiguration.INSTANCE.setAppCfg(configuration);

        final InvokatorResource invokatorResource = new InvokatorResource();

        environment.lifecycle().manage(new ManageConnectionPool(configuration));
        environment.lifecycle().manage(new ManageClient(configuration));

        environment.jersey().register(invokatorResource);


        final ConnectionPoolHealthCheck connectionPoolHealthCheck = new ConnectionPoolHealthCheck();
        environment.healthChecks().register("connectionPool",connectionPoolHealthCheck);
    }
}
