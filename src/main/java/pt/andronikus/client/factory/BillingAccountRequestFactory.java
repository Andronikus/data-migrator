package pt.andronikus.client.factory;

import pt.andronikus.client.dto.*;
import pt.andronikus.client.enums.Attributes;
import pt.andronikus.client.enums.OperationType;
import pt.andronikus.client.enums.OrderItemType;
import pt.andronikus.client.request.BillingAccountRequest;
import pt.andronikus.constants.Global;
import pt.andronikus.constants.MigrationFlag;
import pt.andronikus.entities.BillingAccount;
import pt.andronikus.singletons.AppConfiguration;

import java.util.ArrayList;
import java.util.List;

public class BillingAccountRequestFactory {
    public static BillingAccountRequest getBillingAccountCreateRequest(BillingAccount billingAccount){
        BillingAccountRequest orderExecution = new BillingAccountRequest();

        ExecutionMode executionMode = new AsyncExecutionMode(AppConfiguration.INSTANCE.getConfiguration(Global.CALLBACK_URL).toString() + "/billingAccount");
        orderExecution.setExecutionMode(executionMode);
        orderExecution.setOrderCorrelationId(billingAccount.getOrderCorrelationId());

        BillingAccountOrderItem orderItem = new BillingAccountOrderItem(OperationType.CREATE);
        orderItem.setExternalItemId(orderExecution.getOrderExternalId() + "_01");
        orderItem.setCorrelationId(billingAccount.getCorrelationId());

        List<EntryObject> entries = new ArrayList<>();
        entries.add(new EntryObject(Attributes.MIG_FLAG, MigrationFlag.IN_MIGRATION));
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

    public static BillingAccountRequest getBillingAccountUpdateRequest(BillingAccount billingAccount){
        BillingAccountRequest orderExecution = new BillingAccountRequest();

        ExecutionMode executionMode = new AsyncExecutionMode(AppConfiguration.INSTANCE.getConfiguration(Global.CALLBACK_URL).toString() + "/billingAccount");
        orderExecution.setExecutionMode(executionMode);
        orderExecution.setOrderCorrelationId(billingAccount.getOrderCorrelationId());

        BillingAccountOrderItem orderItem = new BillingAccountOrderItem(OperationType.UPDATE);
        orderItem.setExternalItemId(orderExecution.getOrderExternalId() + "_01");
        orderItem.setCorrelationId(billingAccount.getCorrelationId());

        List<EntryObject> entries = new ArrayList<>();
        entries.add(new EntryObject(Attributes.MIG_FLAG, MigrationFlag.END_MIGRATION));
        orderItem.addOtherInfoEntry(Attributes.ENTRY,entries);

        orderItem.setCustomerId(billingAccount.getCustomerId());
        orderItem.setAccountId(billingAccount.getAccountId());
        orderItem.setAccountName(billingAccount.getAccountName());
        orderItem.setBillingCycleDay(billingAccount.getBillingCycleDay());
        orderExecution.setOrderItem(OrderItemType.ACCOUNT_ORDER_ITEM,orderItem);

        Reason reason = new Reason("OTHER", "Migration UPDATE Billing Account");
        orderItem.setReason(reason);
        return orderExecution;
    }
}
