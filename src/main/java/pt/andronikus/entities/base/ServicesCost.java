package pt.andronikus.entities.base;

public class ServicesCost {
    private Double customerTrainingCost;
    private Double dedicatedApnCost;
    private Double apiAccessCost;
    private Double locationServiceCost;
    private Double smsConsoleCost;

    public ServicesCost(){
    }

    public Double getCustomerTrainingCost() {
        return customerTrainingCost;
    }

    public void setCustomerTrainingCost(Double customerTrainingCost) {
        this.customerTrainingCost = customerTrainingCost;
    }

    public Double getDedicatedApnCost() {
        return dedicatedApnCost;
    }

    public void setDedicatedApnCost(Double dedicatedApnCost) {
        this.dedicatedApnCost = dedicatedApnCost;
    }

    public Double getApiAccessCost() {
        return apiAccessCost;
    }

    public void setApiAccessCost(Double apiAccessCost) {
        this.apiAccessCost = apiAccessCost;
    }

    public Double getLocationServiceCost() {
        return locationServiceCost;
    }

    public void setLocationServiceCost(Double locationServiceCost) {
        this.locationServiceCost = locationServiceCost;
    }

    public Double getSmsConsoleCost() {
        return smsConsoleCost;
    }

    public void setSmsConsoleCost(Double smsConsoleCost) {
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
