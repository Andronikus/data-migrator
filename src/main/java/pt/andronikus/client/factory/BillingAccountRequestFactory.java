package pt.andronikus.client.factory;

import pt.andronikus.client.dto.*;
import pt.andronikus.client.enums.Attributes;
import pt.andronikus.client.enums.OperationType;
import pt.andronikus.client.enums.OrderItemType;
import pt.andronikus.client.request.BillingAccountRequest;
import pt.andronikus.entities.BillingAccount;

import java.util.ArrayList;
import java.util.List;

public class BillingAccountRequestFactory {
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
