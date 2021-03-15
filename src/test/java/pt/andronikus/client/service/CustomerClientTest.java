package pt.andronikus.client.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pt.andronikus.client.ClientPool;
import pt.andronikus.client.factory.CustomerRequestFactory;
import pt.andronikus.client.request.CustomerRequest;
import pt.andronikus.client.response.CustomerResponse;
import pt.andronikus.configuration.ASMServerConfiguration;
import pt.andronikus.entities.Customer;

import java.util.ArrayList;
import java.util.List;

class CustomerClientTest {

    private ASMServerConfiguration serverConfiguration;

    @BeforeAll
    static void createConnectionPool(){
        List<ASMServerConfiguration> asmServerConfiguration = new ArrayList<>();

        ASMServerConfiguration server1 = new ASMServerConfiguration();
        server1.setName("ASM1");
        server1.setIpAddress("localhost");
        server1.setPort(7000);
        server1.setEndpoint("/asm/order");

        asmServerConfiguration.add(server1);

        ClientPool.INSTANCE.createPool(asmServerConfiguration);
    }

    @Test
    public void shouldCreateCustomerWithSuccess(){
        ASMClient client = new ASMClient();

        CustomerRequest request = CustomerRequestFactory.getCustomerCreationRequest(createCustomer());
        CustomerResponse response;

        response = client.customerCreatePost(request);

        System.out.println(response);

    }

    private Customer createCustomer(){
        Customer customer = new Customer();

        customer.setOperatorId(0);
        customer.setId("Rock-001");
        customer.setName("The Rock");
        customer.setPhone("234456789");
        customer.setEmail("theRock@pedreira.com");
        customer.setAddress("Travessa dos Granitos");
        customer.setLocale("PT");
        customer.setTaxNumber("3456789");
        customer.setStatus("ACTIVE");
        customer.setMigFlag(1);

        return customer;
    }
}