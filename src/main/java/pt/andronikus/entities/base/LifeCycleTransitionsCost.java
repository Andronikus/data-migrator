package pt.andronikus.entities.base;

public class LifeCycleTransitionsCost {
    private Float liveToSuspend;
    private Float liveToStopped;
    private Float stoppedToLive;
    private Float stoppedToSuspend;
    private Float stoppedToStandby;
    private Float preActiveToStopped;
    private Float standbyToSuspend;
    private Float suspendToStandby;
    private Float standbyToStopped;
    private Float suspendToStopped;
    private Float suspendToLive;
    private Float standbyToLive;
    private Float liveToStandby;
    private Float testToStopped;
    private Float preActiveToTest;
    private Float testToLive;
    private Float preActiveToLive;
    private Float admActiveToCanceled;
    private Float admActiveToSuspended;
    private Float admSuspendedToActive;
    private Float admSuspendedToCanceled;

    public LifeCycleTransitionsCost() {
    }

    public Float getLiveToSuspend() {
        return liveToSuspend;
    }

    public void setLiveToSuspend(Float liveToSuspend) {
        this.liveToSuspend = liveToSuspend;
    }

    public Float getLiveToStopped() {
        return liveToStopped;
    }

    public void setLiveToStopped(Float liveToStopped) {
        this.liveToStopped = liveToStopped;
    }

    public Float getStoppedToLive() {
        return stoppedToLive;
    }

    public void setStoppedToLive(Float stoppedToLive) {
        this.stoppedToLive = stoppedToLive;
    }

    public Float getStoppedToSuspend() {
        return stoppedToSuspend;
    }

    public void setStoppedToSuspend(Float stoppedToSuspend) {
        this.stoppedToSuspend = stoppedToSuspend;
    }

    public Float getStoppedToStandby() {
        return stoppedToStandby;
    }

    public void setStoppedToStandby(Float stoppedToStandby) {
        this.stoppedToStandby = stoppedToStandby;
    }

    public Float getPreActiveToStopped() {
        return preActiveToStopped;
    }

    public void setPreActiveToStopped(Float preActiveToStopped) {
        this.preActiveToStopped = preActiveToStopped;
    }

    public Float getStandbyToSuspend() {
        return standbyToSuspend;
    }

    public void setStandbyToSuspend(Float standbyToSuspend) {
        this.standbyToSuspend = standbyToSuspend;
    }

    public Float getSuspendToStandby() {
        return suspendToStandby;
    }

    public void setSuspendToStandby(Float suspendToStandby) {
        this.suspendToStandby = suspendToStandby;
    }

    public Float getStandbyToStopped() {
        return standbyToStopped;
    }

    public void setStandbyToStopped(Float standbyToStopped) {
        this.standbyToStopped = standbyToStopped;
    }

    public Float getSuspendToStopped() {
        return suspendToStopped;
    }

    public void setSuspendToStopped(Float suspendToStopped) {
        this.suspendToStopped = suspendToStopped;
    }

    public Float getSuspendToLive() {
        return suspendToLive;
    }

    public void setSuspendToLive(Float suspendToLive) {
        this.suspendToLive = suspendToLive;
    }

    public Float getStandbyToLive() {
        return standbyToLive;
    }

    public void setStandbyToLive(Float standbyToLive) {
        this.standbyToLive = standbyToLive;
    }

    public Float getLiveToStandby() {
        return liveToStandby;
    }

    public void setLiveToStandby(Float liveToStandby) {
        this.liveToStandby = liveToStandby;
    }

    public Float getTestToStopped() {
        return testToStopped;
    }

    public void setTestToStopped(Float testToStopped) {
        this.testToStopped = testToStopped;
    }

    public Float getPreActiveToTest() {
        return preActiveToTest;
    }

    public void setPreActiveToTest(Float preActiveToTest) {
        this.preActiveToTest = preActiveToTest;
    }

    public Float getTestToLive() {
        return testToLive;
    }

    public void setTestToLive(Float testToLive) {
        this.testToLive = testToLive;
    }

    public Float getPreActiveToLive() {
        return preActiveToLive;
    }

    public void setPreActiveToLive(Float preActiveToLive) {
        this.preActiveToLive = preActiveToLive;
    }

    public Float getAdmActiveToCanceled() {
        return admActiveToCanceled;
    }

    public void setAdmActiveToCanceled(Float admActiveToCanceled) {
        this.admActiveToCanceled = admActiveToCanceled;
    }

    public Float getAdmActiveToSuspended() {
        return admActiveToSuspended;
    }

    public void setAdmActiveToSuspended(Float admActiveToSuspended) {
        this.admActiveToSuspended = admActiveToSuspended;
    }

    public Float getAdmSuspendedToActive() {
        return admSuspendedToActive;
    }

    public void setAdmSuspendedToActive(Float admSuspendedToActive) {
        this.admSuspendedToActive = admSuspendedToActive;
    }

    public Float getAdmSuspendedToCanceled() {
        return admSuspendedToCanceled;
    }

    public void setAdmSuspendedToCanceled(Float admSuspendedToCanceled) {
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
