package pt.andronikus.resources;

import com.codahale.metrics.annotation.Timed;
import pt.andronikus.api.StatusResponse;
import pt.andronikus.dao.impl.CustomerDaoImpl;
import pt.andronikus.database.ConnectionPool;
import pt.andronikus.singletons.Migration;
import pt.andronikus.thread.MigrationThread;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/invokator")
@Produces(MediaType.APPLICATION_JSON)
public class InvokatorResource {

    @GET
    @Timed
    @Path("/admin")
    public Response sendCommand(@NotNull
                                @QueryParam("command")
                                @Pattern(regexp = "(start|stop)", message = ":: command must be start or stop")
                                String command){

        switch (command.toLowerCase()){
            case "start":
                if(Migration.INSTANCE.getStatus().equals(Migration.Status.STOPPED)){
                    Migration.INSTANCE.start();
                    Thread newThread = new Thread(new MigrationThread());
                    newThread.start();
                }
                break;
            case "stop":
                Migration.INSTANCE.stop();
                break;
            default:
        }

        return Response.status(Response.Status.OK).build();
    }


    @GET
    @Path("/status")
    @Timed
    public Response getAppStatus(){
        StatusResponse response = new StatusResponse(Migration.INSTANCE.getStatus());
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @GET
    @Path("/customer")
    @Timed
    public Response getCustomer() throws SQLException {

        CustomerDaoImpl customerDAO = new CustomerDaoImpl(ConnectionPool.INSTANCE.getConnection(false));

        customerDAO.getCustomers();

        return Response.status(Response.Status.OK).build();
    }

}
