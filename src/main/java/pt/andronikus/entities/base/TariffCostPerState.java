package pt.andronikus.entities.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TariffCostPerState {
    @JsonProperty("tariff_id")
    private String tariffId;
    @JsonProperty("state_preactive")
    private String costStatePreActive;
    @JsonProperty("state_live")
    private String costStateLive;
    @JsonProperty("state_stopped")
    private String costStateStopped;
    @JsonProperty("state_suspended")
    private String costStateSuspended;
    @JsonProperty("state_standby")
    private String costStateStandBy;
    @JsonProperty("state_test")
    private String costStateTest;

    public TariffCostPerState() {
    }

    public TariffCostPerState(String tariffId, String costStatePreActive, String costStateLive, String costStateStopped, String costStateSuspended, String costStateStandBy, String costStateTest) {
        this.tariffId = tariffId;
        this.costStatePreActive = costStatePreActive;
        this.costStateLive = costStateLive;
        this.costStateStopped = costStateStopped;
        this.costStateSuspended = costStateSuspended;
        this.costStateStandBy = costStateStandBy;
        this.costStateTest = costStateTest;
    }

    public String getTariffId() {
        return tariffId;
    }

    public void setTariffId(String tariffId) {
        this.tariffId = tariffId;
    }

    public String getCostStatePreActive() {
        return costStatePreActive;
    }

    public void setCostStatePreActive(String costStatePreActive) {
        this.costStatePreActive = costStatePreActive;
    }

    public String getCostStateLive() {
        return costStateLive;
    }

    public void setCostStateLive(String costStateLive) {
        this.costStateLive = costStateLive;
    }

    public String getCostStateStopped() {
        return costStateStopped;
    }

    public void setCostStateStopped(String costStateStopped) {
        this.costStateStopped = costStateStopped;
    }

    public String getCostStateSuspended() {
        return costStateSuspended;
    }

    public void setCostStateSuspended(String costStateSuspended) {
        this.costStateSuspended = costStateSuspended;
    }

    public String getCostStateStandBy() {
        return costStateStandBy;
    }

    public void setCostStateStandBy(String costStateStandBy) {
        this.costStateStandBy = costStateStandBy;
    }

    public String getCostStateTest() {
        return costStateTest;
    }

    public void setCostStateTest(String costStateTest) {
        this.costStateTest = costStateTest;
    }

    public void setStateCost(String state, String cost){
        // double costOfState = Double.parseDouble(cost);

        if (state != null && state.length() > 0){
            switch (state.toUpperCase()){
                case "PREACTIVE":
                    setCostStatePreActive(cost);
                    break;
                case "LIVE":
                    setCostStateLive(cost);
                    break;
                case "STOPPED":
                    setCostStateStopped(cost);
                    break;
                case "SUSPENDED":
                    setCostStateSuspended(cost);
                    break;
                case "STANDBY":
                    setCostStateStandBy(cost);
                case "TEST":
                    setCostStateTest(cost);
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
