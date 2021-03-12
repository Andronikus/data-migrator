package pt.andronikus.entities.base;

public class ServicesCost {
    private Float customerTrainingCost;
    private Float dedicatedApnCost;
    private Float apiAccessCost;
    private Float locationServiceCost;
    private Float smsConsoleCost;

    public ServicesCost(){
    }

    public Float getCustomerTrainingCost() {
        return customerTrainingCost;
    }

    public void setCustomerTrainingCost(Float customerTrainingCost) {
        this.customerTrainingCost = customerTrainingCost;
    }

    public Float getDedicatedApnCost() {
        return dedicatedApnCost;
    }

    public void setDedicatedApnCost(Float dedicatedApnCost) {
        this.dedicatedApnCost = dedicatedApnCost;
    }

    public Float getApiAccessCost() {
        return apiAccessCost;
    }

    public void setApiAccessCost(Float apiAccessCost) {
        this.apiAccessCost = apiAccessCost;
    }

    public Float getLocationServiceCost() {
        return locationServiceCost;
    }

    public void setLocationServiceCost(Float locationServiceCost) {
        this.locationServiceCost = locationServiceCost;
    }

    public Float getSmsConsoleCost() {
        return smsConsoleCost;
    }

    public void setSmsConsoleCost(Float smsConsoleCost) {
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
