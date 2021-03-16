package pt.andronikus.entities.base;

public class LifeCycleTransitionsCost {
    private String liveToSuspend;
    private String liveToStopped;
    private String stoppedToLive;
    private String stoppedToSuspend;
    private String stoppedToStandby;
    private String preActiveToStopped;
    private String standbyToSuspend;
    private String suspendToStandby;
    private String standbyToStopped;
    private String suspendToStopped;
    private String suspendToLive;
    private String standbyToLive;
    private String liveToStandby;
    private String testToStopped;
    private String preActiveToTest;
    private String testToLive;
    private String preActiveToLive;
    private String admActiveToCanceled;
    private String admActiveToSuspended;
    private String admSuspendedToActive;
    private String admSuspendedToCanceled;

    public LifeCycleTransitionsCost() {
    }

    public String getLiveToSuspend() {
        return liveToSuspend;
    }

    public void setLiveToSuspend(String liveToSuspend) {
        this.liveToSuspend = liveToSuspend;
    }

    public String getLiveToStopped() {
        return liveToStopped;
    }

    public void setLiveToStopped(String liveToStopped) {
        this.liveToStopped = liveToStopped;
    }

    public String getStoppedToLive() {
        return stoppedToLive;
    }

    public void setStoppedToLive(String stoppedToLive) {
        this.stoppedToLive = stoppedToLive;
    }

    public String getStoppedToSuspend() {
        return stoppedToSuspend;
    }

    public void setStoppedToSuspend(String stoppedToSuspend) {
        this.stoppedToSuspend = stoppedToSuspend;
    }

    public String getStoppedToStandby() {
        return stoppedToStandby;
    }

    public void setStoppedToStandby(String stoppedToStandby) {
        this.stoppedToStandby = stoppedToStandby;
    }

    public String getPreActiveToStopped() {
        return preActiveToStopped;
    }

    public void setPreActiveToStopped(String preActiveToStopped) {
        this.preActiveToStopped = preActiveToStopped;
    }

    public String getStandbyToSuspend() {
        return standbyToSuspend;
    }

    public void setStandbyToSuspend(String standbyToSuspend) {
        this.standbyToSuspend = standbyToSuspend;
    }

    public String getSuspendToStandby() {
        return suspendToStandby;
    }

    public void setSuspendToStandby(String suspendToStandby) {
        this.suspendToStandby = suspendToStandby;
    }

    public String getStandbyToStopped() {
        return standbyToStopped;
    }

    public void setStandbyToStopped(String standbyToStopped) {
        this.standbyToStopped = standbyToStopped;
    }

    public String getSuspendToStopped() {
        return suspendToStopped;
    }

    public void setSuspendToStopped(String suspendToStopped) {
        this.suspendToStopped = suspendToStopped;
    }

    public String getSuspendToLive() {
        return suspendToLive;
    }

    public void setSuspendToLive(String suspendToLive) {
        this.suspendToLive = suspendToLive;
    }

    public String getStandbyToLive() {
        return standbyToLive;
    }

    public void setStandbyToLive(String standbyToLive) {
        this.standbyToLive = standbyToLive;
    }

    public String getLiveToStandby() {
        return liveToStandby;
    }

    public void setLiveToStandby(String liveToStandby) {
        this.liveToStandby = liveToStandby;
    }

    public String getTestToStopped() {
        return testToStopped;
    }

    public void setTestToStopped(String testToStopped) {
        this.testToStopped = testToStopped;
    }

    public String getPreActiveToTest() {
        return preActiveToTest;
    }

    public void setPreActiveToTest(String preActiveToTest) {
        this.preActiveToTest = preActiveToTest;
    }

    public String getTestToLive() {
        return testToLive;
    }

    public void setTestToLive(String testToLive) {
        this.testToLive = testToLive;
    }

    public String getPreActiveToLive() {
        return preActiveToLive;
    }

    public void setPreActiveToLive(String preActiveToLive) {
        this.preActiveToLive = preActiveToLive;
    }

    public String getAdmActiveToCanceled() {
        return admActiveToCanceled;
    }

    public void setAdmActiveToCanceled(String admActiveToCanceled) {
        this.admActiveToCanceled = admActiveToCanceled;
    }

    public String getAdmActiveToSuspended() {
        return admActiveToSuspended;
    }

    public void setAdmActiveToSuspended(String admActiveToSuspended) {
        this.admActiveToSuspended = admActiveToSuspended;
    }

    public String getAdmSuspendedToActive() {
        return admSuspendedToActive;
    }

    public void setAdmSuspendedToActive(String admSuspendedToActive) {
        this.admSuspendedToActive = admSuspendedToActive;
    }

    public String getAdmSuspendedToCanceled() {
        return admSuspendedToCanceled;
    }

    public void setAdmSuspendedToCanceled(String admSuspendedToCanceled) {
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
