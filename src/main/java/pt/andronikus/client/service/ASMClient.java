package pt.andronikus.client.service;

import pt.andronikus.client.ClientPool;
import pt.andronikus.client.exceptions.ServiceClientException;
import pt.andronikus.client.request.CustomerCreateRequest;
import pt.andronikus.client.response.CustomerResponse;
import pt.andronikus.configuration.ASMServerConfiguration;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ASMClient{

    private final WebTarget target;

    public ASMClient() {
        this.target = ClientPool.INSTANCE.getWebTarget();

    }

    public CustomerResponse customerCreatePost(CustomerCreateRequest customerRequest){
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                                  .accept(MediaType.APPLICATION_JSON_TYPE)
                                  .post(Entity.entity(customerRequest, MediaType.APPLICATION_JSON_TYPE));

        return processResponse(response);

    }

    private CustomerResponse processResponse(Response response) throws ServiceClientException {
        try {
            // 200 Ok
            if (response.getStatusInfo().equals(Response.Status.OK)) {
                return response.readEntity(CustomerResponse.class);
            }
            // Other results
            String message = response.getStatusInfo().toString();

            if (response.getStatusInfo().getFamily().equals(Response.Status.Family.SERVER_ERROR)) {
                throw new ServiceClientException(message, true);
            } else {
                throw new ServiceClientException(message, false);
            }
        }finally {
            response.close();
        }
    }
}
