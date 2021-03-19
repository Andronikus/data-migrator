package pt.andronikus.resources;

import com.codahale.metrics.annotation.Timed;
import pt.andronikus.dto.MigrationStatusResponse;
import pt.andronikus.service.MigrationManageService;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/invokator")
@Produces(MediaType.APPLICATION_JSON)
public class InvokatorResource {

    private final MigrationManageService migrationManageService;

    public InvokatorResource() {
        this.migrationManageService = new MigrationManageService();
    }

    @GET
    @Timed
    @Path("/admin")
    public Response sendCommand(@NotNull
                                @QueryParam("command")
                                @Pattern(regexp = "(start|stop)", message = ":: command must be start or stop")
                                String command){

        MigrationStatusResponse response = migrationManageService.operateMigration(command);

        return Response.status(Response.Status.OK).entity(response).build();
    }

    @GET
    @Path("/status")
    @Timed
    public Response getAppStatus(){
        MigrationStatusResponse response = migrationManageService.migrationStatus();
        return Response.status(Response.Status.OK).entity(response).build();
    }
}
