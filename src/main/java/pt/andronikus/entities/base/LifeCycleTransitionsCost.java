package pt.andronikus.entities.base;

public class LifeCycleTransitionsCost {
    private Double liveToSuspend;
    private Double liveToStopped;
    private Double stoppedToLive;
    private Double stoppedToSuspend;
    private Double stoppedToStandby;
    private Double preActiveToStopped;
    private Double standbyToSuspend;
    private Double suspendToStandby;
    private Double standbyToStopped;
    private Double suspendToStopped;
    private Double suspendToLive;
    private Double standbyToLive;
    private Double liveToStandby;
    private Double testToStopped;
    private Double preActiveToTest;
    private Double testToLive;
    private Double preActiveToLive;
    private Double admActiveToCanceled;
    private Double admActiveToSuspended;
    private Double admSuspendedToActive;
    private Double admSuspendedToCanceled;

    public LifeCycleTransitionsCost() {
    }

    public Double getLiveToSuspend() {
        return liveToSuspend;
    }

    public void setLiveToSuspend(Double liveToSuspend) {
        this.liveToSuspend = liveToSuspend;
    }

    public Double getLiveToStopped() {
        return liveToStopped;
    }

    public void setLiveToStopped(Double liveToStopped) {
        this.liveToStopped = liveToStopped;
    }

    public Double getStoppedToLive() {
        return stoppedToLive;
    }

    public void setStoppedToLive(Double stoppedToLive) {
        this.stoppedToLive = stoppedToLive;
    }

    public Double getStoppedToSuspend() {
        return stoppedToSuspend;
    }

    public void setStoppedToSuspend(Double stoppedToSuspend) {
        this.stoppedToSuspend = stoppedToSuspend;
    }

    public Double getStoppedToStandby() {
        return stoppedToStandby;
    }

    public void setStoppedToStandby(Double stoppedToStandby) {
        this.stoppedToStandby = stoppedToStandby;
    }

    public Double getPreActiveToStopped() {
        return preActiveToStopped;
    }

    public void setPreActiveToStopped(Double preActiveToStopped) {
        this.preActiveToStopped = preActiveToStopped;
    }

    public Double getStandbyToSuspend() {
        return standbyToSuspend;
    }

    public void setStandbyToSuspend(Double standbyToSuspend) {
        this.standbyToSuspend = standbyToSuspend;
    }

    public Double getSuspendToStandby() {
        return suspendToStandby;
    }

    public void setSuspendToStandby(Double suspendToStandby) {
        this.suspendToStandby = suspendToStandby;
    }

    public Double getStandbyToStopped() {
        return standbyToStopped;
    }

    public void setStandbyToStopped(Double standbyToStopped) {
        this.standbyToStopped = standbyToStopped;
    }

    public Double getSuspendToStopped() {
        return suspendToStopped;
    }

    public void setSuspendToStopped(Double suspendToStopped) {
        this.suspendToStopped = suspendToStopped;
    }

    public Double getSuspendToLive() {
        return suspendToLive;
    }

    public void setSuspendToLive(Double suspendToLive) {
        this.suspendToLive = suspendToLive;
    }

    public Double getStandbyToLive() {
        return standbyToLive;
    }

    public void setStandbyToLive(Double standbyToLive) {
        this.standbyToLive = standbyToLive;
    }

    public Double getLiveToStandby() {
        return liveToStandby;
    }

    public void setLiveToStandby(Double liveToStandby) {
        this.liveToStandby = liveToStandby;
    }

    public Double getTestToStopped() {
        return testToStopped;
    }

    public void setTestToStopped(Double testToStopped) {
        this.testToStopped = testToStopped;
    }

    public Double getPreActiveToTest() {
        return preActiveToTest;
    }

    public void setPreActiveToTest(Double preActiveToTest) {
        this.preActiveToTest = preActiveToTest;
    }

    public Double getTestToLive() {
        return testToLive;
    }

    public void setTestToLive(Double testToLive) {
        this.testToLive = testToLive;
    }

    public Double getPreActiveToLive() {
        return preActiveToLive;
    }

    public void setPreActiveToLive(Double preActiveToLive) {
        this.preActiveToLive = preActiveToLive;
    }

    public Double getAdmActiveToCanceled() {
        return admActiveToCanceled;
    }

    public void setAdmActiveToCanceled(Double admActiveToCanceled) {
        this.admActiveToCanceled = admActiveToCanceled;
    }

    public Double getAdmActiveToSuspended() {
        return admActiveToSuspended;
    }

    public void setAdmActiveToSuspended(Double admActiveToSuspended) {
        this.admActiveToSuspended = admActiveToSuspended;
    }

    public Double getAdmSuspendedToActive() {
        return admSuspendedToActive;
    }

    public void setAdmSuspendedToActive(Double admSuspendedToActive) {
        this.admSuspendedToActive = admSuspendedToActive;
    }

    public Double getAdmSuspendedToCanceled() {
        return admSuspendedToCanceled;
    }

    public void setAdmSuspendedToCanceled(Double admSuspendedToCanceled) {
        this.admSuspendedToCanceled = admSuspendedToCanceled;
    }

    @Override
    public String toString() {
        return "LifeCycleTransitionsCost{" +
                "liveToSuspend=" + liveToSuspend +
                ", liveToStopped=" + liveToStopped +
                ", stoppedToLive=" + stoppedToLive +
                ", stoppedToSuspend=" + stoppedToSuspend +
                ", stoppedToStandby=" + stoppedToStandby +
                ", preActiveToStopped=" + preActiveToStopped +
                ", standbyToSuspend=" + standbyToSuspend +
                ", suspendToStandby=" + suspendToStandby +
                ", standbyToStopped=" + standbyToStopped +
                ", suspendToStopped=" + suspendToStopped +
                ", suspendToLive=" + suspendToLive +
                ", standbyToLive=" + standbyToLive +
                ", liveToStandby=" + liveToStandby +
                ", testToStopped=" + testToStopped +
                ", preActiveToTest=" + preActiveToTest +
                ", testToLive=" + testToLive +
                ", preActiveToLive=" + preActiveToLive +
                ", admActiveToCanceled=" + admActiveToCanceled +
                ", admActiveToSuspended=" + admActiveToSuspended +
                ", admSuspendedToActive=" + admSuspendedToActive +
                ", admSuspendedToCanceled=" + admSuspendedToCanceled +
                '}';
    }
}
