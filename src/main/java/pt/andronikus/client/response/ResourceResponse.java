package pt.andronikus.client.response;

import pt.andronikus.client.enums.OrderItemType;
import pt.andronikus.client.utils.JSONUtils;

public class ResourceResponse {

    private final OrderExecutionResponse response;

    public ResourceResponse(OrderExecutionResponse response) {
        this.response = response;
    }

    public String getErrorCode(){
        return this.response.getOrderItems().get(OrderItemType.AGREEMENT_ORDER_ITEM.getOrderItemType()).getErrorCode();
    }

    public String getCustomerId(){
        return this.response.getOrderItems().get(OrderItemType.AGREEMENT_ORDER_ITEM.getOrderItemType()).getCustomerId();
    }

    public String getOrderItemStatus(){
        return this.response.getOrderItems().get(OrderItemType.AGREEMENT_ORDER_ITEM.getOrderItemType()).getOrderItemStatus();
    }

    public String getOrderCorrelationId(){
        return this.response.getOrderCorrelationId();
    }

    public String getOrderExternalId(){
        return this.response.getOrderExternalId();
    }

    public String getAsJson(){
        return this.response.getAsJson();
    }

    public String getOrderSource(){
        return this.response.getOrderSource();
    }

    public String getOrderStatus(){
        return this.response.getOrderStatus();
    }
}
