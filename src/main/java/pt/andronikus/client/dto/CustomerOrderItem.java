package pt.andronikus.client.dto;

import pt.andronikus.client.enums.OperationType;

public class CustomerOrderItem extends OrderItem {
    private String customerId;
    private String customerName;

    public CustomerOrderItem() {
        super();
    }

    public CustomerOrderItem(OperationType operation) {
        super(operation);
    }

    public CustomerOrderItem(String customerId, String customerName) {
        super();
        this.customerId = customerId;
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "CustomerOrderItem{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", correlationId='" + correlationId + '\'' +
                ", externalItemId='" + externalItemId + '\'' +
                ", otherInfo=" + otherInfo +
                ", reason=" + reason +
                ", operation=" + operation +
                '}';
    }
}
