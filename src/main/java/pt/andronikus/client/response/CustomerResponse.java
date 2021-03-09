package pt.andronikus.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class CustomerResponse {
    @JsonProperty
    private String orderExternalId;
    @JsonProperty
    private String orderSource;
    @JsonProperty
    private String orderCorrelationId;
    @JsonProperty
    private String orderStatus;
    @JsonProperty
    private CustomerOrderItem orderItems;

    public String getOrderExternalId() {
        return orderExternalId;
    }

    public void setOrderExternalId(String orderExternalId) {
        this.orderExternalId = orderExternalId;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getOrderCorrelationId() {
        return orderCorrelationId;
    }

    public void setOrderCorrelationId(String orderCorrelationId) {
        this.orderCorrelationId = orderCorrelationId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public CustomerOrderItem getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(CustomerOrderItem orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "CustomerResponse{" +
                "orderExternalId='" + orderExternalId + '\'' +
                ", orderSource='" + orderSource + '\'' +
                ", orderCorrelationId='" + orderCorrelationId + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }
}
