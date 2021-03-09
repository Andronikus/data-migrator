package pt.andronikus.client.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public enum OrderItemType {
    CUSTOMER_ORDER_ITEM("customerOrderItem"),
    ACCOUNT_ORDER_ITEM("accountOrderItem"),
    AGREEMENT_ORDER_ITEM("agreementOrderItem"),
    WORK_ORDER_ITEM("workOrderItem");

    private final String orderItemType;

    OrderItemType(String orderItemType) {
        this.orderItemType = orderItemType;
    }

    @JsonValue
    public String getOrderItemType() {
        return orderItemType;
    }

    @JsonCreator
    public OrderItemType fromString(String orderItemType) {
        for(OrderItemType type : OrderItemType.values()){
            if(Objects.equals(type.getOrderItemType(), orderItemType)){
                return type;
            }
        }
        return null;
    }
}
