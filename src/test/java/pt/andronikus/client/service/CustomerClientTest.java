package pt.andronikus.client.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.andronikus.client.ClientPool;
import pt.andronikus.client.factory.CustomerRequestFactory;
import pt.andronikus.client.request.CustomerRequest;
import pt.andronikus.client.response.CustomerResponse;
import pt.andronikus.configuration.ASMServerConfiguration;
import pt.andronikus.configuration.CallbackServerConfiguration;
import pt.andronikus.configuration.InvokatorConfiguration;
import pt.andronikus.configuration.MigrationProcessInfo;
import pt.andronikus.entities.Customer;
import pt.andronikus.entities.ServiceInstance;
import pt.andronikus.singletons.AppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerClientTest {
    private ASMServerConfiguration serverConfiguration;

    @BeforeAll
    static void createConnectionPool(){

        CallbackServerConfiguration callbackCfg = new CallbackServerConfiguration();
        callbackCfg.setIpAddress("10.112.45.21");
        callbackCfg.setPort(7777);
        callbackCfg.setEndpoint("fulfillment");

        MigrationProcessInfo mig = new MigrationProcessInfo();
        mig.setOrderSource("MIG-R8-R9");
        mig.setChannel("M2M-MIG");

        InvokatorConfiguration cfg = new InvokatorConfiguration();
        cfg.setCallbackServerConfiguration(callbackCfg);
        cfg.setMigrationProcessInfo(mig);

        AppConfiguration.INSTANCE.setAppCfg(cfg);


        List<ASMServerConfiguration> asmServerConfiguration = new ArrayList<>();

        ASMServerConfiguration server1 = new ASMServerConfiguration();
        server1.setName("ASM1");
        server1.setIpAddress("10.112.28.77");
        server1.setPort(9000);
        server1.setEndpoint("/asm/order");

        asmServerConfiguration.add(server1);

        ClientPool.INSTANCE.createPool(asmServerConfiguration);
        System.out.println("Initialization done!");
    }

    @Test
    public void shouldSendCustomerCreationAndReceivedAnAcceptedResponse(){
        ASMClient client = new ASMClient();

        Customer customer = createCustomer();

        CustomerRequest request = CustomerRequestFactory.getCustomerCreationRequest(customer);
        Optional<CustomerResponse> response = client.customerPost(request);

        assertTrue(response.isPresent());
        assertEquals(response.get().getCustomerId(), customer.getId());
        assertEquals(response.get().getErrorCode(), "ASM_0008");
        assertEquals(response.get().getOrderCorrelationId(), customer.getOrderCorrelationId());
        System.out.println(response.get().getOrderExternalId());
    }

    private Customer createCustomer(){
        Customer customer = new Customer();

        customer.setOperatorId(1);
        customer.setId("TR"+ UUID.randomUUID().toString().replace("-", "").substring(0,10));
        customer.setName("TheRock SFR");
        customer.setPhone("92111111111");
        customer.setEmail("roquexpto@mail.pt");
        customer.setAddress("Travessa dos Granitos");
        customer.setLocale("en_US");
        customer.setTaxNumber("3456789");
        customer.setStatus("ACTIVE");
        customer.setMigFlag(1);
        customer.setCorrelationId("MIG_" + UUID.randomUUID().toString());
        customer.setOrderCorrelationId("MIG_CUST_" + UUID.randomUUID().toString());

        return customer;
    }
}