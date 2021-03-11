package pt.andronikus.client.factory;

import pt.andronikus.client.dto.*;
import pt.andronikus.client.enums.Attributes;
import pt.andronikus.client.enums.OperationType;
import pt.andronikus.client.enums.OrderItemType;
import pt.andronikus.client.request.BillingAccountRequest;
import pt.andronikus.client.request.CustomerCreateRequest;
import pt.andronikus.entities.BillingAccount;
import pt.andronikus.entities.Customer;

import java.util.ArrayList;
import java.util.List;

public class RequestFactory {
    public static CustomerCreateRequest getCustomerCreationRequest(Customer customer){

        CustomerCreateRequest orderExecution = new CustomerCreateRequest();

        ExecutionMode executionMode = new AsyncExecutionMode("http://localhost:9090/migration/callback");
        orderExecution.setExecutionMode(executionMode);

        CustomerOrderItem orderItem = new CustomerOrderItem(OperationType.CREATE);
        orderItem.setExternalItemId(orderExecution.getOrderExternalId() + "_01");

        orderItem.setCustomerId(customer.getId());
        orderItem.setCustomerName(customer.getName());

        List<EntryObject> entries = new ArrayList<>();

        entries.add(new EntryObject(Attributes.ADDRESS, customer.getAddress()));
        entries.add(new EntryObject(Attributes.PHONE, customer.getPhone()));
        entries.add(new EntryObject(Attributes.EMAIL, customer.getEmail()));
        entries.add(new EntryObject(Attributes.LOCALE, customer.getLocale()));
        entries.add(new EntryObject(Attributes.OPERATOR_ID, customer.getOperatorId().toString()));
        entries.add(new EntryObject(Attributes.TAX_NUMBER, customer.getTaxNumber()));
        entries.add(new EntryObject(Attributes.STATUS, customer.getStatus()));
        entries.add(new EntryObject(Attributes.MIG_FLAG, customer.getMigFlag().toString()));

        orderItem.addOtherInfoEntry(Attributes.ENTRY,entries);

        Reason reason = new Reason("OTHER", "Migration CREATE Customer");
        orderItem.setReason(reason);
        orderExecution.setOrderItem(OrderItemType.CUSTOMER_ORDER_ITEM,orderItem);

        return orderExecution;
    }

    public static BillingAccountRequest getBillingAccountRequest(BillingAccount billingAccount){
        BillingAccountRequest orderExecution = new BillingAccountRequest();

        ExecutionMode executionMode = new AsyncExecutionMode("http://localhost:9090/migration/callback");
        orderExecution.setExecutionMode(executionMode);

        BillingAccountOrderItem orderItem = new BillingAccountOrderItem(OperationType.CREATE);
        orderItem.setExternalItemId(orderExecution.getOrderExternalId() + "_01");

        List<EntryObject> entries = new ArrayList<>();
        entries.add(new EntryObject(Attributes.MIG_FLAG, "1"));
        orderItem.addOtherInfoEntry(Attributes.ENTRY,entries);

        orderItem.setCustomerId(billingAccount.getCustomerId());
        orderItem.setAccountId(billingAccount.getAccountId());
        orderItem.setAccountName(billingAccount.getAccountName());
        orderItem.setBillingCycleDay(billingAccount.getBillingCycleDay());
        orderExecution.setOrderItem(OrderItemType.ACCOUNT_ORDER_ITEM,orderItem);

        Reason reason = new Reason("OTHER", "Migration CREATE Billing Account");
        orderItem.setReason(reason);





        return orderExecution;
    }
}
