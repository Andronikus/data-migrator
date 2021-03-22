package pt.andronikus.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.andronikus.client.enums.ExecutionsModes;

public abstract class ExecutionMode {
    @JsonProperty("url")
    private String callbackUrl;
    private String massive;
    private String massiveClass;
    private String pending;
    private String otherInfo;
    private ExecutionsModes executionMode;

    public ExecutionMode(ExecutionsModes executionMode) {
        this.executionMode = executionMode;
    }

    public ExecutionMode( ExecutionsModes executionMode, String callbackUrl) {
        this.callbackUrl = callbackUrl;
        this.executionMode = executionMode;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getMassive() {
        return massive;
    }

    public void setMassive(String massive) {
        this.massive = massive;
    }

    public String getMassiveClass() {
        return massiveClass;
    }

    public void setMassiveClass(String massiveClass) {
        this.massiveClass = massiveClass;
    }

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public ExecutionsModes getExecutionMode() {
        return executionMode;
    }

    public void setExecutionMode(ExecutionsModes executionMode) {
        this.executionMode = executionMode;
    }

    @Override
    public String toString() {
        return "ExecutionMode{" +
                "callbackUrl='" + callbackUrl + '\'' +
                ", massive='" + massive + '\'' +
                ", massiveClass='" + massiveClass + '\'' +
                ", pending='" + pending + '\'' +
                ", otherInfo='" + otherInfo + '\'' +
                ", executionMode=" + executionMode.toString() +
                '}';
    }
}
