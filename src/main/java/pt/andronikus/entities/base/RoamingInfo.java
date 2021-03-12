package pt.andronikus.entities.base;

public class RoamingInfo {
    private boolean roamingEnabled;
    private String initialRoamingStatus;

    public RoamingInfo() {
    }

    public RoamingInfo(boolean roamingEnabled, String initialRoamingStatus) {
        this.roamingEnabled = roamingEnabled;
        this.initialRoamingStatus = initialRoamingStatus;
    }

    public boolean isRoamingEnabled() {
        return roamingEnabled;
    }

    public void setRoamingEnabled(boolean roamingEnabled) {
        this.roamingEnabled = roamingEnabled;
    }

    public String getInitialRoamingStatus() {
        return initialRoamingStatus;
    }

    public void setInitialRoamingStatus(String initialRoamingStatus) {
        this.initialRoamingStatus = initialRoamingStatus;
    }

    @Override
    public String toString() {
        return "RoamingInfo{" +
                "roamingEnabled=" + roamingEnabled +
                ", initialRoamingStatus='" + initialRoamingStatus + '\'' +
                '}';
    }
}
