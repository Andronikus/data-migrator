package pt.andronikus.client.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.andronikus.client.dto.BillingAccountOrderItem;
import pt.andronikus.client.dto.CustomerOrderItem;
import pt.andronikus.client.dto.EntryObject;
import pt.andronikus.client.dto.OrderItem;
import pt.andronikus.client.enums.Attributes;
import pt.andronikus.client.enums.ExecutionsModes;
import pt.andronikus.client.enums.OperationType;
import pt.andronikus.client.enums.OrderItemType;
import pt.andronikus.client.request.BillingAccountRequest;
import pt.andronikus.client.request.CustomerCreateRequest;
import pt.andronikus.client.utils.JSONUtils;
import pt.andronikus.entities.BillingAccount;
import pt.andronikus.entities.Customer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RequestFactoryTest {

    private static final boolean alreadySetUp = false;

    private Customer customer;
    private BillingAccount billingAccount;

    @BeforeEach
    void setUp() {
        if(!alreadySetUp){
            this.customer = createCustomer();
            this.billingAccount = createBillingAccount();
        }
    }

    @Test
    void shouldCustomerCreateRequestWithAsyncModeAndWithMigAttributes() {
        CustomerCreateRequest customerCreateRequest = RequestFactory.getCustomerCreationRequest(this.customer);

        // order external id should be generated
        assertTrue(customerCreateRequest.getOrderExternalId().length() > 0);
        assertEquals("MIG-R8-R9", customerCreateRequest.getOrderSource());
        assertTrue(customerCreateRequest.getOrderCorrelationId().length() > 0);
        assertEquals("M2M-MIG", customerCreateRequest.getExtChannel());

        // execution mode (with async mode a callback should be set)
        assertEquals(ExecutionsModes.ASYNC, customerCreateRequest.getExecutionMode().getExecutionMode());
        assertTrue(customerCreateRequest.getExecutionMode().getCallbackUrl().length() > 0);
    }

    @Test
    void shouldCustomerCreateRequestHaveAValidCustomerOrderItem(){
        CustomerCreateRequest customerCreateRequest = RequestFactory.getCustomerCreationRequest(this.customer);

        CustomerOrderItem orderItem = (CustomerOrderItem) customerCreateRequest.getOrderItems().get(OrderItemType.CUSTOMER_ORDER_ITEM);
        assertNotNull(orderItem, "Customer create request should have a order item customerOrderItem and its null!");

        // validate order item main info
        assertTrue(orderItem.getCorrelationId().length() > 0);
        assertTrue(orderItem.getExternalItemId().length() > 0);
        assertEquals(this.customer.getId(), orderItem.getCustomerId());
        assertEquals(this.customer.getName(), orderItem.getCustomerName());
        assertEquals(OperationType.CREATE, orderItem.getOperation());

        // validate reason
        assertEquals("OTHER",orderItem.getReason().getCode());
        assertEquals("Migration CREATE Customer",orderItem.getReason().getDescription());
    }

    @Test
    void shouldCustomerCreateRequestHaveValidCustomerInfo(){
        CustomerCreateRequest customerCreateRequest = RequestFactory.getCustomerCreationRequest(this.customer);

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
        assertEquals(customer.getMigFlag().toString(), customerInfo.get(Attributes.MIG_FLAG));
    }

    @Test
    void shouldCreateRequestHaveAValidBillingAccountOrderItem(){
        BillingAccountRequest request = RequestFactory.getBillingAccountRequest(this.billingAccount);

        BillingAccountOrderItem orderItem = (BillingAccountOrderItem) request.getOrderItems().get(OrderItemType.ACCOUNT_ORDER_ITEM);
        assertNotNull(orderItem, "Billing Account create request should have a order item accountOrderItem and its null!");

        // validate order item main info
        assertTrue(orderItem.getCorrelationId().length() > 0);
        assertTrue(orderItem.getExternalItemId().length() > 0);
        assertEquals(orderItem.getCustomerId(), this.billingAccount.getCustomerId());
        assertEquals(orderItem.getAccountId(), this.billingAccount.getAccountId());
        assertEquals(orderItem.getAccountName(), this.billingAccount.getAccountName());
        assertEquals(orderItem.getBillingCycleDay(), this.billingAccount.getBillingCycleDay());
        assertEquals(OperationType.CREATE, orderItem.getOperation());

        // validate reason
        assertEquals("OTHER",orderItem.getReason().getCode());
        assertEquals("Migration CREATE Billing Account",orderItem.getReason().getDescription());

        System.out.println(JSONUtils.toJSON(request));
    }

    @Test
    void shouldHaveAListFromAString(){
        List<String> tariffs = Arrays.asList("81016#81017".split("#"));
        System.out.println(JSONUtils.toJSON(tariffs));
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

    private BillingAccount createBillingAccount(){
        BillingAccount billingAccount = new BillingAccount();
        billingAccount.setOperatorId(0);
        billingAccount.setAccountId("d40ac04b01db62");
        billingAccount.setAccountName("d40ac04b01db62");
        billingAccount.setCustomerId("Rock-001");
        billingAccount.setBillingCycleDay(1);

        return  billingAccount;
    }
}