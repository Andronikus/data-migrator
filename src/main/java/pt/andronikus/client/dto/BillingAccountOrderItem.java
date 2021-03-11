package pt.andronikus.client.dto;

import pt.andronikus.client.enums.OperationType;

public class BillingAccountOrderItem extends OrderItem{
    private String customerId;
    private String accountId;
    private String accountName;
    private Integer billingCycleDay;

    public BillingAccountOrderItem() {
    }

    public BillingAccountOrderItem(OperationType operationType) {
        super(operationType);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getBillingCycleDay() {
        return billingCycleDay;
    }

    public void setBillingCycleDay(Integer billingCycleDay) {
        this.billingCycleDay = billingCycleDay;
    }

    @Override
    public String toString() {
        return "BillingAccountOrderItem{" +
                "customerId='" + customerId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", billingCycleDay=" + billingCycleDay +
                ", correlationId='" + correlationId + '\'' +
                ", externalItemId='" + externalItemId + '\'' +
                ", otherInfo=" + otherInfo +
                ", reason=" + reason +
                ", operation=" + operation +
                '}';
    }
}
