package pt.andronikus.client.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.andronikus.client.dto.CustomerOrderItem;
import pt.andronikus.client.dto.EntryObject;
import pt.andronikus.client.dto.OrderItem;
import pt.andronikus.client.enums.Attributes;
import pt.andronikus.client.enums.ExecutionsModes;
import pt.andronikus.client.enums.OperationType;
import pt.andronikus.client.enums.OrderItemType;
import pt.andronikus.client.request.CustomerRequest;
import pt.andronikus.client.utils.JSONUtils;
import pt.andronikus.configuration.CallbackServerConfiguration;
import pt.andronikus.configuration.InvokatorConfiguration;
import pt.andronikus.configuration.MigrationProcessInfo;
import pt.andronikus.constants.Global;
import pt.andronikus.constants.MigrationFlag;
import pt.andronikus.entities.Customer;
import pt.andronikus.singletons.AppConfiguration;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRequestFactoryTest {
    private static final boolean alreadySetUp = false;
    private Customer customer;

    @BeforeEach
    void setUp() {
        if(!alreadySetUp){
            this.customer = createCustomer();

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
        }
    }

    @Test
    void shouldCustomerCreateRequestWithAsyncModeAndWithMigAttributes() {
        CustomerRequest customerCreateRequest = CustomerRequestFactory.getCustomerCreationRequest(this.customer);

        // order external id should be generated
        assertTrue(customerCreateRequest.getOrderExternalId().length() > 0);
        assertEquals(AppConfiguration.INSTANCE.getConfiguration(Global.ORDER_SOURCE), customerCreateRequest.getOrderSource());
        assertEquals(customer.getOrderCorrelationId(),customerCreateRequest.getOrderCorrelationId());
        assertEquals(AppConfiguration.INSTANCE.getConfiguration(Global.CHANNEL), customerCreateRequest.getExtChannel());

        // execution mode (with async mode a callback should be set)
        assertEquals(ExecutionsModes.ASYNC, customerCreateRequest.getExecutionMode().getExecutionMode());
        assertTrue(customerCreateRequest.getExecutionMode().getCallbackUrl().length() > 0);
    }

    @Test
    void shouldCustomerCreateRequestHaveAValidCustomerOrderItem(){
        CustomerRequest customerCreateRequest = CustomerRequestFactory.getCustomerCreationRequest(this.customer);

        CustomerOrderItem orderItem = (CustomerOrderItem) customerCreateRequest.getOrderItems().get(OrderItemType.CUSTOMER_ORDER_ITEM);
        assertNotNull(orderItem, "Customer create request should have a order item customerOrderItem and its null!");

        // validate order item main info
        assertEquals(customer.getCorrelationId(),orderItem.getCorrelationId());
        assertTrue(orderItem.getExternalItemId().length() > 0);
        assertEquals(customer.getId(), orderItem.getCustomerId());
        assertEquals(customer.getName(), orderItem.getCustomerName());
        assertEquals(OperationType.CREATE, orderItem.getOperation());

        // validate reason
        assertEquals("OTHER",orderItem.getReason().getCode());
        assertEquals("Migration CREATE Customer",orderItem.getReason().getDescription());
    }

    @Test
    void shouldCustomerCreateRequestHaveValidCustomerInfo(){
        CustomerRequest customerCreateRequest = CustomerRequestFactory.getCustomerCreationRequest(this.customer);

        OrderItem orderItem = customerCreateRequest.getOrderItems().get(OrderItemType.CUSTOMER_ORDER_ITEM);

        // validate other info values
        Map<String, String> customerInfo = new HashMap<>();
        for(EntryObject entryObject: orderItem.getOtherInfo().get(Attributes.ENTRY)){
            customerInfo.put(entryObject.getKey(), entryObject.getValue());
        }

        assertTrue(customerInfo.containsKey(Attributes.PHONE));
        assertTrue(customerInfo.containsKey(Attributes.EMAIL));
        assertTrue(customerInfo.containsKey(Attributes.ADDRESS));
        assertTrue(customerInfo.containsKey(Attributes.LOCALE));
        assertTrue(customerInfo.containsKey(Attributes.OPERATOR_ID));
        assertTrue(customerInfo.containsKey(Attributes.TAX_NUMBER));
        assertTrue(customerInfo.containsKey(Attributes.STATUS));
        assertTrue(customerInfo.containsKey(Attributes.MIG_FLAG));

        assertEquals(customer.getPhone(), customerInfo.get(Attributes.PHONE));
        assertEquals(customer.getEmail(), customerInfo.get(Attributes.EMAIL));
        assertEquals(customer.getAddress(), customerInfo.get(Attributes.ADDRESS));
        assertEquals(customer.getLocale(), customerInfo.get(Attributes.LOCALE));
        assertEquals(customer.getOperatorId().toString(), customerInfo.get(Attributes.OPERATOR_ID));
        assertEquals(customer.getTaxNumber(), customerInfo.get(Attributes.TAX_NUMBER));
        assertEquals(customer.getStatus(), customerInfo.get(Attributes.STATUS));
        assertEquals(MigrationFlag.IN_MIGRATION, customerInfo.get(Attributes.MIG_FLAG));

        System.out.println(JSONUtils.toJSON(customerCreateRequest));
    }

    @Test
    void shouldCustomerUpdateRequestHaveValidCustomerInfo(){
        CustomerRequest customerCreateRequest = CustomerRequestFactory.getCustomerUpdateRequest(this.customer);

        OrderItem orderItem = customerCreateRequest.getOrderItems().get(OrderItemType.CUSTOMER_ORDER_ITEM);

        // validate other info values
        Map<String, String> customerInfo = new HashMap<>();
        for(EntryObject entryObject: orderItem.getOtherInfo().get(Attributes.ENTRY)){
            customerInfo.put(entryObject.getKey(), entryObject.getValue());
        }

        assertTrue(customerInfo.containsKey(Attributes.PHONE));
        assertTrue(customerInfo.containsKey(Attributes.EMAIL));
        assertTrue(customerInfo.containsKey(Attributes.ADDRESS));
        assertTrue(customerInfo.containsKey(Attributes.LOCALE));
        assertTrue(customerInfo.containsKey(Attributes.OPERATOR_ID));
        assertTrue(customerInfo.containsKey(Attributes.TAX_NUMBER));
        assertTrue(customerInfo.containsKey(Attributes.STATUS));
        assertTrue(customerInfo.containsKey(Attributes.MIG_FLAG));

        assertEquals(customer.getPhone(), customerInfo.get(Attributes.PHONE));
        assertEquals(customer.getEmail(), customerInfo.get(Attributes.EMAIL));
        assertEquals(customer.getAddress(), customerInfo.get(Attributes.ADDRESS));
        assertEquals(customer.getLocale(), customerInfo.get(Attributes.LOCALE));
        assertEquals(customer.getOperatorId().toString(), customerInfo.get(Attributes.OPERATOR_ID));
        assertEquals(customer.getTaxNumber(), customerInfo.get(Attributes.TAX_NUMBER));
        assertEquals(customer.getStatus(), customerInfo.get(Attributes.STATUS));
        assertEquals(MigrationFlag.END_MIGRATION, customerInfo.get(Attributes.MIG_FLAG));
    }

    private Customer createCustomer(){
        Customer customer = new Customer();

        customer.setOperatorId(1);
        customer.setId("f97369847cTR01");
        customer.setName("TheRock SFR");
        customer.setPhone("92111111111");
        customer.setEmail("roquexpto@mail.pt");
        customer.setAddress("Travessa dos Granitos");
        customer.setLocale("en_US");
        customer.setTaxNumber("3456789");
        customer.setStatus("ACTIVE");
        customer.setMigFlag(1);
        customer.setCorrelationId("MIG_f97369847c3727");
        customer.setOrderCorrelationId("MIG_CUST_BD992E4CFD434CA6E053CE53700A52B0");

        return customer;
    }
}