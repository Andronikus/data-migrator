package pt.andronikus.client.service;

import pt.andronikus.client.ClientPool;
import pt.andronikus.client.exceptions.ServiceClientException;
import pt.andronikus.client.request.BillingAccountRequest;
import pt.andronikus.client.request.CustomerRequest;
import pt.andronikus.client.request.ResourceRequest;
import pt.andronikus.client.request.ServiceInstanceRequest;
import pt.andronikus.client.response.*;
import pt.andronikus.client.utils.JSONUtils;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

public class ASMClient{

    private final WebTarget target;

    public ASMClient() {
        this.target = ClientPool.INSTANCE.getWebTarget();
    }

    public Optional<CustomerResponse> customerPost(CustomerRequest customerRequest){
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                                  .accept(MediaType.APPLICATION_JSON_TYPE)
                                  .post(Entity.entity(JSONUtils.toJSON(customerRequest), MediaType.APPLICATION_JSON_TYPE));

        OrderExecutionResponse orderExecutionResponse = processResponse(response);

        return Optional.of(new CustomerResponse(orderExecutionResponse));

    }

    public Optional<BillingAccountResponse> billingAccountPost( BillingAccountRequest billingAccountRequest){
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(JSONUtils.toJSON(billingAccountRequest), MediaType.APPLICATION_JSON_TYPE));

        OrderExecutionResponse orderExecutionResponse = processResponse(response);

        return Optional.of(new BillingAccountResponse(orderExecutionResponse));
    }

    public Optional<ServiceInstanceResponse> serviceInstancePost(ServiceInstanceRequest serviceInstanceRequest){
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(JSONUtils.toJSON(serviceInstanceRequest), MediaType.APPLICATION_JSON_TYPE));

        OrderExecutionResponse orderExecutionResponse = processResponse(response);

        return Optional.of(new ServiceInstanceResponse(orderExecutionResponse));
    }

    public Optional<ResourceResponse> resourcePost(ResourceRequest resourceRequest){
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(JSONUtils.toJSON(resourceRequest), MediaType.APPLICATION_JSON_TYPE));

        OrderExecutionResponse orderExecutionResponse = processResponse(response);

        return Optional.of(new ResourceResponse(orderExecutionResponse));
    }

    private OrderExecutionResponse processResponse(Response response) throws ServiceClientException {
        try {
            // 200 Ok
            if (response.getStatusInfo().getFamily().equals(Response.Status.Family.SUCCESSFUL)) {
                return response.readEntity(OrderExecutionResponse.class);
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
