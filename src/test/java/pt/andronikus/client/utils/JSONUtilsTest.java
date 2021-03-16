package pt.andronikus.client.utils;

import org.junit.jupiter.api.Test;
import pt.andronikus.client.response.CustomerResponse;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class JSONUtilsTest {

    @Test
    void shouldParseCustomerCreateAsynResponse(){

        String response = "{\"orderExternalId\": \"d839d1e5-35bc-462b-8ed7-4ada20d0add9\",\"orderSource\": \"MIG-R8-R9\",\"orderCorrelationId\": \"MIG_CUST_BD992E4CFD434CA6E053CE53700A52B0\",\"orderStatus\": \"STATUS_NOT_EXECUTED\",\"orderItems\": {\"customerOrderItem\": {\"externalItemId\": \"d839d1e5-35bc-462b-8ed7-4ada20d0add9_01\",\"customerId\": \"f97369847cTR01\",\"orderItemStatus\": \"STATUS_NOT_EXECUTED\",\"errorCode\": \"ASM_0008\",\"errorDescription\": \"The order was accepted for processing. Order will be executed immediately\"}}}";

        CustomerResponse customerResponse = JSONUtils.toClass(response, CustomerResponse.class);

        assertNotNull(customerResponse, "Customer Response after deserialize should not be null");
        assertEquals("d839d1e5-35bc-462b-8ed7-4ada20d0add9", customerResponse.getOrderExternalId());
        assertEquals("MIG-R8-R9", customerResponse.getOrderSource());
        assertEquals("MIG_CUST_BD992E4CFD434CA6E053CE53700A52B0", customerResponse.getOrderCorrelationId());
        assertEquals("STATUS_NOT_EXECUTED", customerResponse.getOrderStatus());

        assertEquals("f97369847cTR01", customerResponse.getCustomerId());
        assertEquals("STATUS_NOT_EXECUTED", customerResponse.getOrderItemStatus());
        assertEquals("ASM_0008", customerResponse.getErrorCode());
    }

}