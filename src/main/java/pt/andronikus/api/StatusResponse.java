package pt.andronikus.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import pt.andronikus.singletons.Migration;

public class StatusResponse {
    @JsonProperty
    private Migration.Status status;

    public StatusResponse() { }

    public StatusResponse(Migration.Status status) {
        this.status = status;
    }

    public Migration.Status getStatus() {
        return status;
    }

    public void setStatus(Migration.Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StatusResponse{" +
                "status=" + status +
                '}';
    }
}
