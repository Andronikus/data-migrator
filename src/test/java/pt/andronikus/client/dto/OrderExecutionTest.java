package pt.andronikus.client.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderExecutionTest {

    @Test
    public void asyncOrderExecutionShouldHaveACallbackUrl(){
        final String CALLBACK = "http://localhost:9000/response";

        OrderExecution orderExecution = new OrderExecution();
        orderExecution.setExecutionMode(new AsyncExecutionMode(CALLBACK));

        assertEquals(CALLBACK, orderExecution.getExecutionMode().getCallbackUrl());
    }


}