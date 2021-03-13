package pt.andronikus.entities.base;

import java.util.ArrayList;
import java.util.List;

public class QosInfo {
    private boolean qosEnabled;
    private String qosResourceDefault;
    private List<String> qosLevels;

    public QosInfo(boolean qosEnabled) {
        this.qosEnabled = qosEnabled;
        this.qosLevels = new ArrayList<>();
    }

    public boolean isQosEnabled() {
        return qosEnabled;
    }

    public void setQosEnabled(boolean qosEnabled) {
        this.qosEnabled = qosEnabled;
    }

    public String getQosResourceDefault() {
        return qosResourceDefault;
    }

    public void setQosResourceDefault(String qosResourceDefault) {
        this.qosResourceDefault = qosResourceDefault;
    }

    public List<String> getQosLevels() {
        return qosLevels;
    }

    public void setQosLevels(List<String> qosLevels) {
        this.qosLevels = qosLevels;
    }

    @Override
    public String toString() {
        return "QosInfo{" +
                "qosEnabled=" + qosEnabled +
                ", qosResourceDefault='" + qosResourceDefault + '\'' +
                ", qosLevels=" + qosLevels +
                '}';
    }
}
