package pt.andronikus.entities.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TariffCostPerState {
    @JsonProperty("tariff_id")
    private String tariffId;
    @JsonProperty("state_preactive")
    private Double costStatePreActive;
    @JsonProperty("state_live")
    private Double costStateLive;
    @JsonProperty("state_stopped")
    private Double costStateStopped;
    @JsonProperty("state_suspended")
    private Double costStateSuspended;
    @JsonProperty("state_standby")
    private Double costStateStandBy;
    @JsonProperty("state_test")
    private Double costStateTest;

    public TariffCostPerState() {
    }

    public TariffCostPerState(String tariffId, Double costStatePreActive, Double costStateLive, Double costStateStopped, Double costStateSuspended, Double costStateStandBy, Double costStateTest) {
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

    public Double getCostStatePreActive() {
        return costStatePreActive;
    }

    public void setCostStatePreActive(Double costStatePreActive) {
        this.costStatePreActive = costStatePreActive;
    }

    public Double getCostStateLive() {
        return costStateLive;
    }

    public void setCostStateLive(Double costStateLive) {
        this.costStateLive = costStateLive;
    }

    public Double getCostStateStopped() {
        return costStateStopped;
    }

    public void setCostStateStopped(Double costStateStopped) {
        this.costStateStopped = costStateStopped;
    }

    public Double getCostStateSuspended() {
        return costStateSuspended;
    }

    public void setCostStateSuspended(Double costStateSuspended) {
        this.costStateSuspended = costStateSuspended;
    }

    public Double getCostStateStandBy() {
        return costStateStandBy;
    }

    public void setCostStateStandBy(Double costStateStandBy) {
        this.costStateStandBy = costStateStandBy;
    }

    public Double getCostStateTest() {
        return costStateTest;
    }

    public void setCostStateTest(Double costStateTest) {
        this.costStateTest = costStateTest;
    }

    public void setStateCost(String state, String cost){
        double costOfState = Double.parseDouble(cost);

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
