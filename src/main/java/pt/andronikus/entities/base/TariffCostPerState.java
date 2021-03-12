package pt.andronikus.entities.base;

public class TariffCostPerState {
    private String tariffId;
    private Float costStatePreActive;
    private Float costStateLive;
    private Float costStateStopped;
    private Float costStateSuspended;
    private Float costStateStandBy;
    private Float costStateTest;

    public TariffCostPerState() {
    }

    public TariffCostPerState(String tariffId) {
        this.tariffId = tariffId;
    }

    public String getTariffId() {
        return tariffId;
    }

    public void setTariffId(String tariffId) {
        this.tariffId = tariffId;
    }

    public Float getCostStatePreActive() {
        return costStatePreActive;
    }

    public void setCostStatePreActive(Float costStatePreActive) {
        this.costStatePreActive = costStatePreActive;
    }

    public Float getCostStateLive() {
        return costStateLive;
    }

    public void setCostStateLive(Float costStateLive) {
        this.costStateLive = costStateLive;
    }

    public Float getCostStateStopped() {
        return costStateStopped;
    }

    public void setCostStateStopped(Float costStateStopped) {
        this.costStateStopped = costStateStopped;
    }

    public Float getCostStateSuspended() {
        return costStateSuspended;
    }

    public void setCostStateSuspended(Float costStateSuspended) {
        this.costStateSuspended = costStateSuspended;
    }

    public Float getCostStateStandBy() {
        return costStateStandBy;
    }

    public void setCostStateStandBy(Float costStateStandBy) {
        this.costStateStandBy = costStateStandBy;
    }

    public Float getCostStateTest() {
        return costStateTest;
    }

    public void setCostStateTest(Float costStateTest) {
        this.costStateTest = costStateTest;
    }

    public void setStateCost(String state, String cost){
        float costOfState = Float.parseFloat(cost);

        if (state != null && state.length() > 0){
            switch (state.toUpperCase()){
                case "PREACTIVE":
                    setCostStatePreActive(costOfState);
                    break;
                case "LIVE":
                    setCostStateLive(costOfState);
                    break;
                case "STOPPED":
                    setCostStateStopped(costOfState);
                    break;
                case "SUSPENDED":
                    setCostStateSuspended(costOfState);
                    break;
                case "STANDBY":
                    setCostStateStandBy(costOfState);
                case "TEST":
                    setCostStateTest(costOfState);
                default:
            }
        }
    }

    @Override
    public String toString() {
        return "TariffCostPerState{" +
                "tariffId='" + tariffId + '\'' +
                ", costStatePreActive=" + costStatePreActive +
                ", costStateLive=" + costStateLive +
                ", costStateStopped=" + costStateStopped +
                ", costStateSuspended=" + costStateSuspended +
                ", costStateStandBy=" + costStateStandBy +
                ", costStateTest=" + costStateTest +
                '}';
    }
}
