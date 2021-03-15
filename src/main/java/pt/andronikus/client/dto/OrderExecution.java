package pt.andronikus.client.dto;

import pt.andronikus.client.enums.OrderItemType;
import pt.andronikus.constants.Global;
import pt.andronikus.singletons.AppConfiguration;

import java.util.*;

public class OrderExecution {
    private final String orderExternalId;
    private final String orderSource;
    private final String extChannel;
    private String orderCorrelationId;
    private ExecutionMode executionMode;
    private Map<OrderItemType,OrderItem> orderItems = new HashMap<>();
    private Map<String,Object> otherInfo = new HashMap<>();

    public OrderExecution() {
        this.orderExternalId = UUID.randomUUID().toString();
        this.orderSource = AppConfiguration.INSTANCE.getConfiguration(Global.ORDER_SOURCE).toString();
        this.extChannel = AppConfiguration.INSTANCE.getConfiguration(Global.CHANNEL).toString();
    }

    public void addOtherInfoEntry(String attribute, Object value){
        this.otherInfo.putIfAbsent(attribute,value);
    }

    public void setOrderCorrelationId(String orderCorrelationId) {
        this.orderCorrelationId = orderCorrelationId;
    }

    public String getOrderExternalId() {
        return orderExternalId;
    }
    public String getOrderSource() {
        return orderSource;
    }

    public String getExtChannel() {
        return extChannel;
    }

    public ExecutionMode getExecutionMode() {
        return executionMode;
    }

    public void setExecutionMode(ExecutionMode executionMode) {
        this.executionMode = executionMode;
    }

    public Map<OrderItemType, OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Map<OrderItemType, OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setOrderItem(OrderItemType orderItemType, OrderItem orderItem) {
        this.orderItems.putIfAbsent(orderItemType,orderItem);
    }

    public String getOrderCorrelationId() {
        return orderCorrelationId;
    }

    public Map<String, Object> getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(Map<String, Object> otherInfo) {
        this.otherInfo = otherInfo;
    }

    @Override
    public String toString() {
        return "OrderExecution{" +
                "orderExternalId='" + orderExternalId + '\'' +
                ", orderSource='" + orderSource + '\'' +
                ", orderCorrelationId='" + orderCorrelationId + '\'' +
                ", extChannel='" + extChannel + '\'' +
                ", executionMode=" + executionMode +
                ", orderItems=" + orderItems +
                ", otherInfo=" + otherInfo +
                '}';
    }
}
