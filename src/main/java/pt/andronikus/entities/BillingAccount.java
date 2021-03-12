package pt.andronikus.entities;

import pt.andronikus.entities.base.BaseEntity;

public class BillingAccount extends BaseEntity {
    private String accountId;
    private String accountName;
    private String customerId;
    private Integer billingCycleDay;

    public BillingAccount() {
        super();
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getBillingCycleDay() {
        return billingCycleDay;
    }

    public void setBillingCycleDay(Integer billingCycleDay) {
        this.billingCycleDay = billingCycleDay;
    }

    @Override
    public String toString() {
        return "BillingAccount{" +
                "operatorId=" + operatorId +
                ", correlationId='" + correlationId + '\'' +
                ", orderCorrelationId='" + orderCorrelationId + '\'' +
                ", migStatus='" + migStatus + '\'' +
                ", accountId='" + accountId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", billingCycleDay=" + billingCycleDay +
                '}';
    }
}
