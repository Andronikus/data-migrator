package pt.andronikus.client.factory;

import pt.andronikus.client.dto.*;
import pt.andronikus.client.enums.Attributes;
import pt.andronikus.client.enums.OperationType;
import pt.andronikus.client.enums.OrderItemType;
import pt.andronikus.client.request.CustomerCreateRequest;
import pt.andronikus.entities.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerRequestFactory {
    public static CustomerCreateRequest getCustomerCreationRequest(Customer customer){

        CustomerCreateRequest orderExecution = new CustomerCreateRequest();

        ExecutionMode executionMode = new AsyncExecutionMode("http://localhost:9090/migration/callback");
        orderExecution.setExecutionMode(executionMode);

        OrderItem orderItem = new OrderItem();
        orderItem.setOperation(OperationType.CREATE);
        orderItem.setExternalItemId(orderExecution.getOrderExternalId() + "_01");
        orderItem.setCustomerId(customer.getId());
        orderItem.setCustomerName(customer.getName());

        List<EntryObject> entries = new ArrayList<>();

        entries.add(new EntryObject(Attributes.ADDRESS, customer.getAddress()));
        entries.add(new EntryObject(Attributes.PHONE, customer.getPhone()));
        entries.add(new EntryObject(Attributes.EMAIL, customer.getEmail()));
        entries.add(new EntryObject(Attributes.LOCALE, customer.getLocale()));
        entries.add(new EntryObject(Attributes.OPERATOR_ID, customer.getOperatorID().toString()));
        entries.add(new EntryObject(Attributes.TAX_NUMBER, customer.getTaxNumber()));
        entries.add(new EntryObject(Attributes.STATUS, customer.getStatus()));
        entries.add(new EntryObject(Attributes.MIG_FLAG, customer.getMigFlag().toString()));

        orderItem.addOtherInfoEntry(Attributes.ENTRY,entries);

        Reason reason = new Reason("OTHER", "Migration CREATE Customer");
        orderItem.setReason(reason);
        orderExecution.setOrderItem(OrderItemType.CUSTOMER_ORDER_ITEM,orderItem);

        return orderExecution;
    }
}
