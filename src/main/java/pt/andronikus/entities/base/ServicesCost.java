package pt.andronikus.entities.base;

public class ServicesCost {
    private String customerTrainingCost;
    private String dedicatedApnCost;
    private String apiAccessCost;
    private String locationServiceCost;
    private String smsConsoleCost;

    public ServicesCost(){
    }

    public String getCustomerTrainingCost() {
        return customerTrainingCost;
    }

    public void setCustomerTrainingCost(String customerTrainingCost) {
        this.customerTrainingCost = customerTrainingCost;
    }

    public String getDedicatedApnCost() {
        return dedicatedApnCost;
    }

    public void setDedicatedApnCost(String dedicatedApnCost) {
        this.dedicatedApnCost = dedicatedApnCost;
    }

    public String getApiAccessCost() {
        return apiAccessCost;
    }

    public void setApiAccessCost(String apiAccessCost) {
        this.apiAccessCost = apiAccessCost;
    }

    public String getLocationServiceCost() {
        return locationServiceCost;
    }

    public void setLocationServiceCost(String locationServiceCost) {
        this.locationServiceCost = locationServiceCost;
    }

    public String getSmsConsoleCost() {
        return smsConsoleCost;
    }

    public void setSmsConsoleCost(String smsConsoleCost) {
        this.smsConsoleCost = smsConsoleCost;
    }

    @Override
    public String toString() {
        return "ServicesCost{" +
                "customerTrainingCost=" + customerTrainingCost +
                ", dedicatedApnCost=" + dedicatedApnCost +
                ", apiAccessCost=" + apiAccessCost +
                ", locationServiceCost=" + locationServiceCost +
                ", smsConsoleCost=" + smsConsoleCost +
                '}';
    }
}
