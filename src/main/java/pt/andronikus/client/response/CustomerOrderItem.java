package pt.andronikus.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerOrderItem {
    @JsonProperty
    private String externalItemId;
    @JsonProperty
    private String customerId;
    @JsonProperty
    private String orderItemStatus;
    @JsonProperty
    private String errorCode;
    @JsonProperty
    private String errorDescription;


    public CustomerOrderItem() {
    }

    public String getExternalItemId() {
        return externalItemId;
    }

    public void setExternalItemId(String externalItemId) {
        this.externalItemId = externalItemId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderItemStatus() {
        return orderItemStatus;
    }

    public void setOrderItemStatus(String orderItemStatus) {
        this.orderItemStatus = orderItemStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    @Override
    public String toString() {
        return "CustomerOrderItem{" +
                "externalItemId='" + externalItemId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderItemStatus='" + orderItemStatus + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorDescription='" + errorDescription + '\'' +
                '}';
    }
}
