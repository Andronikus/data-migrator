package pt.andronikus.dto;

public class MigrationStatusResponse {
    private String customerMigStatus;
    private String billingAccMigStatus;
    private String serviceInstanceMigStatus;
    private String resourceMigStatus;

    public MigrationStatusResponse() {
    }

    public String getCustomerMigStatus() {
        return customerMigStatus;
    }

    public void setCustomerMigStatus(String customerMigStatus) {
        this.customerMigStatus = customerMigStatus;
    }

    public String getBillingAccMigStatus() {
        return billingAccMigStatus;
    }

    public void setBillingAccMigStatus(String billingAccMigStatus) {
        this.billingAccMigStatus = billingAccMigStatus;
    }

    public String getServiceInstanceMigStatus() {
        return serviceInstanceMigStatus;
    }

    public void setServiceInstanceMigStatus(String serviceInstanceMigStatus) {
        this.serviceInstanceMigStatus = serviceInstanceMigStatus;
    }

    public String getResourceMigStatus() {
        return resourceMigStatus;
    }

    public void setResourceMigStatus(String resourceMigStatus) {
        this.resourceMigStatus = resourceMigStatus;
    }

    @Override
    public String toString() {
        return "MigrationStatusResponse{" +
                "customerMigStatus='" + customerMigStatus + '\'' +
                ", billingAccMigStatus='" + billingAccMigStatus + '\'' +
                ", serviceInstanceMigStatus='" + serviceInstanceMigStatus + '\'' +
                ", resourceMigStatus='" + resourceMigStatus + '\'' +
                '}';
    }
}
