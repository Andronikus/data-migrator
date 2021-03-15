package pt.andronikus.client.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.andronikus.client.dto.*;
import pt.andronikus.client.enums.Attributes;
import pt.andronikus.client.enums.OperationType;
import pt.andronikus.client.enums.OrderItemType;
import pt.andronikus.client.request.CustomerRequest;
import pt.andronikus.client.utils.JSONUtils;
import pt.andronikus.constants.Global;
import pt.andronikus.constants.MigrationFlag;
import pt.andronikus.entities.Customer;
import pt.andronikus.singletons.AppConfiguration;

import java.util.ArrayList;
import java.util.List;

public class CustomerRequestFactory {
    final static Logger LOGGER = LoggerFactory.getLogger(CustomerRequestFactory.class);
    final static String LOG_PREFIX = CustomerRequestFactory.class.getSimpleName() + " :: ";

    public static CustomerRequest getCustomerCreationRequest(Customer customer){
        final String METHOD_NAME = LOG_PREFIX + " getCustomerCreationRequest - ";

        CustomerRequest orderExecution = new CustomerRequest();
        ExecutionMode executionMode = new AsyncExecutionMode(AppConfiguration.INSTANCE.getConfiguration(Global.CALLBACK_URL).toString() + "/customer");
        orderExecution.setExecutionMode(executionMode);
        orderExecution.setOrderCorrelationId(customer.getOrderCorrelationId());

        CustomerOrderItem orderItem = new CustomerOrderItem(OperationType.CREATE);
        orderItem.setExternalItemId(orderExecution.getOrderExternalId() + "_01");
        orderItem.setCorrelationId(customer.getCorrelationId());

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
        entries.add(new EntryObject(Attributes.MIG_FLAG, MigrationFlag.IN_MIGRATION));

        orderItem.addOtherInfoEntry(Attributes.ENTRY,entries);

        Reason reason = new Reason("OTHER", "Migration CREATE Customer");
        orderItem.setReason(reason);
        orderExecution.setOrderItem(OrderItemType.CUSTOMER_ORDER_ITEM,orderItem);

        if(LOGGER.isDebugEnabled()){
            LOGGER.debug(METHOD_NAME);
            LOGGER.debug(JSONUtils.toJSON(orderExecution));
        }

        return orderExecution;
    }

    public static CustomerRequest getCustomerUpdateRequest(Customer customer){
        final String METHOD_NAME = LOG_PREFIX + " getCustomerUpdateRequest - ";

        CustomerRequest orderExecution = new CustomerRequest();
        ExecutionMode executionMode = new AsyncExecutionMode(AppConfiguration.INSTANCE.getConfiguration(Global.CALLBACK_URL).toString() + "/customer");
        orderExecution.setExecutionMode(executionMode);
        orderExecution.setOrderCorrelationId(customer.getOrderCorrelationId());

        CustomerOrderItem orderItem = new CustomerOrderItem(OperationType.UPDATE);
        orderItem.setExternalItemId(orderExecution.getOrderExternalId() + "_01");
        orderItem.setCorrelationId(customer.getCorrelationId());

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
        entries.add(new EntryObject(Attributes.MIG_FLAG, MigrationFlag.END_MIGRATION));

        orderItem.addOtherInfoEntry(Attributes.ENTRY,entries);

        Reason reason = new Reason("OTHER", "Migration UPDATE Customer");
        orderItem.setReason(reason);
        orderExecution.setOrderItem(OrderItemType.CUSTOMER_ORDER_ITEM,orderItem);

        if(LOGGER.isDebugEnabled()){
            LOGGER.debug(METHOD_NAME);
            LOGGER.debug(JSONUtils.toJSON(orderExecution));
        }

        return orderExecution;
    }



}
