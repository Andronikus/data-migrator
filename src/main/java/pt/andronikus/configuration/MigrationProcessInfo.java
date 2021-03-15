package pt.andronikus.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MigrationProcessInfo {
    @JsonProperty
    @NotNull
    @NotEmpty
    private String orderSource;
    @JsonProperty
    @NotNull
    @NotEmpty
    private String channel;

    public MigrationProcessInfo() {
    }

    public MigrationProcessInfo(String orderSource, String channel) {
        this.orderSource = orderSource;
        this.channel = channel;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "MigrationProcessInfo{" +
                "orderSource='" + orderSource + '\'' +
                ", channel='" + channel + '\'' +
                '}';
    }
}
